package com.julesai.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.observer.ExtentObserver;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentConfiguration {

    private static ExtentReports extent;
    private static final String TIME_STAMP = new SimpleDateFormat("dd.MM.yyyy.HH.mm").format(new Date());
    public static final String WORKING_DIR = System.getProperty("user.dir");
    private static final String EXTENT_REPORTS_FOLDER;
    private static final String REPORT_NAME;
    private static final String EXTENT_REPORTS_PATH;

    static {
        EXTENT_REPORTS_FOLDER = WORKING_DIR + "/AutomationReports";
        REPORT_NAME = "ExtentReport_" + TIME_STAMP + ".html";
        EXTENT_REPORTS_PATH = EXTENT_REPORTS_FOLDER + File.separator + REPORT_NAME;
    }

    private ExtentConfiguration() {
    }

    public static ExtentReports getInstance() {
        if (extent == null) {
            createReportsFolder();
            attachReporters();
        }
        return extent;
    }

    private static ExtentReports attachReporters() {
        extent = new ExtentReports();
        extent.attachReporter(new ExtentObserver[]{initHtmlReporter()[0]});
        return extent;
    }

    private static ExtentSparkReporter[] initHtmlReporter() {
        ExtentSparkReporter extentHtmlReporter = new ExtentSparkReporter(EXTENT_REPORTS_PATH);
        try {
            extentHtmlReporter.loadJSONConfig("{" +
                    "  \"theme\": \"STANDARD\"," +
                    "  \"encoding\": \"utf-8\"," +
                    "  \"protocol\": \"HTTPS\"," +
                    "  \"timelineEnabled\": true," +
                    "  \"documentTitle\": \"" + REPORT_NAME + "\"," +
                    "  \"timeStampFormat\": \"MMM dd, yyyy HH:mm:ss a\"," +
                    "  \"js\": \"\"," +
                    "  \"css\" : \"\"" +
                    "}");
        }catch (IOException e){
            setConfig(extentHtmlReporter);
        }
        return new ExtentSparkReporter[]{extentHtmlReporter};
    }

    private static void setConfig(ExtentSparkReporter extentSparkReporter){
        extentSparkReporter.config().setTheme(Theme.STANDARD);
        extentSparkReporter.config().setDocumentTitle(REPORT_NAME);
        extentSparkReporter.config().setEncoding("utf-8");
        extentSparkReporter.config().setReportName("Execution-Status");
        extentSparkReporter.config().setCss("css-string");
        extentSparkReporter.config().setJs("js-string");
        extentSparkReporter.config().setProtocol(Protocol.HTTPS);
        extentSparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        extentSparkReporter.config().setTimelineEnabled(true);
    }

    private static void createReportsFolder() {
        File file = new File(EXTENT_REPORTS_FOLDER);
        if (!file.exists() && !file.mkdir()) {
            System.out.println("Filed to create directory");
        }
    }


}
