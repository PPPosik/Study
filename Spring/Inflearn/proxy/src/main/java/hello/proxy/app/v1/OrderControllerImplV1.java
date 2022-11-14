package hello.proxy.app.v1;

public class OrderControllerImplV1 implements OrderControllerV1 {
    private final OrderServiceV1 orderService;

    public OrderControllerImplV1(OrderServiceV1 orderService) {
        this.orderService = orderService;
    }

    @Override
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @Override
    public String noLog() {
        return "ok";
    }
}
