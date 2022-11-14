package hello.proxy.config.v1_proxy;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {
    @Bean
    public OrderControllerV2 orderControllerConcreteProxy(LogTrace trace) {
        return new OrderControllerConcreteProxy(new OrderControllerV2(orderServiceConcreteProxy(trace)), trace);
    }

    @Bean
    public OrderServiceV2 orderServiceConcreteProxy(LogTrace trace) {
        return new OrderServiceConcreteProxy(new OrderServiceV2(orderRepositoryConcreteProxy(trace)), trace);
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryConcreteProxy(LogTrace trace) {
        return new OrderRepositoryConcreteProxy(new OrderRepositoryV2(), trace);
    }
}
