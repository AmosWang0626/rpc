package com.amos.arpc.netty.common.serializer;

import com.alibaba.com.caucho.hessian.io.HessianInput;
import com.alibaba.com.caucho.hessian.io.HessianOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 序列化---Hessian2
 *
 * @author Daoyuan
 * @date 2019/3/19
 */
public class Hessian2Serializer implements Serializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hessian2Serializer.class);

    @Override
    public byte getSerializerCode() {
        return SerializerTypeEnum.HESSIAN2.getType();
    }

    @Override
    public byte[] serialize(Object object) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput hessianOutput = new HessianOutput(os);

        try {
            hessianOutput.writeObject(object);
            return os.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Hessian 序列化失败!!! [{}]", e.getMessage());
        }

        return null;
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        HessianInput hessianInput = new HessianInput(is);

        try {
            return (T) hessianInput.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Hessian 反序列化失败!!! [{}]", e.getMessage());
        }

        return null;
    }

}
