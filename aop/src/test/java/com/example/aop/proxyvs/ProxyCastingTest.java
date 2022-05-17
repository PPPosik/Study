package com.example.aop.proxyvs;

import com.example.aop.member.MemberService;
import com.example.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class ProxyCastingTest {
    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); // jdk dynamic proxy

        MemberService proxy1 = (MemberService) proxyFactory.getProxy();
        assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl proxy2 = (MemberServiceImpl) proxyFactory.getProxy();
        });
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // CGLIB proxy

        MemberService proxy1 = (MemberService) proxyFactory.getProxy();
        MemberServiceImpl proxy2 = (MemberServiceImpl) proxyFactory.getProxy();
    }
}
