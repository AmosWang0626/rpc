package com.amos.api;

import com.amos.api.service.HelloService;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Hello Service Test
 *
 * @author wangdaoyuan.wdy
 * @date 2021/7/1
 */
public class HelloServiceTest {

    public static void main(String[] args) {
        final Class<HelloService> helloServiceClass = HelloService.class;

        final Method[] methods = helloServiceClass.getMethods();

        System.out.println(methods.length);

        for (Method method : methods) {
            System.out.println(method);

            final Class<?>[] parameterTypes = method.getParameterTypes();
            System.out.println(Arrays.toString(parameterTypes));

            final Class<?> returnType = method.getReturnType();
            System.out.println(returnType);
        }
    }

}
