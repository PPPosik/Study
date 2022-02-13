package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository repository;

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("AAA");

        // when
        Long saveId = memberService.join(member);

        // then
        assertThat(member.getName()).isEqualTo(repository.findById(saveId).get().getName());
    }

    @Test
    void duplicateJoin() {
        Member member = new Member();
        member.setName("AAA");

        memberService.join(member);
        assertThrows(IllegalStateException.class, () -> memberService.join(member));

//        try {
//            memberService.join(member);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("already exists");
//        }
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}