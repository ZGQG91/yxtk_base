package com.yidu.distribute;

import com.yidu.baseabstract.AbstractDynCompreApiService;
import com.yidu.callback.ServiceCallback;
import com.yidu.enums.VersionEnum;
import com.yidu.exception.Code;
import com.yidu.result.DbApiResult;
import com.yidu.service.IDynCompreApiService;
import com.yidu.service.IServiceInterface;
import com.yidu.utils.PageDataInter;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2016/10/19.
 */
@Service
public class DynCompreApiService extends AbstractDynCompreApiService implements IDynCompreApiService {
    public DbApiResult<Object> execute(String srvName, String method, PageDataInter request) {
        return this.execute(srvName, method, request,null);
    }
    public DbApiResult<Object> execute(final String srvName, final String method, final PageDataInter request, final PageDataInter header) {
        final DbApiResult<Object> execResult=new DbApiResult<Object>();
        operationTemplate.opera(execResult,config,new ServiceCallback(){
            IServiceInterface iServiceInterface;
            VersionEnum version;
            boolean flag;
            @Override
            public boolean valid() {
                boolean flag=(Boolean) userColCompone.validLogin(request);
                return flag;
            }
            @Override
            public String versionControl() {
                String methodVersion=findMethodVersion(srvName,method);
                return methodVersion;
            }
            @Override
            public void doOperation() {
//                String userId= (String) request.get("userId");
//                PermissionModule user=userCompone.queryUserCompone(userId,srvName);
//                //解析权限
//                if(permission.get(srvName)==null){
//                    flag=true;
//                }else{
//                    flag= PermissionTools.parsePermission(PermissionTools.splitToStr(user),permission.get(srvName));
//                }
                //占时不使用权限
                flag=true;
                if(flag){
                    String apiVersion=(String)request.get("serviceVersion");
                    if (StringUtils.isBlank(apiVersion)) {
                        version = VersionEnum.CURRENT_VERSION;
                    } else {
                        version = VersionEnum.getVersion(apiVersion);
                    }
                    iServiceInterface=findApiHandler(srvName,VersionEnum.CURRENT_VERSION);
                }
            }
            @Override
            public void check() {
                this.check("");
            }
            @Override
            public void check(String methodVersion){
                if(flag){
                    if(iServiceInterface==null){
                        execResult.setData(null);
                        execResult.setCode(Code.FAIL);
                        execResult.setMessage("未找到此服务");
                        execResult.setSuccess(Code.BIZFAIL);
                        execResult.setVersion("0");
                    }else{
                        Object exec=iServiceInterface.execute(method,request,header,methodVersion);
                        DbApiResult resu=null;
                        if(exec!=null){
                            resu=(DbApiResult) exec;
                            execResult.setVersion(version.getVersion());
                            execResult.setData(resu.getData());
                            execResult.setMessage(resu.getMessage());
                            execResult.setCode(resu.getCode());
                            execResult.setTotalCount(resu.getTotalCount());
                            execResult.setSuccess(resu.isSuccess());
                        }else{
                            execResult.setVersion(version.getVersion());
                            execResult.setData(null);
                            execResult.setMessage("未找到此方法");
                            execResult.setCode(200);
                            execResult.setTotalCount(0);
                            execResult.setSuccess(true);
                        }
                    }
                }else{
                    execResult.setData(null);
                    execResult.setCode(300);
                    execResult.setMessage("没有权限");
                    execResult.setSuccess(Code.BIZFAIL);
                }
            }
        });
        return execResult;
    }

    public DbApiResult<Object> execute(String srvName, String method, PageDataInter request, PageDataInter header, MultipartFile file) {
        request.put("file",file);
        return this.execute(srvName, method, request, header);
    }

    /**
     * 方法的版本信息
     * @param srvname
     * @param method
     * @return
     */
    public String findMethodVersion(String srvname,String method){
        if(method==null)return null;
        String version=this.method(srvname, method);
        return version;
    }
    /**
     * 查找服务
     * @param srvname
     * @param version
     * @return
     */
    public IServiceInterface findApiHandler(String srvname,VersionEnum version){
        if(version==null)return null;
        IServiceInterface iServiceInterface=this.handler(srvname, version);
        if(iServiceInterface!=null){
            return iServiceInterface;
        }
        return findApiHandler(srvname, version.getParent());
    }

    public String method(String srvname,String method){
        if(CollectionUtils.isEmpty(versionHanlers)){
            return null;
        }
        String version= versionHanlers.get(generateVersionKey(srvname,method));
        return version;
    }
    public IServiceInterface handler(String srvname,VersionEnum version){
        if(CollectionUtils.isEmpty(compareHandlers)){
            return null;
        }
        if(version==null){
            return null;
        }
        IServiceInterface iServiceInterface= compareHandlers.get(generateServerKey(srvname,version));
        return iServiceInterface;
    }

    private String generateServerKey(String srvName, VersionEnum version) {
        return srvName + "-" + version.getVersion();
    }
    private String generateVersionKey(String srvName, String method) {
        return srvName + "-" + method;
    }
}
