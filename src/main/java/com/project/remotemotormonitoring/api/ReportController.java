package com.project.remotemotormonitoring.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.remotemotormonitoring.api.dto.MonthlyReportRequest;
import com.project.remotemotormonitoring.api.dto.ReportRequest;
import com.project.remotemotormonitoring.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/report")
@CrossOrigin

public class ReportController {
    private final ReportService reportService;
    String reportName;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/weekly_report")
    @Operation(summary = "get weekly report for each motor")
//    @PreAuthorize("hasAnyRole('CFO','Manager','Clerk')")
    public @ResponseBody
    ResponseEntity<byte[]> weeklyReport(@RequestParam String motorName,@RequestParam String fromDate,
                                        @RequestParam String toDate
                                        ) throws IOException, JRException, SQLException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String[] dateRange = objectMapper.readValue(request.getRange(), String[].class);
//
//        String fromDateStr = dateRange[0];
//        String toDateStr = dateRange[1];
        Map<String, Object> params = new HashMap<>();

        LocalDate localFrom = LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate localTo = LocalDate.parse(toDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        params.put("motor_name", motorName);
        params.put("to_date", Date.valueOf(localTo));
        params.put("from_date", Date.valueOf(localFrom));
        String fileName = "sensorReport";
        reportName = "sensorReport";
        byte[] bytes = reportService.generateReport(reportName, params);
        return ResponseEntity.ok().header("Content-Type", "application/pdf; charset=UTF-8")
                .header("Content-Disposition", "inline; filename=\"" + fileName + ".pdf\"")
                .body(bytes);
    }

    @GetMapping("/monthly_report")
    @Operation(summary = "get monthly report for each motor")
//    @PreAuthorize("hasAnyRole('CFO','Manager','Clerk')")
    public @ResponseBody
    ResponseEntity<byte[]> monthlyReport(@RequestParam String motorName, @RequestParam String month) throws IOException, JRException, SQLException {

        String newDate = month + "-01";

        Map<String, Object> params = new HashMap<>();

        LocalDate localTo = LocalDate.parse(newDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        params.put("motor_name", motorName);
        params.put("month_date", Date.valueOf(localTo));

        String fileName = "monthlyReport";
        reportName = "monthlyReport";
        byte[] bytes = reportService.generateReport(reportName, params);
        return ResponseEntity.ok().header("Content-Type", "application/pdf; charset=UTF-8")
                .header("Content-Disposition", "inline; filename=\"" + fileName + ".pdf\"")
                .body(bytes);
    }

}
