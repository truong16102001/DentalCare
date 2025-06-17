package com.example.swp.controller;

import com.example.swp.entity.Medicine;
import com.example.swp.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MedicineController {
    @Autowired
    MedicineService medicineService;

    @GetMapping("/search-medicine")
    @ResponseBody
    public List<Medicine> searchMedicine(@RequestParam("name") String name) {
        return medicineService.searchByName(name); // Ví dụ: findByMedicineNameContainingIgnoreCase
    }

    @GetMapping("/medicines")
    @ResponseBody
    public List<Medicine> getAllMedicines() {
        return medicineService.findAll();
    }
}
