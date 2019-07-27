package com.hongying.repository.mapper;

import com.hongying.repository.domain.SentenceFeedback;
import com.hongying.repository.domain.SentenceFeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SentenceFeedbackDAO {
    long countByExample(SentenceFeedbackExample example);

    int deleteByExample(SentenceFeedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SentenceFeedback record);

    int insertSelective(SentenceFeedback record);

    List<SentenceFeedback> selectByExample(SentenceFeedbackExample example);

    SentenceFeedback selectByPrimaryKey(Integer id);

    SentenceFeedback selectLastRecordByUserId(@Param("userId") Long userId);

    int updateByExampleSelective(@Param("record") SentenceFeedback record, @Param("example") SentenceFeedbackExample example);

    int updateByExample(@Param("record") SentenceFeedback record, @Param("example") SentenceFeedbackExample example);

    int updateByPrimaryKeySelective(SentenceFeedback record);

    int updateByPrimaryKey(SentenceFeedback record);
}