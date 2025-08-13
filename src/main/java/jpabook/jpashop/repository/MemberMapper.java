package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MemberMapper {
    void save(Member member);
    Member findById(Long id);
    List<Member> findByName(String name);
    List<Member> findAll();
} 