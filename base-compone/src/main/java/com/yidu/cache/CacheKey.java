package com.yidu.cache;

import java.lang.annotation.*;

@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheKey {

    /** 缓存字段，必须具有getter方法, 为空则直接去当前参数的toString(), 当前参数为空则为"" */
    String[] fields() default {};

}
