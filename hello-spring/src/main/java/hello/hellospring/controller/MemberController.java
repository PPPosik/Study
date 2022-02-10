package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    // field DI
//    @Autowired private MemberService memberService;
    private final MemberService memberService;

    // constructor DI
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // setter DI
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }
}
