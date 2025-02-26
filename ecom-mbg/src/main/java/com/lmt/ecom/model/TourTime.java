package com.lmt.ecom.model;

import lombok.Data;

import java.util.Date;

@Data
public class TourTime {
    Integer id;
    String time;
    Integer partnerId;
    String partnerName;
    Integer entrepreneurId;
    String entrepreneurName;
    Date tourTime;
}
