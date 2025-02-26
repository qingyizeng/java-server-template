package com.lmt.ecom.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.lmt.ecom.mapper.AppointmentsMapper;
import com.lmt.ecom.mapper.EntrepreneursMapper;
import com.lmt.ecom.mapper.PartnersMapper;
import com.lmt.ecom.mapper.ProductMapper;
import com.lmt.ecom.model.*;
import com.lmt.ecom.portal.service.TourService;
import com.lmt.ecom.portal.util.DateUtil;
import com.lmt.ecom.portal.util.Utils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PartnersServiceImpl implements TourService {
    @Autowired
    private PartnersMapper partnersMapper;
    @Autowired
    private EntrepreneursMapper entrepreneursMapper;
    @Autowired
    private AppointmentsMapper appointmentsMapper;

    @Override
    public Partners getPartnersInfo(Integer id) {
        Partners partner = partnersMapper.selectByPrimaryKey(id);
        return partner;
    }

    @Override
    public List<Partners> getPartners(Map<String, Object> param) {
        int pageNum = Utils.toInt(param.get("pageNum"));
        int pageSize = Utils.toInt(param.get("pageSize"));
        PageHelper.startPage(pageNum, pageSize);
        PartnersExample example = new PartnersExample();
        example.createCriteria().andIdIsNotNull();
        List<Partners> list = partnersMapper.selectByExample(example);
        return list;
    }


    @Override
    public List<Entrepreneurs> getEntrepreneurs() {
        EntrepreneursExample example = new EntrepreneursExample();
        example.createCriteria().andIdIsNotNull();
        List<Entrepreneurs> list = entrepreneursMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<TourTime> getAllTour(Integer id, String date) {
        ArrayList<String> statusList = new ArrayList<String>();
        statusList.add("confirmed");
        List<Appointments> appointments = getDateTour(id, date,statusList);
        //把List<Appointments> 转换成List<TourTime>
        List<TourTime> tourTimes = new ArrayList<>();
        for (Appointments appointment : appointments) {
            Entrepreneurs entrepreneurs = entrepreneursMapper.selectByPrimaryKey(appointment.getEntrepreneurId());
            TourTime tourTime = new TourTime();
            tourTime.setId(appointment.getId());
            tourTime.setPartnerId(appointment.getPartnerId());
            tourTime.setEntrepreneurId(entrepreneurs.getId());
            tourTime.setEntrepreneurName(entrepreneurs.getName());
            tourTime.setTourTime(appointment.getAppointmentTime());
            //把appointment.getAppointmentTime() 转换成 HH:mm
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            tourTime.setTime(sdf.format(appointment.getAppointmentTime()));
            tourTimes.add(tourTime);
        }
        //把tourTime按TourTime升序排序
        tourTimes.sort((o1, o2) -> o1.getTourTime().compareTo(o2.getTourTime()));
        return tourTimes;
    }

    private List<Appointments> getDateTour(Integer id, String date,ArrayList<String> statusList) {
        Date startTime = DateUtil.parseByYmdhms(date + " 00:00:00");
        Date endTime = DateUtil.parseByYmdhms(date + " 23:59:59");
        AppointmentsExample example = new AppointmentsExample();
        example.createCriteria().andPartnerIdEqualTo(id).andStatusIn(statusList)
                .andAppointmentTimeBetween(startTime, endTime);
        List<Appointments> appointments = appointmentsMapper.selectByExample(example);
        return appointments;
    }

    @Override
    public boolean createAppointment(Integer partnerId, Integer entrepreneurId, Long timestamp) {
        Date dateTime = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(dateTime);
        // 1. 判断是否已经在这个日期内预约过
        Date startTime = DateUtil.parseByYmdhms(date + " 00:00:00");
        Date endTime = DateUtil.parseByYmdhms(date + " 23:59:59");
        AppointmentsExample example = new AppointmentsExample();
        example.createCriteria().andPartnerIdEqualTo(partnerId).andEntrepreneurIdEqualTo(entrepreneurId)
                .andAppointmentTimeBetween(startTime, endTime).andStatusEqualTo("confirmed");
        List<Appointments> existAppointments = appointmentsMapper.selectByExample(example);
        if (existAppointments.size() > 0) {
            return false;
        }
        Appointments appointments = new Appointments();
        appointments.setAppointmentTime(new Date(timestamp));
        appointments.setPartnerId(partnerId);
        appointments.setEntrepreneurId(entrepreneurId);
        appointments.setStatus("confirmed");
        int count = appointmentsMapper.insert(appointments);
        return count > 0;
    }

    @Override
    public List<AvailableTime> getAvailableTimes(Integer id, String date) {
        // 1. 获取合伙人的可用时间
        Partners partner = getPartnersInfo(id);
        Gson gson = new Gson();
        List<String> timeList = gson.fromJson(partner.getAvailableTimes(), List.class);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        // Get the current date (without the time part)
        List<AvailableTime> timeAvailableTimes = new ArrayList<AvailableTime>(); // List
        for (String timeSlot : timeList) {
            // Get the start time (before the " - ")
            String startTime = timeSlot.split(" - ")[0];
            // Combine the current date with the start time
            String dateTimeString = date + " " + startTime;
            // Parse the combined date and time
            try {
                Date dateTime = dateFormat.parse(dateTimeString);
                timeAvailableTimes.add(new AvailableTime(timeSlot, dateTime.getTime()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        // 2. 获取当天所有的已预约时间段
        ArrayList<String> statusList = new ArrayList<String>();
        statusList.add("confirmed");
        statusList.add("cancelled");
        List<Appointments> appointments = getDateTour(id, date,statusList);
        List<Long> bookedTimes = appointments.stream().map((app) -> app.getAppointmentTime().getTime()).collect(Collectors.toList());

        // 3. 从可用时间中排除已预约的时间段
        List<AvailableTime> availableForToday = timeAvailableTimes.stream()
                .filter(time -> !bookedTimes.contains(time.getTimestamp()))
                .collect(Collectors.toList());

        return availableForToday;
    }

    @Override
    public boolean cancelAppointment(Integer id) {
        AppointmentsExample example = new AppointmentsExample();
        example.createCriteria().andIdEqualTo(id);
        Appointments appointments = new Appointments();
        appointments.setStatus("cancelled");
        int count = appointmentsMapper.updateByExampleSelective(appointments, example);
        return count > 0;
    }
}
