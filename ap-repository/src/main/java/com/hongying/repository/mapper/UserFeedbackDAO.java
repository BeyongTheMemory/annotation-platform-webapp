package com.hongying.repository.mapper;

import com.hongying.repository.domain.UserFeedback;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFeedbackDAO {
    int deleteByPrimaryKey(Long id);

    int insert(UserFeedback record);

    int insertSelective(UserFeedback record);

    UserFeedback selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserFeedback record);

    int updateByPrimaryKey(UserFeedback record);

    UserFeedback selectLastRecordByUserIdAndType(@Param("userId") Long userId,@Param("entityCategoryType") Integer entityCategoryType);
}