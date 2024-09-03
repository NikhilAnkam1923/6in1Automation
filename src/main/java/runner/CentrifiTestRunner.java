package runner;

import cucumber.api.CucumberOptions;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

@CucumberOptions(
        features = {"features"},
        glue = { "com.centrifi.automation.glue"},
        monochrome = true
)
public class CentrifiTestRunner extends BaseRunner {

    @BeforeClass(alwaysRun = true)
    public void beforeClass(ITestContext iTestContext) throws IOException {
        init(iTestContext);
    }
}