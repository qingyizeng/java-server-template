package com.lmt.ecom.model;

import lombok.Data;

@Data
public class AvailableTime {
    String time; //显示的时间段
    Long timestamp; //开始时间戳

    public AvailableTime(String timeSlot, long time) {
        this.time = timeSlot;
        this.timestamp = time;
    }
}
