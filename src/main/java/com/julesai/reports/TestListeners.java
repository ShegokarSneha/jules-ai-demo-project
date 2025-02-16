package com.julesai.reports;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TestListeners implements ITestListener, IAnnotationTransformer {
    private Map<String, String> allParameters = new HashMap<>();
    private Map<String, String> suiteParameters = new HashMap<>();
    private Map<String, String> localParameters = new HashMap<>();
    private final List<String> fileList = new ArrayList<>();

    public TestListeners() {
    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    public void onStart(ITestContext iTestContext) {
        this.allParameters = iTestContext.getSuite().getXmlSuite().getAllParameters();
        this.suiteParameters = iTestContext.getSuite().getXmlSuite().getParameters();
        this.localParameters = iTestContext.getCurrentXmlTest().getLocalParameters();
    }

    public Map<String, String> getLocalParameters() {
        return this.localParameters;
    }

    public Map<String, String> getSuiteParameters() {
        return this.suiteParameters;
    }

    public Map<String, String> getAllParameters() {
        return this.allParameters;
    }

    public void onFinish(ITestContext iTestContext) {
        ExtentConfiguration.getInstance().flush();
        ExtentTestManager.endTest();
        this.compressDirectory("AutomationReports", "AutomationReports.zip");
    }

    private void compressDirectory(String dir, String zipFile) {
        File directory = new File(dir);
        this.getFileList(directory);
        try (FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);) {
            for (String filePath : this.fileList) {
                String name = filePath.substring(directory.getAbsolutePath().length() + 1, filePath.length());
                ZipEntry zipEntry = new ZipEntry(name);
                zipOutputStream.putNextEntry(zipEntry);
                this.readFileWriteZipout(zipOutputStream, filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFileWriteZipout(ZipOutputStream zipOutputStream, String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int lenght;
            while ((lenght = fileInputStream.read(buffer)) > 0) {
                zipOutputStream.write(buffer, 0, lenght);
            }
            zipOutputStream.closeEntry();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getFileList(File directory) {
        File[] files = directory.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isFile()) {
                    this.fileList.add(file.getAbsolutePath());
                } else {
                    this.getFileList(file);
                }
            }
        }
    }

    public void onTestStart(ITestResult iTestResult) {
        ExtentTestManager.startTest(iTestResult.getParameters()[0].toString().replace("\"", ""),
                iTestResult.getParameters()[1].toString().replace("\"", ""));
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(iTestResult.getName() + " Passed Successfully.");
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Failed " + getTestMethodName(iTestResult));
        if (ExtentTestManager.getTest() != null) {
            if (iTestResult.getThrowable().toString().contains("java.lang.AssertionError")) {
                String errorMsg = iTestResult.getThrowable().getMessage().toLowerCase();
                try {
                    ExtentTest test = ExtentTestManager.getTest();
                    Status status = Status.FAIL;
                    String newvalue = errorMsg.substring(errorMsg.indexOf("expected"), errorMsg.length() - 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                ExtentTestManager.getTest().log(Status.FAIL, "Test Step Failed : " + iTestResult.getThrowable());
            }
        }
    }

    public void onTestSkipped(ITestResult iTestResult) {
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().log(Status.SKIP, iTestResult.getName() + " Execution skipped");
        }
    }
}
