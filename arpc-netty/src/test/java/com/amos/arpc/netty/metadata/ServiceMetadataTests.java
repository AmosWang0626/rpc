package com.amos.arpc.netty.metadata;

import com.amos.arpc.netty.model.ServiceMetadata;
import com.amos.arpc.netty.model.ServiceMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 服务元数据测试
 *
 * @author amos.wang
 * @date 2021/7/11
 */
public class ServiceMetadataTests {

    public static void main(String[] args) {
        Class<HelloService> clazz = HelloService.class;

        ServiceMetadata metadata = ServiceMetadata.builder()
                .application("provider")
                .clazzName(clazz.getName())
                .build();

        List<ServiceMethod> serviceMethods = new ArrayList<>();

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            ServiceMethod serviceMethod = ServiceMethod.builder()
                    .methodName(method.getName())
                    .parameterTypes(Arrays.stream(method.getGenericParameterTypes())
                            .map(Type::getTypeName)
                            .collect(Collectors.joining(",")))
                    .returnType(method.getGenericReturnType().getTypeName())
                    .build();

            serviceMethods.add(serviceMethod);
        }

        metadata.setMethodList(serviceMethods);

        System.out.println(metadata);
    }

    interface HelloService {
        String sayHello(String name, Integer scene);

        List<String> sayHello(String name, Map<String, List<String>> param);

        Map<String, List<String>> sayHello(String name, List<String> param);
    }

}