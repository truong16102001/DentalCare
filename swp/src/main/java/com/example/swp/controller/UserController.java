package com.example.swp.controller;

import com.example.swp.DTO.UserRegisterDTO;
import com.example.swp.entity.Role;
import com.example.swp.entity.User;
import com.example.swp.repository.RoleRepository;
import com.example.swp.service.EmailService;
import com.example.swp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.Enumeration;
import java.util.Optional;

@Controller
@RequestMapping
public class UserController {
    UserService userService;
    RoleRepository roleRepository;
    EmailService emailService;

    @Autowired
    public  UserController(UserService userService, EmailService emailService, RoleRepository roleRepository){
        this.userService = userService;
        this.emailService = emailService;
        this.roleRepository = roleRepository;
    }


    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerDTO", new UserRegisterDTO());
        return "register";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // lấy session hiện tại, không tạo mới nếu chưa có
        if (session != null) {
            session.invalidate(); // xóa toàn bộ session
        }
        return "redirect:/login"; // nên redirect thay vì trả về tên view trực tiếp
    }

    @PostMapping("/editprofile")
    public String editprofile( @RequestParam("userId") int userId,
                               @RequestParam("fullName") String fullName,
                               @RequestParam("phone") String phone,
                               @RequestParam("address") String address,
                               @RequestParam("email") String email,
                               @RequestParam("gender") String gender,
                               @RequestParam("dob") String dobStr,
                               @RequestParam("password") String password,
                               @RequestParam(value = "avatar", required = false) MultipartFile avatar,
                               HttpSession session) {

        User existingUser = userService.findByEmail(email);
        if (existingUser != null && existingUser.getUserId() != userId) {
            session.setAttribute("notification", "Email đã tồn tại");
            return "redirect:/home";
        }
        String base64Avatar = null;
        try {
            User user = userService.findByUserId(userId);

            if (avatar != null && !avatar.isEmpty()) {
                byte[] bytes = avatar.getBytes();
                base64Avatar = "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
            } else {
                // Nếu không upload ảnh mới thì lấy ảnh cũ
                base64Avatar = user.getAvatar();
            }
            if (dobStr != null && !dobStr.isEmpty()) {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dob = formatter.parse(dobStr);
                    user.setDob(dob);
                } catch (ParseException e) {
                    user.setDob(user.getDob());
                }
            }
            user.setFullName(fullName);
            user.setPhoneNumber(phone);
            user.setEmail(email);
            user.setPassword(password);
            user.setGender(gender);
            user.setAddress(address);
            user.setAvatar(base64Avatar);
            userService.save(user);
            session.setAttribute("us", user);

            session.setAttribute("notification", "Cập nhật thông tin thành công !");
        }
        catch  (IOException e) {
            e.printStackTrace();
            session.setAttribute("notification", "Có lỗi xảy ra khi xử lý ảnh.");
        }
        return "redirect:/home"; // nên redirect thay vì trả về tên view trực tiếp
    }

    @PostMapping("/changepassword")
    public String changepassword( @RequestParam("userId") int userId,
                               @RequestParam("old_pass") String old_pass,
                               @RequestParam("new_pass1") String new_pass1,
                               @RequestParam("new_pass2") String new_pass2,
                               HttpSession session) {

        User user = userService.findByUserId(userId);
        if(!user.getPassword().equals(old_pass)){
            session.setAttribute("notification", "Mật khẩu cũ sai");
            return "redirect:/home"; // nên redirect thay vì trả về tên view trực tiếp
        }
        else{
            user.setPassword(new_pass1);
            userService.save(user);
            session.setAttribute("notification", "Thay đổi mật khẩu thành công! Hãy đăng nhập lại");
            // Xóa tất cả session attributes ngoại trừ 'notification'
            Enumeration<String> attributeNames = session.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String attrName = attributeNames.nextElement();
                if (!"notification".equals(attrName)) {
                    session.removeAttribute(attrName);
                }
            }            return "redirect:/login";
        }
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerDTO") UserRegisterDTO userRegisterDTO,  HttpSession session, Model model) {

        LocalDate today = LocalDate.now();
        LocalDate dob = userRegisterDTO.getDob().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        Period age = Period.between(dob, today);
        if (age.getYears() < 3) {
            model.addAttribute("dobError", "Người dùng phải từ 3 tuổi trở lên.");
            return "register";
        }

        // Kiểm tra email và số điện thoại tồn tại
        if (userService.existsByEmail(userRegisterDTO.getEmail())) {
            model.addAttribute("emailError", "Email này đã tồn tại.");
            return "register";
        }
        if (userService.existsByPhoneNumber(userRegisterDTO.getPhoneNumber())) {
            model.addAttribute("phoneError", "Số điện thoại đã tồn tại.");
            return "register";
        }

        Optional<Role> defaultRole = roleRepository.findById(5);

        // Sinh OTP
        String randomPassword = emailService.generateOtp();
        emailService.sendEmail(userRegisterDTO.getEmail(), randomPassword);

        User user = new User();
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(randomPassword); // nên mã hóa nếu không dùng NoOp
        user.setFullName(userRegisterDTO.getFullName());
        user.setPhoneNumber(userRegisterDTO.getPhoneNumber());
        user.setAddress(userRegisterDTO.getAddress());
        user.setDob(userRegisterDTO.getDob());
        user.setIsActive(true); // chưa kích hoạt
        user.setRole(defaultRole.get());
        userService.save(user);
        session.setAttribute("notification", "Đăng ký thành công. Chúng tôi đã gửi mật khẩu đăng nhập tới email: " + user.getEmail());
        return  "redirect:/login";
    }
}
