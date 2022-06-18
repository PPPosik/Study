package jpabook.start1;

import jpabook.start1.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class MergeTest {
    public static void mergeTest(EntityManagerFactory emf) {
        Member member = createMember("1", "CCC", emf.createEntityManager());
        member.setUsername("DDD");
        mergeMember(member, emf.createEntityManager());
    }

    private static Member createMember(String id, String username, EntityManager em) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Member member = new Member();
        member.setId(id);
        member.setUsername(username);
        member.setAge(20);

        em.persist(member);
        transaction.commit();

        em.close();

        return member;
    }

    private static void mergeMember(Member member, EntityManager em) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        Member mergeMember = em.merge(member);
        transaction.commit();

        System.out.println("member.getUsername() = " + member.getUsername());
        System.out.println("mergeMember.getUsername() = " + mergeMember.getUsername());
        System.out.println("em.contains(member) = " + em.contains(member));
        System.out.println("em.contains(mergeMember) = " + em.contains(mergeMember));

        em.remove(mergeMember);

        em.close();
    }
}
