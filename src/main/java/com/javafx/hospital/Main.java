package com.javafx.hospital;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws JRException {
        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile("src/main/resources/Jasper/Blank_A4.jasper");
        Map<String, Object> fullname = new HashMap<String, Object>();

        fullname.put("name", Users.fullname[0]);
        fullname.put("Idade", 20);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, fullname, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfFile(jasperPrint, "/media/alpha3/Armazenamento/output.pdf");

    }
}