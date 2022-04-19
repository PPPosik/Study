package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {
    private final OrderRepositoryV2 target;
    private final LogTrace trace;

    public OrderRepositoryConcreteProxy(OrderRepositoryV2 target, LogTrace trace) {
        this.target = target;
        this.trace = trace;
    }

    @Override
    public void save(String itemId) {
        TraceStatus status = trace.begin("OrderRepository.request()");
        try {
            target.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
