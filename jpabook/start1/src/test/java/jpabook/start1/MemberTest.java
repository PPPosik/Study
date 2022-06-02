package jpabook.start1;

import jpabook.start1.domain.Member;
import jpabook.start1.domain.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.*;

@Slf4j
@SpringBootTest
public class MemberTest {
    @Autowired
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void basicTest() {
        Member member = new Member();
        member.setId("1");
        member.setUsername("AAA");
        member.setAge(20);

        em.persist(member);

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            logic(em);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    private void logic(EntityManager entityManager) {
        log.info("call logic");
    }
}
