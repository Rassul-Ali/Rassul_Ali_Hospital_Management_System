package com.javafx.hospital;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws JRException {
        /*JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile("src/main/resources/Jasper/Blank_A4.jasper");
        Map<String, Object> fullname = new HashMap<String, Object>();

        fullname.put("name", Users.fullname[0]);
        fullname.put("Idade", 20);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, fullname, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfFile(jasperPrint, "/media/alpha3/Armazenamento/output.pdf");*/

        File url = new File("src/main/java/com/javafx/hospital/DBfiles/URL.dat");
        File user = new File("src/main/java/com/javafx/hospital/DBfiles/USER.dat");
        File password = new File("src/main/java/com/javafx/hospital/DBfiles/PASSWORD.dat");

        RandomAccessFile urlDD = null;
        RandomAccessFile userDD = null;
        RandomAccessFile passwordDD = null;
        String u,u1,p;
        try {
            urlDD = new RandomAccessFile(url, "r");
            userDD = new RandomAccessFile(user, "r");
            passwordDD = new RandomAccessFile(password, "r");
            u = urlDD.readUTF();
            u1 = userDD.readUTF();
            p = passwordDD.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            passwordDD.close();
            urlDD.close();
            userDD.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(u + u1 + p);

    }
}