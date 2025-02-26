package com.lmt.ecom.portal.controller;

import com.lmt.ecom.common.api.CommonPage;
import com.lmt.ecom.common.api.CommonResult;
import com.lmt.ecom.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import com.lmt.ecom.portal.service.TourService;


@Controller
@RequestMapping("/tour")
public class TourController {

    @Autowired
    TourService tourService;

    @RequestMapping(value = "/getPartners", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getPartners(@RequestBody Map<String, Object> param) {
        List<Partners> result = tourService.getPartners(param);
        return CommonResult.success(CommonPage.restPage(result));
    }

    @RequestMapping(value = "/getAllTour", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getAllTour(@RequestBody Map<String, Object> param) {
        Integer id = (Integer) param.get("partnerId");
        String date = (String) param.get("date");
        List<TourTime> result = tourService.getAllTour(id,date);
        return CommonResult.success(result);
    }


    @RequestMapping(value = "/getEntrepreneurs", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getEntrepreneurs() {
        List<Entrepreneurs> result = tourService.getEntrepreneurs();
        return CommonResult.success(result);
    }


    @RequestMapping(value = "/createAppointment", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createAppointment(@RequestBody Map<String, Object> param) {
        Integer partnerId = (Integer) param.get("partnerId");
        Integer entrepreneurId = (Integer) param.get("entrepreneurId");
        Long timestamp = (Long) param.get("timestamp");
        boolean result = tourService.createAppointment(partnerId, entrepreneurId, timestamp);
        if(result){
            return CommonResult.success("预约成功");
        }else{
            return CommonResult.failed("预约失败");
        }
    }

    @RequestMapping(value = "/cancelAppointment", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult cancelAppointment(@RequestBody Map<String, Object> param) {
        Integer id = (Integer) param.get("id");
        boolean result = tourService.cancelAppointment(id);
        if(result){
            return CommonResult.success("取消预约成功");
        }else{
            return CommonResult.failed("取消预约失败");
        }
    }

    /**
     * 获取合伙人当天可用的时间段
     * @return 可用的时间段
     */
    @RequestMapping(value = "/getAvailableTimes", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getAvailableTimes(@RequestBody Map<String, Object> param) {

        List<AvailableTime> result = tourService.getAvailableTimes((Integer) param.get("partnerId"), (String) param.get("date"));

        return CommonResult.success(result);
    }




}
