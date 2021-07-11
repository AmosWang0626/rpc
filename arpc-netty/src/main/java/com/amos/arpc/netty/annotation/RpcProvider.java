package com.amos.arpc.netty.annotation;

import java.lang.annotation.*;

/**
 * RpcProvider
 *
 * @author amos.wang
 * @date 2021/7/11
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
public @interface RpcProvider {



}
