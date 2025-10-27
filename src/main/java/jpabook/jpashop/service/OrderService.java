package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final OrderItemMapper orderItemMapper;
    private final DeliveryMapper deliveryMapper;

    //주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        log.info("Attempting to order item: {}, current stock: {}, requested quantity: {}", 
                 item.getName(), item.getStockQuantity(), count);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);
        deliveryMapper.save(delivery);

        // 재고 수량 감소 시도 (체크와 감소를 동시에 수행)
        int updatedRows = itemMapper.decreaseStock(itemId, count);
        log.info("Stock update result: {} rows affected", updatedRows);
        
        if (updatedRows == 0) {
            throw new NotEnoughStockException("need more stock");
        }

        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);
        orderRepository.save(order);

        // OrderItem 에 저장
        orderItem.setOrder(order);
        orderItemMapper.save(orderItem);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        
        //주문취소
        if (order.getDelivery().getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        
        order.setStatus(OrderStatus.CANCEL);
        orderRepository.updateStatus(orderId, OrderStatus.CANCEL);
        
        //재고 수량 원복 for문
        for (OrderItem orderItem : order.getOrderItems()) {
            int currentStock = itemMapper.getStockQuantity(orderItem.getItem().getId());
            itemMapper.updateStockQuantity(orderItem.getItem().getId(), currentStock + orderItem.getCount());
        }
    }

    /** 주문 검색 */
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }
}
