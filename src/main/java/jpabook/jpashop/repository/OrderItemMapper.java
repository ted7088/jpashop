package jpabook.jpashop.repository;

import jpabook.jpashop.domain.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    void save(OrderItem orderItem);
    OrderItem findById(Long id);
    List<OrderItem> findByOrderId(Long orderId);
    void deleteByOrderId(Long orderId);
} 