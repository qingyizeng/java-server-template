package com.lmt.ecom.mapper;

import com.lmt.ecom.model.Appointments;
import com.lmt.ecom.model.AppointmentsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppointmentsMapper {
    long countByExample(AppointmentsExample example);

    int deleteByExample(AppointmentsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Appointments record);

    int insertSelective(Appointments record);

    List<Appointments> selectByExample(AppointmentsExample example);

    Appointments selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Appointments record, @Param("example") AppointmentsExample example);

    int updateByExample(@Param("record") Appointments record, @Param("example") AppointmentsExample example);

    int updateByPrimaryKeySelective(Appointments record);

    int updateByPrimaryKey(Appointments record);
}