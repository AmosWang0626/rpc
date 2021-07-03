package com.amos.grpc;

import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;

/**
 * ProtoBuf Empty
 *
 * @author wangdaoyuan.wdy
 * @date 2021/7/3
 */
public class EmptyTest {

    public static void main(String[] args) {
        try {
            System.out.println("ByteString.EMPTY:" +
                    Empty.parseFrom(ByteString.EMPTY).toBuilder().build());

            System.out.println("ByteString.copyFrom([]):" +
                    Empty.parseFrom(ByteString.copyFrom(new ArrayList<>())).toBuilder().build());

            System.out.println("ByteString.copyFromUtf8(\"\"):" +
                    Empty.parseFrom(ByteString.copyFromUtf8("")).toBuilder().build());

            // 会抛异常，Empty.parseFrom 应该只能接收空字符串
            System.out.println(Empty.parseFrom(ByteString.copyFromUtf8("你好呀！")).toBuilder().build());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

}
