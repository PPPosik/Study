package hello.proxy.config.v3_proxyfactory.advice;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public class LogTraceAdvice implements MethodInterceptor {
    private final LogTrace trace;

    public LogTraceAdvice(LogTrace trace) {
        this.trace = trace;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();

        TraceStatus status = trace.begin(method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()");
        try {
            Object result = invocation.proceed();
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
