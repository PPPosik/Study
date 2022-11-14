package repository;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();

        member.setName("AAA");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findById() {
        Member member = new Member();

        member.setName("AAA");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        Member member2 = new Member();

        member1.setName("BBB");
        member2.setName("CCC");

        repository.save(member1);
        repository.save(member2);

        Member result1 = repository.findByName("BBB").get();
        Member result2 = repository.findByName("CCC").get();
//        Member result3 = repository.findByName("123").get();

        assertThat(result1).isEqualTo(member1);
        assertThat(result2).isEqualTo(member2);
//        assertThat(result3).isEqualTo(null);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        Member member2 = new Member();
        Member member3 = new Member();

        member1.setName("AAA");
        member2.setName("BBB");
        member3.setName("CCC");

        repository.save(member1);
        repository.save(member2);
        repository.save(member3);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(3);
    }
}
