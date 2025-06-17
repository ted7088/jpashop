package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final ItemMapper itemMapper;

    public void save(Item item) {
        if (item.getId() == null) {
            itemMapper.save(item);
        } else {
            itemMapper.update(item);
        }
    }

    public Item findOne(Long id) {
        return itemMapper.findById(id);
    }

    public List<Item> findAll() {
        return itemMapper.findAll();
    }
    
    public void updateStock(Long itemId, int stockQuantity) {
        Item item = findOne(itemId);
        item.setStockQuantity(stockQuantity);
        itemMapper.update(item);
    }
}
