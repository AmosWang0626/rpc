package com.amos.arpc.netty.common.serializer;

import com.esotericsoftware.kryo.kryo5.Kryo;
import com.esotericsoftware.kryo.kryo5.io.Input;
import com.esotericsoftware.kryo.kryo5.io.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 序列化---Kryo
 *
 * @author wangdaoyuan.wdy
 * @date 2021/7/3
 */
public class KryoSerializer implements Serializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KryoSerializer.class);

    private static final int OUTPUT_BUFFER_INIT_SIZE;
    private static final int OUTPUT_BUFFER_MAX_SIZE;

    static {
        OUTPUT_BUFFER_INIT_SIZE = 8192;
        OUTPUT_BUFFER_MAX_SIZE = 102400;
    }

    private static final ThreadLocal<Kryo> KRYO = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);
        kryo.setReferences(false);
        return kryo;
    });

    @Override
    public byte getSerializerCode() {
        return SerializerTypeEnum.KRYO.getType();
    }

    @Override
    public byte[] serialize(Object object) {
        try {
            Output output = new Output(OUTPUT_BUFFER_INIT_SIZE, OUTPUT_BUFFER_MAX_SIZE);
            KRYO.get().writeClassAndObject(output, object);

            byte[] bytes = output.toBytes();
            output.close();

            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Kryo 序列化失败!!! [{}]", e.getMessage());
        }

        return null;
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        Input input = new Input(bytes);

        try {
            return (T) KRYO.get().readClassAndObject(input);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Kryo 反序列化失败!!! [{}]", e.getMessage());
        }

        return null;
    }
}
