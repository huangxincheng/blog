package com.limaila.blog.tops.dao;

import com.limaila.blog.tops.entity.TopsApiAccount;
import com.limaila.blog.tops.entity.TopsApiAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopsApiAccountMapper {
    int countByExample(TopsApiAccountExample example);

    int deleteByExample(TopsApiAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TopsApiAccount record);

    int insertSelective(TopsApiAccount record);

    List<TopsApiAccount> selectByExample(TopsApiAccountExample example);

    TopsApiAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TopsApiAccount record, @Param("example") TopsApiAccountExample example);

    int updateByExample(@Param("record") TopsApiAccount record, @Param("example") TopsApiAccountExample example);

    int updateByPrimaryKeySelective(TopsApiAccount record);

    int updateByPrimaryKey(TopsApiAccount record);
}