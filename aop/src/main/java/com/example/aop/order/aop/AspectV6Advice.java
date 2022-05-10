package com.example.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {
//    @Around("com.example.aop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // @Before
            log.info("[transaction] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            // @AfterReturning
            log.info("[transaction commit] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            // @AfterThrowing
            log.info("[transaction rollback] {}", joinPoint.getSignature());
            throw e;
        } finally {
            // @After
            log.info("[resource release] {}", joinPoint.getSignature());
        }
    }

    @Before("com.example.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "com.example.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doAfterReturing(JoinPoint joinPoint, Object result) {
        log.info("[return] {}, result={}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "com.example.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {}, message={}", joinPoint.getSignature(), ex);
    }

    @After("com.example.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }
}
