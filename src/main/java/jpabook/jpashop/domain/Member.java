package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class Member {
    private Long id;
    private String name;
    private Address address;
    private List<Order> orders = new ArrayList<>();
}
