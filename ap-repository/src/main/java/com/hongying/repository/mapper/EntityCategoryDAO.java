package com.hongying.repository.mapper;

import com.hongying.repository.domain.EntityCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityCategoryDAO {
    int deleteByPrimaryKey(Long id);

    int insert(EntityCategory record);

    int insertSelective(EntityCategory record);

    EntityCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EntityCategory record);

    int updateByPrimaryKey(EntityCategory record);
}