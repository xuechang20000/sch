package com.wb.utils.web;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wb.jdbcutils.Page;
import com.wb.utils.web.common.AjaxObject;
import com.wb.utils.web.common.AjaxPage;
import com.wb.utils.web.common.JsonDate;

public class JsonUtils {
	private static final Log LOGGER = LogFactory.getLog(JsonUtils.class);

	public static String getJsonByObject(boolean flag, String str) {
		return getJsonData(new AjaxObject(flag, str));
	}

	public static String getJsonByPage(Page page) {
		AjaxPage minipage = new AjaxPage();
		minipage.setTotal(new Long(page.getTotalrows()));
		minipage.setData(page.getResult());
		return getJsonData(minipage);
	}

	public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
		JSONArray jsonArr = JSONArray.fromObject(jsonStr);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Iterator<JSONObject> it = jsonArr.iterator();
		while (it.hasNext()) {
			JSONObject json2 = it.next();
			list.add(parseJSON2Map(json2.toString()));
		}
		return list;
	}

	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		/*
		 * Map<String, Object> map = new HashMap<String, Object>(); JSONArray
		 * jsonArr = JSONArray.fromObject(jsonStr); Iterator<JSONObject> it =
		 * jsonArr.iterator(); while(it.hasNext()){ JSONObject json2 =
		 * it.next(); for(Object k : json2.keySet()){
		 * System.out.println(k.toString()); } }
		 */

		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(parseJSON2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

	private static void fixJSONObject(JSONObject jsonObject, String dateProp, String dateFormat) {
		try {
			if ((jsonObject.get(dateProp).equals(null)) || (jsonObject.get(dateProp).equals(""))) {
				jsonObject.put(dateProp, new JSONObject(true));
			} else {
				Date date = new SimpleDateFormat(dateFormat).parse(jsonObject.get(dateProp).toString());
				jsonObject.put(dateProp, "{\"time\":" + JSONObject.fromObject(date).get("time") + "}");
			}
		} catch (ParseException e) {
			LOGGER.error("parse json data error, lack of property: '" + dateProp + "' in json string: " + jsonObject,
					e);
		}
	}

	/*
	 * public static String getJsonDataFromPage(Page page, Class voClass) { if
	 * (voClass != null) { return getJsonData(transformPage(page,
	 * voClass)).toString(); }
	 * 
	 * return getJsonData(page); }
	 * 
	 * public static Page transformPage(Page page, Class voClass) { List boList
	 * = page.getResult(); if ((boList != null) && (!boList.isEmpty())) { List
	 * voList = new ArrayList(boList.size()); Iterator iter = boList.iterator();
	 * while (iter.hasNext()) { try { Object voObject = voClass.newInstance();
	 * Object boObject = iter.next(); BeanUtils.copyProperties(boObject,
	 * voObject); voList.add(voObject); } catch (InstantiationException e) {
	 * LOGGER.error("VO class cannot be instantiated: " + voClass.getName(), e);
	 * } catch (IllegalAccessException e) {
	 * LOGGER.error("VO class instantiated error: " + voClass.getName(), e); } }
	 * 
	 * page.setResult(voList); } return page; }
	 */

	public static JsonConfig getJsonConfig(String dateFormat) {
		JsonDate beanProcessor = new JsonDate();
		if (dateFormat != null) {
			DateFormat df = new SimpleDateFormat(dateFormat);
			beanProcessor.setDateFormat(df);
		}

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(Date.class, beanProcessor);
		String defaultValue = "null";
		if ((StringUtils.isNotBlank(defaultValue)) && (defaultValue.equals("null"))) {
			jsonConfig.registerDefaultValueProcessor(Integer.class, new DefaultValueProcessor() {
				@Override
				public Object getDefaultValue(Class type) {
					return null;
				}
			});
			jsonConfig.registerDefaultValueProcessor(Long.class, new DefaultValueProcessor() {
				@Override
				public Object getDefaultValue(Class type) {
					return null;
				}
			});
			jsonConfig.registerDefaultValueProcessor(Float.class, new DefaultValueProcessor() {
				@Override
				public Object getDefaultValue(Class type) {
					return null;
				}
			});
			jsonConfig.registerDefaultValueProcessor(Double.class, new DefaultValueProcessor() {
				@Override
				public Object getDefaultValue(Class type) {
					return null;
				}
			});
			jsonConfig.registerDefaultValueProcessor(BigInteger.class, new DefaultValueProcessor() {
				@Override
				public Object getDefaultValue(Class type) {
					return null;
				}
			});
			jsonConfig.registerDefaultValueProcessor(BigDecimal.class, new DefaultValueProcessor() {
				@Override
				public Object getDefaultValue(Class type) {
					return null;
				}
			});
		}
		return jsonConfig;
	}

	public static String getJsonData(Object bean) {
		return JSONObject.fromObject(bean, getJsonConfig(null)).toString();
	}

	public static String getJsonDataFromCollection(Collection collection) {
		return JSONArray.fromObject(collection, getJsonConfig(null)).toString();
	}

	public static String formatJsonData(String jsonData, String[] dateProps) {
		return formatJsonData(jsonData, dateProps, "yyyy-MM-dd'T'HH:mm:ss");
	}

	public static String formatJsonData(String jsonData, String[] dateProps, String dateFormat) {
		if (dateProps == null) {
			return jsonData;
		}
		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		for (Iterator iter = Arrays.asList(dateProps).iterator(); iter.hasNext();) {
			String dateProp = (String) iter.next();
			if (jsonObject.has(dateProp)) {
				fixJSONObject(jsonObject, dateProp, dateFormat);
			}
		}
		return jsonObject.toString();
	}

	public static String formatJsonDataArray(String jsonData, String[] dateProps, String dateFormat) {
		if (dateProps == null) {
			return jsonData;
		}
		JSONArray jsonArray = JSONArray.fromObject(jsonData);
		JSONObject jsonObject;
		Iterator iter;
		for (int i = 0; i < jsonArray.size(); i++) {
			jsonObject = jsonArray.getJSONObject(i);
			for (iter = Arrays.asList(dateProps).iterator(); iter.hasNext();) {
				String dateProp = (String) iter.next();
				if (jsonObject.has(dateProp)) {
					fixJSONObject(jsonObject, dateProp, dateFormat);
				}
			}
		}
		return jsonArray.toString();
	}

	public static Object getBeanFromJsonData(String data, Class beanClass) {
		JSONObject jsonObject = JSONObject.fromObject(data);
		return JSONObject.toBean(jsonObject, beanClass);
	}

	public static Object getBeanFromJsonData(String data, String[] dateProps, String dateFormat, Class beanClass) {
		return getBeanFromJsonData(formatJsonData(data, dateProps, dateFormat), beanClass);
	}

	public static Object getBeanFromJsonData(String data, String[] dateProps, Class beanClass) {
		return getBeanFromJsonData(data, dateProps, "yyyy-MM-dd'T'HH:mm:ss", beanClass);
	}

	public static List getBeanListFromJsonData(String data, Class beanClass) {
		JSONArray jsonArray = JSONArray.fromObject(data);
		List list = new ArrayList(jsonArray.size());
		for (Iterator iter = jsonArray.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			list.add(JSONObject.toBean(jsonObject, beanClass));
		}
		return list;
	}

	public static List getBeanListFromJsonData(String data, String[] dateProps, String dateFormat, Class beanClass) {
		return getBeanListFromJsonData(formatJsonDataArray(data, dateProps, dateFormat), beanClass);
	}

	public static List getBeanListFromJsonData(String data, String[] dateProps, Class beanClass) {
		return getBeanListFromJsonData(data, dateProps, "yyyy-MM-dd'T'HH:mm:ss", beanClass);
	}

	public static List getBeanListWithExtendFromJsonData(String data, String[] dateProps, String[] extendProps,
			Class beanClass) {
		JSONArray jsonArray = new JSONArray();
		if ((dateProps != null) && (dateProps.length > 0)) {
			String fdata = formatJsonDataArray(data, dateProps, "yyyy-MM-dd'T'HH:mm:ss");
			jsonArray = JSONArray.fromObject(fdata);
		} else {
			jsonArray = JSONArray.fromObject(data);
		}

		List list = new ArrayList(jsonArray.size());
		for (Iterator iter = jsonArray.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			Map extendMap = new HashMap();
			for (int i = 0; i < extendProps.length; i++)
				try {
					extendMap.put(extendProps[i], PropertyUtils.getProperty(jsonObject, extendProps[i]));
				} catch (Exception ex) {
				}
			list.add(new Object[] { JSONObject.toBean(jsonObject, beanClass), extendMap });
		}
		return list;
	}
}
