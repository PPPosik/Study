package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Long join(Member member) {
        validateDuplicateMember(member);

        Long id = repository.save(member).getId();
        return id;
    }

    private void validateDuplicateMember(Member member) {
        repository.findById(member.getId()).ifPresent(m -> {
            throw new IllegalStateException("already exists");
        });
    }

    public List<Member> findMembers() {
        return repository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return repository.findById(memberId);
    }
}
