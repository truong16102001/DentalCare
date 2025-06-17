package com.example.swp.service;

import com.example.swp.entity.PatientReport;
import com.example.swp.entity.ReportMedicine;

import java.util.List;
import java.util.Optional;

public interface ReportMedicineService {
    List<ReportMedicine> findByPatientReport(PatientReport patientReport);
    ReportMedicine save(ReportMedicine reportMedicine);
    void deleteByPatientReport(PatientReport report);
}
