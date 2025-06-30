package com.example.swp.controller;

import com.example.swp.entity.Chart;
import com.example.swp.entity.DateObject;
import com.example.swp.service.BookingService;
import com.example.swp.service.DateService;
import com.example.swp.service.DentalCareService;
import com.example.swp.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class DashboardController {
    @Autowired
    UserService userService;
    @Autowired
    DentalCareService dentalCareService;
    @Autowired
    DateService dateService;

    @Autowired
    BookingService bookingService;

    @Autowired
    private ObjectMapper objectMapper;


    @GetMapping("/receptionist-manage")
    public String getReceptionistManagePage(
    ) {
        return "receptionist/receptionist-manage";
    }

    @GetMapping("/manager-manage")
    public String getManagerManagePage(
            @RequestParam(name = "start", required = false) String start_raw,
            @RequestParam(name = "end", required = false) String end_raw,
            Model model) throws JsonProcessingException {
        int countCustomer = (int) userService.countByRoleId(5);
        int countService = (int) dentalCareService.getTotalService();

        DateObject date = dateService.get7day();
        String start = date.getStart().toString();
        String end = date.getEnd().toString();
        if(start_raw != null){
            start = start_raw;
            end = end_raw;
        }
        int numberOfDay = dateService.countDayByStartEnd(start, end);
        // set chart revenue
        List<Chart> listChartRevenueArea = dentalCareService.getChartRevenueArea(start, numberOfDay);
        int maxListChartRevenueArea = -1;
        for (Chart o : listChartRevenueArea) {
            if (o.getValue() > maxListChartRevenueArea) {
                maxListChartRevenueArea = o.getValue();
            }
        }
        maxListChartRevenueArea = (maxListChartRevenueArea / 1000000 + 2) * 1000000;

        List<String> listStatusBooking = new ArrayList<>();
        listStatusBooking.add("Pending");
        listStatusBooking.add("Processing");
        listStatusBooking.add("Completed");


        int totalBookingByStatus1 = (int) bookingService.gettotalBookingByStatus("Pending", start, numberOfDay);
        int totalBookingByStatus2 = (int) bookingService.gettotalBookingByStatus("Processing", start, numberOfDay);
        int totalBookingByStatus3 = (int) bookingService.gettotalBookingByStatus("Completed", start, numberOfDay);


        List<Integer> pieDataList = Arrays.asList(
                totalBookingByStatus1,
                totalBookingByStatus2,
                totalBookingByStatus3
        );
        model.addAttribute("pieDataList", pieDataList);

        String pieLabelsJson = objectMapper.writeValueAsString(listStatusBooking);
        String pieDataJson = objectMapper.writeValueAsString(pieDataList);

        model.addAttribute("pieLabelsJson", pieLabelsJson);
        model.addAttribute("pieDataJson", pieDataJson);

        model.addAttribute("listStatusBooking", listStatusBooking);
        model.addAttribute("totalBooking1", totalBookingByStatus1);
        model.addAttribute("totalBooking2", totalBookingByStatus2);
        model.addAttribute("totalBooking3", totalBookingByStatus3);
        model.addAttribute("noCustomer", countCustomer);
        model.addAttribute("noService", countService);

        model.addAttribute("listChartRevenueArea", listChartRevenueArea);
        model.addAttribute("maxListChartRevenueArea", maxListChartRevenueArea);

        model.addAttribute("start", start);
        model.addAttribute("end", end);
        model.addAttribute("p", 0);

        return "manager/manager-manage";
    }

    @GetMapping("/admin-manage")
    public String getAdminManagePage(
    ) {
        return "admin/admin-manage";
    }

    @GetMapping("/doctor-manage")
    public String getDoctorManagePage(
    ) {
        return "doctor/doctor-manage";
    }


}
