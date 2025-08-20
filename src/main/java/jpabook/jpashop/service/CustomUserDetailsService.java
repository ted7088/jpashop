package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Member> members = memberRepository.findByName(username);

        if (members.isEmpty()) {
            throw new UsernameNotFoundException("사용자 없음: " + username);
        }

        Member member = members.get(0); // 첫 번째 회원 선택

        return User.builder()
                .username(member.getName())
                .password(member.getPassword()) // 반드시 암호화된 값이어야 함
                .roles("USER")
                .build();
    }
}