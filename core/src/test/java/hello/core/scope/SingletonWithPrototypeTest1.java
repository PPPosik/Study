package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {
    @Test
    public void prototypeFind() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean1 = applicationContext.getBean(PrototypeBean.class);
        bean1.addCnt();
        assertThat(bean1.getCnt()).isEqualTo(1);

        PrototypeBean bean2 = applicationContext.getBean(PrototypeBean.class);
        bean2.addCnt();
        assertThat(bean2.getCnt()).isEqualTo(1);
    }

    @Test
    public void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean client1 = applicationContext.getBean(ClientBean.class);
        int cnt1 = client1.logic();
        assertThat(cnt1).isEqualTo(1);

        ClientBean client2 = applicationContext.getBean(ClientBean.class);
        int cnt2 = client2.logic();
        assertThat(cnt2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {
        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
//        private ObjectFactory<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCnt();
            return prototypeBean.getCnt();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int cnt = 0;

        public void addCnt() {
            cnt++;
        }

        public int getCnt() {
            return cnt;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy" + this);
        }
    }
}
