package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter @Setter @NoArgsConstructor
public class Delivery {
    private Long id;
    private Order order;
    private Address address;
    private DeliveryStatus status; // READY, COMP
}
