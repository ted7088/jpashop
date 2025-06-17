package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    void save(Category category);
    Category findById(Long id);
    List<Category> findAll();
    List<Category> findByParentId(Long parentId);
    void addItemToCategory(Long categoryId, Long itemId);
    void removeItemFromCategory(Long categoryId, Long itemId);
} 