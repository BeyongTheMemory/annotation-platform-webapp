package com.hongying.repository.mapper;

import com.hongying.repository.domain.EntityCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityCategoryDAO {
    int deleteByPrimaryKey(Long id);

    int insert(EntityCategory record);

    int insertSelective(EntityCategory record);

    EntityCategory selectByPrimaryKey(Long id);

    EntityCategory selectOneByMinIdAndType(@Param("id") Long id,@Param("type") Integer type);

    int updateByPrimaryKeySelective(EntityCategory record);

    int updateByPrimaryKey(EntityCategory record);
}