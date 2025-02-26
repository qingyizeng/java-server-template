package com.lmt.ecom.mapper;

import com.lmt.ecom.model.Partners;
import com.lmt.ecom.model.PartnersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PartnersMapper {
    long countByExample(PartnersExample example);

    int deleteByExample(PartnersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Partners record);

    int insertSelective(Partners record);

    List<Partners> selectByExampleWithBLOBs(PartnersExample example);

    List<Partners> selectByExample(PartnersExample example);

    Partners selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Partners record, @Param("example") PartnersExample example);

    int updateByExampleWithBLOBs(@Param("record") Partners record, @Param("example") PartnersExample example);

    int updateByExample(@Param("record") Partners record, @Param("example") PartnersExample example);

    int updateByPrimaryKeySelective(Partners record);

    int updateByPrimaryKeyWithBLOBs(Partners record);

    int updateByPrimaryKey(Partners record);
}