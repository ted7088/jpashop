package jpabook.jpashop.repository;

import jpabook.jpashop.domain.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface OrderItemMapper {
    void save(OrderItem orderItem);
    OrderItem findById(Long id);
    List<OrderItem> findByOrderId(Long orderId);
    void deleteByOrderId(Long orderId);
} 