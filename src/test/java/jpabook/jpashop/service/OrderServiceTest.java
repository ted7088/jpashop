package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemMapper;
import jpabook.jpashop.repository.MemberMapper;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //Given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고
        int orderCount = 2;
        //When
        Long orderId = orderService.order(member.getId(), item.getId(),
                orderCount);
        //Then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문시 상태는 ORDER");
        assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확 해야 한다.");
        assertEquals(10000 * 2, getOrder.getTotalPrice(), "주문 가격은 가격 * 수량이 다.");
        assertEquals(8, item.getStockQuantity(), "주문 수량만큼 재고가 줄어야 한다.");
    }

    @Test
    public void 상품주문_재고수량초과() {
    //...
    }

    @Test
    public void 주문취소() {
    //...
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        memberMapper.save(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        book.setDtype("Book");
        itemMapper.save(book);
        return book;
    }
}