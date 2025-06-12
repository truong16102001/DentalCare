package com.example.swp.controller;

import com.example.swp.entity.Room;
import com.example.swp.entity.Shift;
import com.example.swp.entity.User;
import com.example.swp.entity.WorkingSchedule;
import com.example.swp.service.RoomService;
import com.example.swp.service.ShiftService;
import com.example.swp.service.UserService;
import com.example.swp.service.WorkScheduleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Controller
public class ManagerController {
    @Autowired
    private WorkScheduleService workScheduleService;
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;

    @GetMapping("/work-assignment")
    public String getWorkSchedule(Model model, HttpSession session,
                                  @RequestParam(name = "assignDate", required = false) LocalDate date
                                  ) {
        if(date == null) date = LocalDate.now();
        try {
            // Lấy danh sách phân công làm việc cho hôm nay
            List<WorkingSchedule> schedules = workScheduleService.getByWorkingDate(date);
            List<Shift> shifts = shiftService.findAll();
            List<Room> rooms = roomService.findAll();
            List<User> doctors = userService.findByRoleId(3);
            // Tạo map [roomId + shiftId] -> WorkingSchedule
            Map<String, WorkingSchedule> scheduleMap = new HashMap<>();
            for (WorkingSchedule s : schedules) {
                String key = s.getRoom().getRoomId() + "_" + s.getShift().getShiftId();
                scheduleMap.put(key, s);
            }
            // Gửi sang giao diện
            model.addAttribute("scheduleMap", scheduleMap);
            model.addAttribute("assignDate", date);
            model.addAttribute("schedules", schedules != null ? schedules : new ArrayList<>());
            model.addAttribute("shifts", shifts != null ? shifts : new ArrayList<>());
            model.addAttribute("rooms", rooms != null ? rooms : new ArrayList<>());
            model.addAttribute("doctors", doctors);
            session.setAttribute("p", 7);
            return "manager/work-assignment";
        } catch (Exception e) {
            model.addAttribute("error", "Có lỗi xảy ra khi tải dữ liệu phân công làm việc.");
            return "error";
        }
    }

    @PostMapping("/work-assignment")
    public String saveWorkSchedule(Model model, HttpSession session,
                                  @RequestParam(name = "roomId", required = false) Integer roomId,
                                   @RequestParam(name = "shiftId", required = false) Integer shiftId,
                                   @RequestParam(name = "assignDate", required = false) LocalDate assignDate,
                                   @RequestParam(name = "doctorId", required = false) Integer doctorId
    ) {
        WorkingSchedule w = new WorkingSchedule();
        Room r = roomService.findById(roomId).orElse(null);
        Shift shift = shiftService.findById(shiftId).orElse(null);
        User u = userService.findByUserId(doctorId);
        w.setIsWorking(true);
        w.setDateWorking(assignDate);
        w.setRoom(r);
        w.setShift(shift);
        w.setEmployee(u);
        w.setLastUpdatedTime(new Date());
        workScheduleService.save(w);
        session.setAttribute("notification", "Phân công lịch thành công");

        return "redirect:/work-assignment?p=7&assignDate="+assignDate;
    }
}
