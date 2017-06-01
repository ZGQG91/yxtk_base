package com.yidu.service.impl;

import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import com.yidu.HanlpBaseServiceInter;
import com.yidu.baseabstract.AbstractService;
import com.yidu.callback.MethodCallback;
import com.yidu.exception.Code;
import com.yidu.result.DbApiResult;
import com.yidu.service.IServiceInterface;
import com.yidu.utils.PageDataInter;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/26.
 */
public class HanlpBaseService extends AbstractService implements IServiceInterface,HanlpBaseServiceInter {
    @Override
    public Object execute(String method, PageDataInter param, PageDataInter header,String methodVersion) {
        if("testHanlpVersion".equals(method)){
            DbApiResult resultStr=this.testHanlpVersion(methodVersion,param);
            return resultStr;
        }
        if("insertHanlp".equals(method)){
            this.insertHanlp(param);
        }
        if("splitWords".equals(method)){
            Object obj=this.splitWords(param);
            return new DbApiResult(obj,"分词成功", Code.BIZSUCCESS,0);
        }
        return new DbApiResult(null,"请求方法不存在", Code.BIZFAIL);
    }

    public boolean insertHanlp(Map<String,Object> param) {
        try{
            String key=(String)param.get("key");
            String value=(String)param.get("value");
            CustomDictionary.add(key,value);
            return true;
        }catch(Exception e){
            e.getMessage();
            return false;
        }
    }

    public List<Term> splitWords(Map<String,Object> map) {
        try{
            String question =(String)map.get("question");
            List<Term> termList = StandardTokenizer.segment(question);
            return termList;
        }catch(Exception e){
            return null;
        }
    }

    public DbApiResult<Object> testHanlpVersion(final String methodVersion, Map<String,Object> param){
        Object obj=param.get("version");
        if(obj==null)return null;
        String version= (String) obj;
        final DbApiResult<Object> execResult=new DbApiResult<Object>();
        operationMethodTemplate.opera(execResult,version,methodVersion,new MethodCallback() {
            @Override
            public void oldMethod() {
                execResult.setData("执行"+methodVersion+"版本的方法");
            }
            @Override
            public void invokeMethod() {
                execResult.setData("最新版本的方法");
            }
        });
        return execResult;
    }
}
