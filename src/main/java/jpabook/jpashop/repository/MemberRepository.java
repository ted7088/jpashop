package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final MemberMapper memberMapper;

    public void save(Member member) {
        memberMapper.save(member);
    }

    public Member findOne(Long id) {
        return memberMapper.findById(id);
    }

    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    public List<Member> findByName(String name) {
        return memberMapper.findByName(name);
    }
}
