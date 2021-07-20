package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import zephyr.apiImpl.CreateTestCycleAndExecutions;
import zephyr.pojo.CreateCycleResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static Util.Constants.CYCLE_ID;

@Slf4j
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber-report.json"},
        features = "src/test/resources/features",
        tags = "@JiraIssueType",
        glue = {"stepDefinitions"},
        monochrome = true)
public class CucumberRunner {

    @BeforeClass
    public static void setUpTestCycle() throws IOException, URISyntaxException {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        String d = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
        CreateCycleResponse cycle = CreateTestCycleAndExecutions.createTestCycle(
                "Test Cycle Automation - " + d + " (" + UUID.randomUUID() + ")");
        CYCLE_ID = cycle.getId();
        log.info("Created CYCLE = " + CYCLE_ID);
    }

    @AfterClass
    public static void addTestsAndExecute() {
        CreateTestCycleAndExecutions.bulkExecutionUpdate();
    }
}
