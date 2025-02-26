package com.lmt.ecom.mapper;

import com.lmt.ecom.model.Entrepreneurs;
import com.lmt.ecom.model.EntrepreneursExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EntrepreneursMapper {
    long countByExample(EntrepreneursExample example);

    int deleteByExample(EntrepreneursExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Entrepreneurs record);

    int insertSelective(Entrepreneurs record);

    List<Entrepreneurs> selectByExampleWithBLOBs(EntrepreneursExample example);

    List<Entrepreneurs> selectByExample(EntrepreneursExample example);

    Entrepreneurs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Entrepreneurs record, @Param("example") EntrepreneursExample example);

    int updateByExampleWithBLOBs(@Param("record") Entrepreneurs record, @Param("example") EntrepreneursExample example);

    int updateByExample(@Param("record") Entrepreneurs record, @Param("example") EntrepreneursExample example);

    int updateByPrimaryKeySelective(Entrepreneurs record);

    int updateByPrimaryKeyWithBLOBs(Entrepreneurs record);

    int updateByPrimaryKey(Entrepreneurs record);
}