package com.hongying.repository.mapper;

import com.hongying.repository.domain.Sentence;
import com.hongying.repository.domain.SentenceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SentenceDAO {
    long countByExample(SentenceExample example);

    int deleteByExample(SentenceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Sentence record);

    int insertSelective(Sentence record);

    List<Sentence> selectByExample(SentenceExample example);

    Sentence selectByPrimaryKey(Long id);

    Sentence selectOneByMinId(@Param("id") Long id);

    Sentence selectByIndex(@Param("index")Integer index);

    int updateByExampleSelective(@Param("record") Sentence record, @Param("example") SentenceExample example);

    int updateByExample(@Param("record") Sentence record, @Param("example") SentenceExample example);

    int updateByPrimaryKeySelective(Sentence record);

    int updateByPrimaryKey(Sentence record);
}