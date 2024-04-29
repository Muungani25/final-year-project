package com.project.remotemotormonitoring.service;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
    private final DataSource dataSource;
    @Override
    public byte[] generateReport(String reportName, Map<String, Object> params) throws JRException, SQLException {
        byte[] bytes;
        System.out.println("report name............... " + reportName);
        InputStream jasperStream = this.getClass().getResourceAsStream("/templates/" +
                reportName + ".jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);
        Connection con = dataSource.getConnection();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, con);
        con.close();
        bytes = JasperExportManager.exportReportToPdf(jasperPrint);
        return bytes;
    }
}
