package cn.shin.autotest.testcase;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, format = { "pretty",
		"html:cucumber/cucumber", "rerun:cucumber/rerun.txt" })
public class CucumberTest {

}
