package com.example.swp.controller;

import org.springframework.ui.Model;
import com.example.swp.entity.Service;
import com.example.swp.service.DentalCareService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ServiceController {
    @Autowired
    DentalCareService dentalCareService;

    @GetMapping("/service")
    public String getServices(
            @RequestParam(required = false) String key,
            @RequestParam(required = false) String value,
            @RequestParam(required = false) String type,
            @RequestParam(defaultValue = "1") int index,
            Model model,
            HttpSession session
    ) {
        // Lấy danh sách dịch vụ từ service layer, có thể thêm lọc, tìm kiếm, sắp xếp
        List<Service> list = dentalCareService.getServices(key, value, type, index);

        int total = dentalCareService.getTotalPages(key); // Tính số trang tổng

        model.addAttribute("list", list);
        model.addAttribute("pageIndex", index);
        model.addAttribute("endPage", total);

        // Truyền lại các thông số tìm kiếm/sắp xếp để sử dụng trong template
        model.addAttribute("key", key);
        model.addAttribute("value", value);
        model.addAttribute("type", type);
        model.addAttribute("historyKey", key != null ? "&key=" + key : "");
        model.addAttribute("historyValue", value != null ? "&value=" + value : "");
        model.addAttribute("historyType", type != null ? "&type=" + type : "");
        model.addAttribute("active", 2);
        return "service"; // Tên file thymeleaf ở src/main/resources/templates/service/list.html
    }
}
