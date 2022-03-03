package hello.core.lifecycle;

import lombok.Setter;

@Setter
public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("call constructor : " + url);
    }

    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String msg) {
        System.out.println("call : " + url + " message : " + msg);
    }

    public void disconnect() {
        System.out.println("disconnect :  " + url);
    }

    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 메시지");
    }

    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
