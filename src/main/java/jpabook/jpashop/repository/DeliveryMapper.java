package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Delivery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface DeliveryMapper {
    void save(Delivery delivery);
    Delivery findById(Long id);
    void updateStatus(Long id, String status);
} 