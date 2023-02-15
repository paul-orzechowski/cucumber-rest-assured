package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features", glue = "stepDefinitions", monochrome = true, tags = "not @ignore"
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
