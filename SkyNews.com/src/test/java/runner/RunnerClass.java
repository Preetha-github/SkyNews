package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="FeatureFiles",
glue = {"stepDefinition", "hooks"},
dryRun = false, 
monochrome = true,
tags="@Homepage",
plugin = {"html:Report/webreport.html", "json:Report/jsonreport.json", "junit:Report/xmlreport.xml"}
)
public class RunnerClass {
	

}