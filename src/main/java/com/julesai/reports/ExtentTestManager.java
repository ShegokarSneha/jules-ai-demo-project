package com.julesai.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {

    private ExtentTestManager(){
    }

    private static final Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    private static ExtentReports extent;

    public static synchronized ExtentTest getTest(){
        return extentTestMap.get(getCurrentThread());
    }

    private static Integer getCurrentThread() {
        return (int) (Thread.currentThread().getId());
    }

    public static synchronized void endTest(){
        if(!extentTestMap.isEmpty()){
            extent.removeTest(extentTestMap.get(getCurrentThread()));
        }
    }

    public static synchronized ExtentTest startTest(String testName, final String desc){
        extent = ExtentConfiguration.getInstance();
        ExtentTest extentTest = extent.createTest(testName, desc);
        extentTestMap.put(getCurrentThread(), extentTest);
        return extentTest;
    }
}
