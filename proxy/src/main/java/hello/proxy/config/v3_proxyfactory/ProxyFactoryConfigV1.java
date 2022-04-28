package hello.proxy.config.v3_proxyfactory;

import hello.proxy.app.v1.*;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfigV1 {
    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace trace) {
        OrderControllerV1 target = new OrderControllerImplV1(orderServiceV1(trace));
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(trace));
        OrderControllerV1 proxy = (OrderControllerV1) proxyFactory.getProxy();

        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), target.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace trace) {
        OrderServiceImplV1 target = new OrderServiceImplV1(orderRepositoryV1(trace));
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(trace));
        OrderServiceV1 proxy = (OrderServiceV1) proxyFactory.getProxy();

        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), target.getClass());
        return proxy;
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace trace) {
        OrderRepositoryImplV1 target = new OrderRepositoryImplV1();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(trace));
        OrderRepositoryV1 proxy = (OrderRepositoryV1) proxyFactory.getProxy();

        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), target.getClass());
        return proxy;
    }

    private Advisor getAdvisor(LogTrace trace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        LogTraceAdvice advice = new LogTraceAdvice(trace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
