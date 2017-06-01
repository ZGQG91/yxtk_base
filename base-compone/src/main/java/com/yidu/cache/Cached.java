package com.yidu.cache;

import com.yidu.enums.OperationEnum;
import com.yidu.enums.PrefixEnum;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cached {

    /**
     * 缓存项前缀
     *
     * @return
     */
    PrefixEnum prefix();

    /**
     * 操作类型， 默认为读取
     *
     * @return
     */
    OperationEnum operateType() default OperationEnum.READ;

    /**
     * 静态缓存key，针对无参数的情况
     *
     * @return
     */
    String staticKey() default "";

    /** 过期时间：默认为1小时， 单位为S */
    int expire() default 60 * 60;

}
