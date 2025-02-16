package com.julesai.testrunner;


import com.julesai.reports.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",
        glue = {"com/julesai/stepdefinition"},
        tags = "@LoginTest", plugin = {"pretty", "json:AutomationReports/Cucumber.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class JulesAiTestRunner extends AbstractTestNGCucumberTests {
}
