package com.amos.arpc.netty.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 服务地址
 *
 * @author amos.wang
 * @date 2021/7/11
 */

@Getter
@Setter
@Builder
public class ServiceUrl {

    private String application;

    private String host;

    private Integer port;

    @Override
    public String toString() {
        return "ServiceUrl{" +
                "application='" + application + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
