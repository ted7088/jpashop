-- 기존 데이터 삭제
DELETE FROM category_item;
DELETE FROM category;
DELETE FROM order_item;
DELETE FROM orders;
DELETE FROM delivery;
DELETE FROM item;
DELETE FROM member;

-- 회원 데이터 (비밀번호는 BCrypt로 암호화된 'password'입니다)
INSERT INTO member (member_id, name, password, city, street, zipcode) VALUES 
(1, 'user1', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'Seoul', 'Gangnam', '12345'),
(2, 'user2', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'Busan', 'Haeundae', '23456'),
(3, 'user3', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'Incheon', 'Yeonsu', '34567');

-- 상품 데이터
-- Book
INSERT INTO item (item_id, dtype, name, price, stock_quantity, author, isbn) VALUES 
(1, 'Book', '스프링 부트와 JPA', 35000, 100, '김영한', '12345'),
(2, 'Book', '리액트 프로그래밍', 45000, 200, '이일민', '23456'),
(3, 'Book', 'MyBatis 프로그래밍', 40000, 150, '조민석', '34567');

-- Album
INSERT INTO item (item_id, dtype, name, price, stock_quantity, artist, etc) VALUES 
(4, 'Album', 'Fine', 20000, 50, 'IU', 'Korean Pop'),
(5, 'Album', 'Butter', 25000, 100, 'BTS', 'K-Pop');

-- Movie
INSERT INTO item (item_id, dtype, name, price, stock_quantity, director, actor) VALUES 
(6, 'Movie', '기생충', 30000, 300, '봉준호', '송강호'),
(7, 'Movie', '아바타', 35000, 400, '제임스 카메론', '샘 워싱턴');

-- 배송 정보
INSERT INTO delivery (delivery_id, city, street, zipcode, status) VALUES 
(1, 'Seoul', 'Gangnam', '12345', 'READY'),
(2, 'Busan', 'Haeundae', '23456', 'READY'),
(3, 'Incheon', 'Yeonsu', '34567', 'READY');

-- 주문 데이터
INSERT INTO orders (order_id, member_id, delivery_id, order_date, status) VALUES 
(1, 1, 1, CURRENT_TIMESTAMP(), 'ORDER'),
(2, 2, 2, CURRENT_TIMESTAMP(), 'ORDER'),
(3, 3, 3, CURRENT_TIMESTAMP(), 'ORDER');

-- 주문상품 데이터
INSERT INTO order_item (order_item_id, order_id, item_id, order_price, count) VALUES 
(1, 1, 1, 35000, 2),  -- user1이 스프링 부트와 JPA 2권 주문
(2, 1, 4, 20000, 1),  -- user1이 IU 앨범 1개 주문
(3, 2, 2, 45000, 3),  -- user2가 리액트 프로그래밍 3권 주문
(4, 3, 6, 30000, 1);  -- user3이 기생충 1개 주문

-- 카테고리 데이터
INSERT INTO category (category_id, name) VALUES 
(1, '도서'),
(2, '음반'),
(3, '영화');

-- 카테고리-상품 연결
INSERT INTO category_item (category_id, item_id) VALUES 
(1, 1), -- 도서 카테고리에 스프링 부트와 JPA 연결
(1, 2), -- 도서 카테고리에 리액트 프로그래밍 연결
(1, 3), -- 도서 카테고리에 MyBatis 프로그래밍 연결
(2, 4), -- 음반 카테고리에 IU 앨범 연결
(2, 5), -- 음반 카테고리에 BTS 앨범 연결
(3, 6), -- 영화 카테고리에 기생충 연결
(3, 7); -- 영화 카테고리에 아바타 연결 