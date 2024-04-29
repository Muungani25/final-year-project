package com.project.remotemotormonitoring.service;

import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.Map;

public interface ReportService {
    byte[] generateReport(String reportName, Map<String, Object> params) throws JRException, SQLException;
}
