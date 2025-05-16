package runner;

import cucumber.api.CucumberOptions;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

@CucumberOptions(
        features = {"features/probateFormsOC04.feature"},
        glue = { "com.sixinone.automation.glue"},
        monochrome = true
)

public class TestRunner extends BaseRunner {

    @BeforeClass(alwaysRun = true)
    public void beforeClass(ITestContext iTestContext) throws IOException {
        init(iTestContext);
    }
}