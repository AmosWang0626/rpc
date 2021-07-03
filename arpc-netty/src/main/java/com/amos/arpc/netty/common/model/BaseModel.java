package com.amos.arpc.netty.common.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Request/Response基类
 *
 * @author Daoyuan
 * @date 2019/3/19
 */
@Getter
@Setter
public abstract class BaseModel implements Serializable {

    private static final long serialVersionUID = -6407325355917095397L;

    /**
     * 创建时间
     */
    private Long createTime = System.currentTimeMillis();

    /**
     * 协议版本
     */
    private Byte protocolVersion = 1;
    /**
     * 序列化方式
     */
    private Byte serializeType;

    /**
     * 获取当前 Model 对应的指令
     *
     * @return 指令
     */
    public abstract ModelTypeEnum getCommand();
}
