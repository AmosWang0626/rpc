package com.amos.arpc.netty.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 服务元数据
 *
 * @author amos.wang
 * @date 2021/7/11
 */
@Getter
@Setter
@Builder
public class ServiceMetadata {

    private String application;

    private String clazzName;

    private List<ServiceMethod> methodList;

    @Override
    public String toString() {
        return "ServiceMetadata{" +
                "application='" + application + '\'' +
                ", clazzName='" + clazzName + '\'' +
                ", methodList=" + methodList +
                '}';
    }
}
