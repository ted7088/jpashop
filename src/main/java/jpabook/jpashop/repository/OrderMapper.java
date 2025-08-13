package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface OrderMapper {
    void save(Order order);
    Order findById(Long id);
    List<Order> findAll();
    List<Order> findByMemberId(Long memberId);
    void updateStatus(@Param("orderId") Long orderId, @Param("status") String status);
    List<Order> findBySearchCriteria(OrderSearch orderSearch);
} 