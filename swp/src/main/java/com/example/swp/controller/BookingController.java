package com.example.swp.controller;

import com.example.swp.entity.Booking;
import com.example.swp.service.BookingService;
import com.example.swp.service.EmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    EmailService emailService;

    @GetMapping("/manage-booking")
    public String manageBooking(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date bookingDateFrom,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date bookingDateTo,
            Model model,
            HttpSession session
    ) {
        String notification = (String) session.getAttribute("notification");
        if(notification != null && !notification.isEmpty()){
            model.addAttribute("notification", notification);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Booking> listOfPage = bookingService.searchBookings(status, bookingDateFrom, bookingDateTo);
        model.addAttribute("listOfPage", listOfPage);
        model.addAttribute("status", status);
        String historyUrl = "/manage-booking";

        if (bookingDateFrom != null) {
            model.addAttribute("bookingDateFrom", dateFormat.format(bookingDateFrom));
            historyUrl += "?bookingDateFrom=" + dateFormat.format(bookingDateFrom);
        }
        if (bookingDateTo != null) {
            if(bookingDateFrom == null){
                historyUrl += "?bookingDateTo=" + dateFormat.format(bookingDateTo);
            }else{
                historyUrl += "&bookingDateTo=" + dateFormat.format(bookingDateTo);
            }
            model.addAttribute("bookingDateTo", dateFormat.format(bookingDateTo));
        }
        historyUrl+="&status="+status;
        session.setAttribute("historyUrl", historyUrl);
        model.addAttribute("p", 3);
        return "receptionist/manage-booking";
    }

    @PostMapping("/update-booking")
    public String updateBookingInfo(
            @RequestParam(value = "bookingId", required = false) Integer bookingId,
            @RequestParam(required = false) String patientName,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String note,
            @RequestParam(required = false) String status,
            HttpSession session
    ) {
        Booking booking = bookingService.findById(bookingId).orElse(null);
        if(booking != null){
            if(status.equalsIgnoreCase("Cancled")){
                emailService.sendSimpleMail(email, "Đăng ký lịch thất bại", "Xin chào!\n Chúng tôi chưa thể xác thực thông tin lịch bạn đăng ký. Hãy đăng ký lại!");
                bookingService.delete(booking);
                session.setAttribute("notification", "Từ chối lịch hẹn thành công.");
            }
            else if(status.equalsIgnoreCase("Approved")){
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                booking.setPatientName(patientName != null ? patientName : booking.getPatientName());
                booking.setDob(dob != null ? dob : booking.getDob());
                booking.setPhoneNumber(phoneNumber != null ? phoneNumber : booking.getPhoneNumber());
                booking.setEmail(email != null ? email : booking.getEmail());
                booking.setAddress(address != null ? address : booking.getAddress());
                booking.setNote(note);
                booking.setStatus(status);
                booking.setLastUpdatedTime(new Date());
                // Format ngày hẹn
                String formattedDate = sdf.format(booking.getAppointmentDate());
                String time = booking.getSlot().getStartTime().toString();
                // Gửi email xác nhận
                emailService.sendSimpleMail(
                        email,
                        "Đăng ký lịch thành công",
                        "Xin chào!\nBạn đã đăng ký dịch vụ " + booking.getService().getServiceName() +
                                " thành công.\nHẹn gặp bạn tại DentalCare vào " + formattedDate + ", lúc " + time + "."
                );

                // Lưu lại thông tin booking
                bookingService.save(booking);
            }
            else { // completed
                booking.setStatus(status);
                booking.setLastUpdatedTime(new Date());
                bookingService.save(booking);
                emailService.sendSimpleMail(
                        email,
                        "Hoàn tất dịch vụ",
                        "Xin chào!\n Cảm ơn bạn đã sử dụng dịch vụ " + booking.getService().getServiceName() +
                                " của chúng tôi.\nNếu cần hỗ trợ khác, hãy liên hệ với chung tôi"
                );
            }
            session.setAttribute("notification", "Cập nhật thành công. Kết quả xác nhận đã gửi tới email: " + booking.getEmail());
        }

        return "redirect:"+session.getAttribute("historyUrl");
    }

    @GetMapping("/update-booking")
    public String updateBookingInfo(
            @RequestParam(value = "bookingId", required = false) Integer bookingId,
            @RequestParam(required = false) String status,
            HttpSession session
    ) {
        Booking booking = bookingService.findById(bookingId).orElse(null);
        if(booking != null){
            if(status.equalsIgnoreCase("Cancled")){
                emailService.sendSimpleMail(booking.getEmail(), "Đăng ký lịch thất bại", "Xin chào!\n Chúng tôi chưa thể xác thực thông tin lịch bạn đăng ký. Hãy đăng ký lại!");
                bookingService.delete(booking);
                session.setAttribute("notification", "Từ chối lịch hẹn thành công.");
            }
            else if(status.equalsIgnoreCase("Approved")){
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                booking.setStatus(status);
                booking.setLastUpdatedTime(new Date());
                // Format ngày hẹn
                String formattedDate = sdf.format(booking.getAppointmentDate());
                String time = booking.getSlot().getStartTime().toString();
                // Gửi email xác nhận
                emailService.sendSimpleMail(
                        booking.getEmail(),
                        "Đăng ký lịch thành công",
                        "Xin chào!\nBạn đã đăng ký dịch vụ " + booking.getService().getServiceName() +
                                " thành công.\nHẹn gặp bạn tại DentalCare vào " + formattedDate + ", lúc " + time + "."
                );

                // Lưu lại thông tin booking
                bookingService.save(booking);
            }
            else { // completed
                booking.setStatus(status);
                booking.setLastUpdatedTime(new Date());
                bookingService.save(booking);
                emailService.sendSimpleMail(
                        booking.getEmail(),
                        "Hoàn tất dịch vụ",
                        "Xin chào!\n Cảm ơn bạn đã sử dụng dịch vụ " + booking.getService().getServiceName() +
                                " của chúng tôi.\nNếu cần hỗ trợ khác, hãy liên hệ với chung tôi"
                );
            }
            session.setAttribute("notification", "Cập nhật thành công. Kết quả xác nhận đã gửi tới email: " + booking.getEmail());
        }

        return "redirect:"+session.getAttribute("historyUrl");
    }

}
