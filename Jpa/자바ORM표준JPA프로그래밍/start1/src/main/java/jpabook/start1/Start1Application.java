package jpabook.start1;

import jpabook.start1.domain.Member;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import java.util.List;

@SpringBootApplication
public class Start1Application {
    public static void main(String[] args) {
//        SpringApplication.run(Start1Application.class, args);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

//        generalTest(em);
//        MergeTest.mergeTest(emf);
    }

    private static void generalTest(EntityManager em) {
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
//            logic(em);
//            JPQLLogic(em);
            testDetached(em);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    private static void testDetached(EntityManager entityManager) {
        Member member = new Member();
        member.setId("1");
        member.setUsername("AAA");
        member.setAge(20);

        // DB에 반영되지 않음
        entityManager.persist(member);
//        entityManager.detach(member);
        entityManager.clear();
    }

    private static void JPQLLogic(EntityManager entityManager) {
        Member member = new Member();
        member.setId("1");
        member.setUsername("AAA");
        member.setAge(20);

        entityManager.persist(member);
        TypedQuery<Member> query = entityManager.createQuery("select m from Member m", Member.class);
        List<Member> members = query.getResultList();

        for (Member m : members) {
            System.out.println("member : " + m.getId() + " " + m.getUsername() + " " + m.getAge());
            entityManager.remove(m);
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
        System.out.println("findMember.getAge() = " + findMember.getAge());

        List<Member> members = entityManager.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size() = " + members.size());

        entityManager.remove(member);
    }
}
