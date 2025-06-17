package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public abstract class Item {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String dtype;
    private List<Category> categories = new ArrayList<>();

    //재고수량을 늘려주는 로직
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    //재고 수량 감소
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
