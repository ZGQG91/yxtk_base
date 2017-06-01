package com.yidu.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class PageData extends HashMap implements PageDataInter {
	private static final long serialVersionUID = 1L;
	Map map = null;
	HttpServletRequest request;
	/**
	 * （1）传输方式 1 gzip 0 默认
	 * （2）是否获取头参数 1 获取 0 默认不获取
	 * @param request
     */
	public PageData(HttpServletRequest request, PageDataEnum pageDataEnum) {
		this.request=request;
		this.param(request);
		if(false){
			int transmissionMethod=pageDataEnum.getTransmissionMethod();
			int secMethod=pageDataEnum.getSecMethod();
			String paramName=pageDataEnum.getParamName();
			int isHead=pageDataEnum.getIsHead();
			this.transmissionMethod(transmissionMethod,secMethod,paramName,request);
			this.headParam(isHead,request);
		}

	}

	public String aes(String aesParamName){
		String param= (String) map.get(aesParamName);
		String aes="";
		if(!StringUtils.isEmpty(param)){
			byte[] bytes= Tools.parseHexStr2Byte(param);
			byte[] aesSecs= Tools.decrypt(bytes,"aesSec");
			aes=new String(aesSecs);
		}
		return aes;
	}

	public PageData(){
		map=new HashMap();
	}

	public void transmissionMethod(int transmission,int aesMethod,String aesParamName,HttpServletRequest request){
		switch(transmission){
			case 0:
				//默认
				this.param(request);
				break;
			case 1:
				//gzip
				this.excuteMethod(aesMethod,aesParamName);
				break;
		}
	}

	public void excuteMethod(int aesMethod,String aesParamName){
		String gzip=GZIPUtils.getBoydParam(request);
		map= JSONObject.parseObject(gzip);
		if(aesMethod==1){
			this.param(request);
			this.aesParseMap(aesParamName);
		}
	}

	public void headParam(int isHead,HttpServletRequest request){
		switch(isHead){
			case 0:
				//默认
				this.headParam(request);
				break;
			case 1:
				break;
		}
	}

	public Map<String,Object> paramToMap(String json){
		Map<String,Object> pa=new HashMap<String,Object>();
		String[] arrayStr=json.split("&");
		for(int i=0;i<arrayStr.length;i++){
			String map=arrayStr[i];
			String[] val=map.split("=");
			if(val.length>1){
				pa.put(val[0],val[1]);
			}else{
				pa.put(val[0],null);
			}
		}
		return pa;
	}
	public void aesParseMap(String aesParamName){
		String aesStr=this.aes(aesParamName);
		map=this.paramToMap(aesStr);
	}
//	public PageData(HttpServletRequest request) {
//		Map properties = request.getParameterMap();
//		returnMap(request,properties);
//	}
//	public PageData(HttpServletRequest request, String json, String type) {
//		Map properties=JSONObject.parseObject(json);
//		returnMap(request,properties);
//	}

	public void headParam(HttpServletRequest request){
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String keyHeader = (String) headerNames.nextElement();
			String valueHeeader = request.getHeader(keyHeader);
			map.put(keyHeader, valueHeeader);
		}
	}
	public String getString(Object key) {
		return (String) get(key);
	}

	public void param(HttpServletRequest request){
		Map returnMap = new HashMap();
		Map properties = request.getParameterMap();
		Iterator entries = properties.entrySet().iterator();
		Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		map=returnMap;
	}

	@Override
	public Object get(Object key) {
		Object obj = null;
		if (map.get(key) instanceof Object[]) {
			Object[] arr = (Object[]) map.get(key);
			obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	@Override
	public Set entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	@Override
	public Set keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putAll(Map t) {
		// TODO Auto-generated method stub
		map.putAll(t);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	@Override
	public Collection values() {
		// TODO Auto-generated method stub
		return map.values();
	}

}
