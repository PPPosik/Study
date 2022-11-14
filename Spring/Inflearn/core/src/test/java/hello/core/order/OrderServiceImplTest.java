package hello.core.order;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {

    @Test
    public void createOrder() {
        MemberRepository memberRepository = new MemoryMemberRepository();
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new RateDiscountPolicy());

        memberRepository.save(new Member(1L, "AA", Grade.VIP));

        Assertions.assertThat(orderService.createOrder(1L, "AAA", 10000).getDiscountPrice()).isEqualTo(1000);
    }

}