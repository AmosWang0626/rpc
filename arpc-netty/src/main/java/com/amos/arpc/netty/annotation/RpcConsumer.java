package com.amos.arpc.netty.annotation;

import java.lang.annotation.*;

/**
 * RpcConsumer
 *
 * @author amos.wang
 * @date 2021/7/11
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface RpcConsumer {



}
