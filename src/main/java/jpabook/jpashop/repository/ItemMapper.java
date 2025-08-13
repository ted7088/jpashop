package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ItemMapper {
    void save(Item item);
    Item findById(Long id);
    List<Item> findAll();
    void update(Item item);
    void delete(Long id);
    List<Item> findByName(String name);
    void updateStockQuantity(Long id, int stockQuantity);
    int decreaseStock(Long id, int quantity);
    int getStockQuantity(Long id);
} 