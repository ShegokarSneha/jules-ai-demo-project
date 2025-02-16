package com.julesai.utilities;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.julesai.reports.ExtentTestManager;
import com.microsoft.playwright.Page;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.nio.file.Paths;

public class Screenshots {
    private Screenshots() {
    }

    static {
        createDirectory();
    }
    private static String screenshotsFolderPath;

    private static void createDirectory() {
        screenshotsFolderPath = "AutomationReports/screenshots/";
        File file = new File(screenshotsFolderPath);
        if (!file.exists() && !file.mkdir()) {
            System.out.println("Failed Creation");
        }
    }

    public static void addStepWithScreenshotInReport(Page page, String message) {
        ExtentTest extentTest = ExtentTestManager.getTest();
        if (extentTest != null) {
            if (page != null) {
                String path = captureScreenshot(page, "screenshot");
                try {
                    extentTest.pass(message, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                extentTest.pass(message);
            }
        }
    }

    public static void addStepInReport(boolean condition, String message){
        ExtentTest extentTest = ExtentTestManager.getTest();
        if(extentTest != null){
            if(condition){
                extentTest.pass(message);
            }else{
                extentTest.fail(message);
            }
        }
    }
    private static String captureScreenshot(Page page, String screenshot) {
        String randomNumber = RandomStringUtils.randomNumeric(5);
        String destinationPath = screenshotsFolderPath + screenshot + randomNumber + ".png";
        page.screenshot((new Page.ScreenshotOptions()).setPath(Paths.get(destinationPath)));
        return destinationPath.substring(destinationPath.indexOf("/") + 1);
    }
}
