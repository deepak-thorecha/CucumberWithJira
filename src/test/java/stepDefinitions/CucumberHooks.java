package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import zephyr.apiImpl.CreateTestCycleAndExecutions;
import zephyr.apiImpl.ExecutionStatus;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static Util.Constants.*;

public class CucumberHooks extends BaseTest {

    public static List<String> issues = new ArrayList<>();

    @Before(order = 1)
    public void collectJiraIssues(Scenario scenario) {
        issues = new ArrayList<>(0);
        issues.addAll(scenario.getSourceTagNames().stream()
                .filter(tag -> tag.contains(PROJECT_NAME))
                .map(s -> s.substring(1))
                .collect(Collectors.toList()));
    }

    @After(order = 2)
    public void addJiraTestToCycle(Scenario scenario) throws IOException, URISyntaxException {
        ADD_TEST_REF_ID = CreateTestCycleAndExecutions.addBulkTestToCycle(CYCLE_ID, issues);
        List<String> executionIds = CreateTestCycleAndExecutions.createExecutionId(issues);
        updateTestStatusMap(scenario.isFailed() ? ExecutionStatus.FAIL.name() : ExecutionStatus.PASS.name(), executionIds);
    }

    private void updateTestStatusMap(String statusKey, List<String> execIds) {
        if(TEST_STATUS.containsKey(ExecutionStatus.valueOf(statusKey).name())) {
            List<String> updateExecIds = TEST_STATUS.get(ExecutionStatus.valueOf(statusKey).name());
            updateExecIds.addAll(execIds);
            TEST_STATUS.put(ExecutionStatus.valueOf(statusKey).name(), updateExecIds);
        } else {
            TEST_STATUS.put(ExecutionStatus.valueOf(statusKey).name(), execIds);
        }
    }
}
