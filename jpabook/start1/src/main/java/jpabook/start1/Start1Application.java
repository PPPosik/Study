package jpabook.start1;

import jpabook.start1.domain.Member;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@SpringBootApplication
public class Start1Application {
    public static void main(String[] args) {
//        SpringApplication.run(Start1Application.class, args);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
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

    private static void logic(EntityManager entityManager) {
        System.out.println("call logic");

        Member member = new Member();
        member.setId("1");
        member.setUsername("AAA");
        member.setAge(20);

        entityManager.persist(member);

        member.setAge(30);

        Member findMember = entityManager.find(Member.class, "1");
        System.out.println("findMember = " + findMember);

        List<Member> members = entityManager.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size() = " + members.size());

        entityManager.remove(member);
    }
}
