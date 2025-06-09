package com.example.swp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/receptionist-manage")
    public String getReceptionistManagePage(
    ) {
        return "receptionist/receptionist-manage";
    }
}
