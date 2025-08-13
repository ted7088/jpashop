package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Getter
public class OrderRepository {

    private final OrderMapper orderMapper;

    public void save(Order order) {
        orderMapper.save(order);
    }

    public Order findOne(Long id) {
        return orderMapper.findById(id);
    }

    public List<Order> findAllByString(OrderSearch orderSearch) {
        return orderMapper.findBySearchCriteria(orderSearch);
    }

    public void updateStatus(Long orderId, OrderStatus status) {
        orderMapper.updateStatus(orderId, status.name());
    }
}
