package com.yidu.module;

import com.yidu.utils.Constants;
import com.yidu.utils.ModuleTools;

import java.util.Date;

/**
 * Created by Administrator on 2016/10/25.
 */
public class BaseModule {
    private int id;
    private String creator;
    private String modifier;
    private String createTime;
    private String modifyTime;
    private String isDeleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime= ModuleTools.formatDate(createTime, Constants.formatTime);
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = ModuleTools.formatDate(modifyTime, Constants.formatTime);
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}
