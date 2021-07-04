# RPC 汇总

- spring cloud
- dubbo
- gRPC
- aRPC（类似Dubbo，手写RPC）

|RPC体系|涉及模块|额外占用端口|应用端口|
|---|---|---|---|
| spring cloud | common-api, spring-cloud-consumer, spring-cloud-provider | 无 | consumer 8080, provider 8081 |
| dubbo | common-api, dubbo-consumer, dubbo-provider | 20880 | consumer 8082, provider 8083 |
| gRPC | grpc-api, grpc-consumer, grpc-provider | 50051 | 无 |
| aRPC | common-api, arpc-netty, arpc-consumer, arpc-provider | 7000 | consumer 8084, provider 8085 |

注：只有 spring cloud 无需额外占用端口。

|RPC体系|技术栈|总结|
|---|---|---|
| spring cloud | OpenFeign(Feign + Ribbon)、 | 服务间 Restful 方式调用，Ribbon支持负载均衡（随机、轮询、权重、重试等等）。 |
| dubbo | Netty、Dubbo协议、Hessian2序列化 | 基于TCP、Dubbo协议传输，SPI机制拓展性强，有多种序列化方式可选，默认 Hessian2。 |
| gRPC | Netty、HTTP/2协议、ProtoBuf序列化 | 使用ProtoBuf序列化，跨语言、性能高；使用HTTP/2协议通信，通用性强；整体，自我感觉对Java不太友好，有点麻烦 |

## 总结

RPC（Remote Procedure Call）远程过程调用。要实现的效果就是，服务间调用就像本地方法调用一样简单，对外屏蔽网络通信。

真正执行的时候，consumer只是调用一个接口，provider就能收到请求，并进行本地方法调用，返回相应结果。

实现RPC的方式很多，本质上C/S架构，我们平时开发的MVC应用，也是C/S，服务端收到请求，经过 DispatcherServlet 转发到对应到 Controller方法等等。

要实现的功能：

1. 客户端和服务端通信（序列化、传输协议）
2. 客户端接口调用（动态代理，请求服务端）
3. 服务端响应（收到请求，调用本地方法，返回结果）

Netty的各种优点，自然是通信首选，NIO多路复用等。序列化会直接影响方法调用前后的开销，序列化后的包越小，传输的速度也自然越快。
传输协议，http1.0令人诟病的阻塞、请求头过大等等。所以精巧的协议自然是便于传输的，但是精巧的东西，往往不够健壮、不够通用。

但是，HTTP/2出来了，二进制分帧、多路复用、头部压缩、双向传输等等，以后应该是主流了。

序列化，犹未可知，ProtoBuf在跨语言方面优势明显；但对于纯Java应用来说，不太友好。看 Dubbo3 相关文档，Kryo或者FST这些序列化新秀还是很有潜力的。

## 参考文档

- [https://dubbo.apache.org/zh/docs/v3.0/references/serializations/serialization/](https://dubbo.apache.org/zh/docs/v3.0/references/serializations/serialization/)