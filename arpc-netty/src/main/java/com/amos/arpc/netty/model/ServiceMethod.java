package com.amos.arpc.netty.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 服务方法
 *
 * @author amos.wang
 * @date 2021/7/11
 */
@Getter
@Setter
@Builder
public class ServiceMethod {

    private String methodName;

    private String parameterTypes;

    private String returnType;

    @Override
    public String toString() {
        return "ServiceMethod{" +
                "methodName='" + methodName + '\'' +
                ", parameterTypes='" + parameterTypes + '\'' +
                ", returnType='" + returnType + '\'' +
                '}';
    }
}
