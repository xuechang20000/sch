package com.wb.contain.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb.jdbcutils.CommonJdbcUtils;
import com.wb.jdbcutils.Page;
import com.wb.jdbcutils.annos.Callable;
import com.wb.school.common.bo.TFile;
import com.wb.user.utils.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

import com.wb.contain.service.ContainService;
import com.wb.login.SessionUtils;
import com.wb.utils.web.JsonUtils;
import com.wb.utils.web.Md5Utils;
import com.wb.utils.web.RequestUtils;
import com.wb.utils.web.common.AjaxPage;

@Controller
public class ContainAction {
	private ContainService containService;
	private DefaultAnnotationHandlerMapping handlerMapping;

	@RequestMapping(value = "/common/codes.action", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String getCommonCodesPOST(HttpServletRequest request, HttpServletResponse response) {
		ServletContext context = request.getSession().getServletContext();
		String codename = RequestUtils.getParameter(request, "codename", null);
		List aa10s = null;
		if (codename != null) {
			aa10s = (List) context.getAttribute(codename.toUpperCase());
		}
		return JsonUtils.getJsonDataFromCollection(aa10s);
	}

	@RequestMapping(value = "/common/code.action", method = RequestMethod.GET)
	@ResponseBody
	public String getCommonCodesGET(HttpServletRequest request, HttpServletResponse response) {
		ServletContext context = request.getSession().getServletContext();
		String codename = RequestUtils.getParameter(request, "codename", null);
		List aa10s = null;
		if (codename != null) {
			aa10s = (List) context.getAttribute(codename.toUpperCase());
		}
		return JsonUtils.getJsonDataFromCollection(aa10s);
	}

	@RequestMapping(value = "/common/exportExcel.action", method = RequestMethod.POST)
	@ResponseBody
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
		String strJson = null;
		String url = RequestUtils.getParameter(request, "url", null);
		String header = RequestUtils.getParameter(request, "header", null);
		ServletOutputStream outputStream = null;
		if (url != null) {
			Map<String, Object> map = handlerMapping.getHandlerMap();
			String appName = request.getContextPath();
			url = url.replace(appName, "");
			Object action = getActionByUrl(url, map);
			try {
				try {
					//如果action中存在自定义查询方法，则调用自定义方法获取数据
					/**
					 *函数原型：
					 *public List getGridValue(HttpServletRequest request);
					 **/
					Method customQueryGridMethod = action.getClass().getMethod("getGridValue", HttpServletRequest.class);
					List list = (List) customQueryGridMethod.invoke(action, request);
					if (list != null && list.size() > 0) {
						AjaxPage minipage = new AjaxPage();
						minipage.setData(list);
						strJson = JsonUtils.getJsonData(minipage);
					}
				} catch (NoSuchMethodException e) {
					//如果action中不存在自定义查询方法，则系统自动处理
					Method method = getMethodByName(action, url);
					if (method != null) {
						Type[] types = method.getGenericParameterTypes();
						if (types == null || types.length == 0) {
							throw new RuntimeException("gird的请求方法必须包含HttpServletRequest参数");
						} else if (types != null && types.length > 0) {
							if (types.length == 1) {
								if (types[0].equals(HttpServletRequest.class)) {
									strJson = (String) method.invoke(action, request);
								}
							}
							if (types.length == 2) {
								if (types[0].equals(HttpServletRequest.class) && types[1].equals(HttpServletResponse.class)) {
									strJson = (String) method.invoke(action, request, response);
								} else if (types[0].equals(HttpServletResponse.class) && types[1].equals(HttpServletRequest.class)) {
									strJson = (String) method.invoke(action, response, request);
								}
							}
						}
					}
				}
				//System.out.println("header:"+header);
				//System.out.println("strJson:"+strJson);

				response.setContentType("application/vnd.ms-excel");
				outputStream = response.getOutputStream();
				Long userid = (Long) request.getSession().getAttribute(SessionUtils.USERID);
				String useridstr = null;
				if (userid == null || userid == 0) {
					Random random = new Random();
					int min = 1;
					int max = 999999;
					int s = random.nextInt(max) % (max - min + 1) + min;
					useridstr = Integer.toString(s);
				} else {
					useridstr = Long.toString(userid == null ? 0 : userid);
				}
				String useridMd5 = Md5Utils.MD5Code(useridstr);

				response.setHeader("content-disposition", "attachment;filename=" + useridMd5 + ".xls");
				containService.exportExcel(outputStream, request.getSession().getServletContext(), header, strJson);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (outputStream != null) {
					try {
						outputStream.flush();
						outputStream.close();
					} catch (IOException e) {
					}
				}
			}
		}
		return;
	}

	@RequestMapping(value = "/common/saveExcel.action", method = RequestMethod.POST)
	@ResponseBody
	public String saveExcel(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
		String useridMd5 = UUID.randomUUID().toString();
		String filePath=null;
		OutputStream outputStream = null;
		 filePath="/mnt/files/"+useridMd5+".xls";
        //filePath = "e:/" + useridMd5 + ".xls";
		TFile tFile=containService.saveFile(filePath, "A");

		outputStream = new FileOutputStream(filePath);
		saveExcelFile(request,response,outputStream,useridMd5);

		containService.updateToComplete(tFile.getFileid());
		return "{}";
	}

	public void saveExcelFile(HttpServletRequest request, HttpServletResponse response,OutputStream outputStream,String useridMd5) {
		String strJson = null;
		String url = RequestUtils.getParameter(request, "url", null);
		String header = RequestUtils.getParameter(request, "header", null);

		if (url != null) {
			Map<String, Object> map = handlerMapping.getHandlerMap();
			String appName = request.getContextPath();
			url = url.replace(appName, "");
			Object action = getActionByUrl(url, map);
			try {
				try {
					//如果action中存在自定义查询方法，则调用自定义方法获取数据
					/**
					 *函数原型：
					 *public List getGridValue(HttpServletRequest request);
					 **/
					Method customQueryGridMethod = action.getClass().getMethod("getGridValue", HttpServletRequest.class);
					List list = (List) customQueryGridMethod.invoke(action, request);
					if (list != null && list.size() > 0) {
						AjaxPage minipage = new AjaxPage();
						minipage.setData(list);
						strJson = JsonUtils.getJsonData(minipage);
					}
				} catch (NoSuchMethodException e) {
					//如果action中不存在自定义查询方法，则系统自动处理
					Method method = getMethodByName(action, url);
					if (method != null) {
						Type[] types = method.getGenericParameterTypes();
						if (types == null || types.length == 0) {
							throw new RuntimeException("gird的请求方法必须包含HttpServletRequest参数");
						} else if (types != null && types.length > 0) {
							if (types.length == 1) {
								if (types[0].equals(HttpServletRequest.class)) {
									strJson = (String) method.invoke(action, request);
								}
							}
							if (types.length == 2) {
								if (types[0].equals(HttpServletRequest.class) && types[1].equals(HttpServletResponse.class)) {
									strJson = (String) method.invoke(action, request, response);
								} else if (types[0].equals(HttpServletResponse.class) && types[1].equals(HttpServletRequest.class)) {
									strJson = (String) method.invoke(action, response, request);
								}
							}
						}
					}
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("content-disposition", "attachment;filename=" + useridMd5 + ".xls");
				containService.exportExcel(outputStream, request.getSession().getServletContext(), header, strJson);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (outputStream != null) {
					try {
						outputStream.flush();
						outputStream.close();
					} catch (IOException e) {
					}
				}
			}
		}
	}

	@RequestMapping(value = "/common/querySavedFiles.action")
	@ResponseBody
	public String queryUsersByNodeid(HttpServletRequest request, HttpServletResponse response,TFile tFile)
			throws Exception {
		int pageSize = RequestUtils.getParameter(request, "pageSize", 0);
		int pageIndex = RequestUtils.getParameter(request, "pageIndex", 0);

		Page page = new Page();
		page.setPageno(pageIndex + 1);
		page.setPagesize(pageSize);
		this.containService.querySavedFile(page,tFile);
		return JsonUtils.getJsonByPage(page);
	}
	@RequestMapping(value = "/common/downloadFile.action")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response,TFile tFile){
		TFile tFile1=CommonJdbcUtils.queryFirst("select * from t_file where fileid=?",TFile.class,tFile.getFileid());
		FileUtils.downLoadFile(request,response,tFile1.getFilepath());
	}
	private Method getMethodByName(Object action, String url){
		if(action==null||url==null)
			return null;
		Class clazz = action.getClass();
		Annotation clazzRequestMappingAnnotation=clazz.getAnnotation(RequestMapping.class);
		String clazzRequestMappingName=null;
		String requestUrl="";
		if(clazzRequestMappingAnnotation!=null)
			clazzRequestMappingName=clazzRequestMappingAnnotation.toString();
		Method[] methods = clazz.getMethods();
		for(int i=0;i<methods.length;i++){
			Method m = methods[i];
			RequestMapping requestMapping = m.getAnnotation(RequestMapping.class);
			if(requestMapping==null) continue;
			if(clazzRequestMappingName!=null)
				requestUrl=substrValue(clazzRequestMappingName)+requestMapping.value()[0];
			else
				requestUrl=requestMapping.value()[0];
			if(url.equals(requestUrl))
			return m;
		}
		return null;
	}
	public String substrValue(String value){
		String ss="";
		if(value.length()<7) return "";
		ss=value.substring(value.indexOf("value=[")+7);
		StringBuffer sb=new StringBuffer();
		for(char c:ss.toCharArray()){
			if(c!=']')
				sb.append(c);
			else
				break;
		}
		return sb.toString();
	}
	private Object getActionByUrl(String url, Map<String,Object> map){
		return map.get(url);
	}
	

	public DefaultAnnotationHandlerMapping getHandlerMapping() {
		return handlerMapping;
	}

	public void setHandlerMapping(DefaultAnnotationHandlerMapping handlerMapping) {
		this.handlerMapping = handlerMapping;
	}
	
	public ContainService getContainService() {
		return containService;
	}

	public void setContainService(ContainService containService) {
		this.containService = containService;
	}
	
}
