package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CategoryMapper {

    //저장
    void save(Category category);
    Category findById(Long id);

    //찾기
    List<Category> findAll();
    List<Category> findByParentId(Long parentId);
    void addItemToCategory(Long categoryId, Long itemId);
    void removeItemFromCategory(Long categoryId, Long itemId);
} 