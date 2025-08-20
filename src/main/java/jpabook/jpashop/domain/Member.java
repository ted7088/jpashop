package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class Member {
    private Long id;
    private String name;       // 로그인 아이디로 사용
    private String password;
    private Address address;
    private List<Order> orders = new ArrayList<>();
}
