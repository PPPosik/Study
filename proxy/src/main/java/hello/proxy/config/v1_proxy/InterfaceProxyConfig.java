package hello.proxy.config.v1_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {
    @Bean
    public OrderControllerV1 orderController(LogTrace trace) {
        return new OrderControllerInterfaceProxy(new OrderControllerImplV1(orderService(trace)), trace);
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace trace) {
        return new OrderServiceInterfaceProxy(new OrderServiceImplV1(orderRepository(trace)), trace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace trace) {
        return new OrderRepositoryInterfaceProxy(new OrderRepositoryImplV1(), trace);
    }
}
