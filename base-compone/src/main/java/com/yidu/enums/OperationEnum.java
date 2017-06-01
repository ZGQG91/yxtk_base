package com.yidu.enums;

public enum OperationEnum {
    /**
     * 读取， 需要读取和刷新缓存
     */
    READ,
    /**
     * 写， 需要清空缓存
     */
    WRITE;
}
