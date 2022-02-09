package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new MemoryMemberRepository();
        memberService = new MemberService(repository);
    }

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("AAA");

        // when
        Long saveId = memberService.join(member);

        // then
        assertThat(member.getName()).isEqualTo(memberService.findOne(saveId).get().getName());
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