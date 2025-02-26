package com.lmt.ecom.portal.service;

import com.lmt.ecom.model.*;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

public interface TourService {
    Partners getPartnersInfo(Integer id);
    List<Partners> getPartners(Map<String, Object> param);
    List<Entrepreneurs> getEntrepreneurs();
    List<TourTime> getAllTour(Integer id,String date);
    List<AvailableTime> getAvailableTimes(Integer id, String date);

    boolean createAppointment(Integer partnerId, Integer entrepreneurId, Long timestamp);

    boolean cancelAppointment(Integer id);
}
