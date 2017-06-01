package com.yidu.callback;

/**
 * Created by Administrator on 2016/11/9.
 */
public abstract class ServiceCallback {
    /**
     * 检查操作
     */
    public void doOperation(){}

    /**
     * 查找服务 判断版本
     * @param version
     */
    public void check(String version){}
    /**
     * 查找服务
     */
    public void check(){}

    /**
     * 查找版本配置
     * @return
     */
    public String versionControl(){return null;}

    /**
     * 用户验证
     * @return
     */
    public boolean valid(){return true;}
}
