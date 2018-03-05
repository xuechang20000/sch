package com.wb.contain.interceptor;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wb.utils.web.ReflectUtils;

public class ObjectInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//HandlerMethod handlerMethod = (HandlerMethod)handler;
		//Object bean = handlerMethod.getBean();
		try{
			Method getValue = handler.getClass().getMethod("getRequestValue");
			if(getValue!=null){
				Object value = getValue.invoke(handler);
				processRequestToObject(request,value);
			}
		}catch(Exception e){}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}
	
	private void processRequestToObject(HttpServletRequest request, Object obj) throws Exception{
		if(obj!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Field[] fields = ReflectUtils.getAllFields(obj.getClass());
			for(int i=0;i<fields.length;i++){
				String field = fields[i].getName();
				Class fieldType = fields[i].getType();
				Object value = null;
				if(fieldType.equals(java.lang.Integer.class)||fieldType.equals(int.class)
						||fieldType.equals(java.lang.Short.class)||fieldType.equals(short.class)
						||fieldType.equals(java.lang.Byte.class)||fieldType.equals(byte.class)){
					//value = RequestUtils.getParameter(request, field, 0);
					String valuenew = request.getParameter(field);
					if(StringUtils.hasText(valuenew)){
						value = Integer.parseInt(valuenew);
					}
				}else if(fieldType.equals(java.lang.Long.class)||fieldType.equals(long.class)){
					String valuenew = request.getParameter(field);
					if(StringUtils.hasText(valuenew)){
						value = Long.parseLong(valuenew);
					}
				}else if(fieldType.equals(java.lang.Float.class)||fieldType.equals(float.class)){
					String valuenew = request.getParameter(field);
					if(StringUtils.hasText(valuenew)){
						value = Float.parseFloat(valuenew);
					}
				}else if(fieldType.equals(java.lang.Double.class)||fieldType.equals(double.class)){
					String valuenew = request.getParameter(field);
					if(StringUtils.hasText(valuenew)){
						value = Double.parseDouble(valuenew);
					}
				}else if(fieldType.equals(java.lang.String.class)){
					String valuenew = request.getParameter(field);
					if(StringUtils.hasText(valuenew)){
						value = valuenew;
					}
				}else if(fieldType.equals(java.util.Date.class)){
					String valuenew = request.getParameter(field);
					try{
						value = sdf.parse(valuenew);
					}catch(Exception e){}
				}
				if(value!=null){
					String setFieldMethodName = "set"+field.substring(0,1).toUpperCase()+field.substring(1,field.length());
					Method m = obj.getClass().getMethod(setFieldMethodName, fieldType);
					m.invoke(obj, value);
				}
			}
		}
	}
	
	
	
}
