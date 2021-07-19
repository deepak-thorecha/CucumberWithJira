package zephyr.apiImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jiraApi.JIRAIssuesApi;
import org.apache.http.entity.ContentType;
import org.openqa.selenium.remote.http.HttpMethod;
import zephyr.JWTGenerator;
import zephyr.pojo.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static Util.Constants.*;

public class CreateTestCycleAndExecutions {

    private static ObjectMapper mapper = new ObjectMapper();

    private static Response postRequest(String entity, String uriPath) throws IOException, URISyntaxException {
        String jwtToken = JWTGenerator.getJWTToken(HttpMethod.POST, uriPath);
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(ZAPI_BASE_URL)
                .setBasePath(uriPath)
                .addHeader(HDR_AUTHORIZATION, jwtToken)
                .addHeader(HDR_ACCESS_KEY, ACCESS_KEY)
                .addHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType())
                .setBody(entity).build();
        return RestAssured.given(requestSpecification).post();
        /*CloseableHttpClient client = HttpClients.createMinimal();
        HttpPost httpPost = new HttpPost(ZAPI_BASE_URL + uriPath);

        httpPost.addHeader(HDR_AUTHORIZATION, jwtToken);
        httpPost.addHeader(HDR_ACCESS_KEY, ACCESS_KEY);
        httpPost.addHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
        StringEntity strEntity = new StringEntity(entity);
        httpPost.setEntity(strEntity);
        CloseableHttpResponse response = client.execute(httpPost);
        client.close();
        return response;*/
    }

    public static CreateCycleResponse createTestCycle(String cycleName)
            throws IOException, URISyntaxException {
        String d = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
        CreateCycleRequest cycle = CreateCycleRequest
                .builder().projectId(10000)
                .versionId(-1)
                .description("Test Cycle Generated via Automation")
                .environment("DEV")
                .startDate(d)
                .name(cycleName).build();
        String entity = mapper.writeValueAsString(cycle);
        Response response = postRequest(entity, "/public/rest/api/1.0/cycle");
        return response.getBody().as(CreateCycleResponse.class);
    }

    public static String addBulkTestToCycle(String cycleId, List<String> issueNames)
            throws IOException, URISyntaxException {
        AddTestToCycle addTest = AddTestToCycle.builder()
                .issues(issueNames)
                .assigneeType("assignee")
                .assigneeAccountId(ACCOUNT_ID)
                .projectId(PROJECT_ID)
                .versionId(VERSION_ID)
                .build();
        String entity = mapper.writeValueAsString(addTest);
        Response response = postRequest(entity, "/public/rest/api/1.0/executions/add/cycle/" + cycleId);
        return response.getBody().toString();
    }

    public static List<String> createExecutionId(List<String> issues) {
        List<String> executionIds = new ArrayList<>(0);
        issues
                .forEach(issueName -> {
                    try {
                        long jiraId = JIRAIssuesApi.getJiraIdForIssueName(issueName);
                        CreateExecutionIdRequest execRequest = CreateExecutionIdRequest
                                .builder()
                                .status(Status.builder().id(-1).build())
                                .cycleId(CYCLE_ID)
                                .projectId(PROJECT_ID)
                                .versionId(VERSION_ID)
                                .issueId(jiraId)
                                .id(ADD_TEST_REF_ID).build();
                        String entity = mapper.writeValueAsString(execRequest);
                        Response response = postRequest(entity, "/public/rest/api/1.0/execution");
                        /*JsonObject json = mapper.readValue(response.getBody().asPrettyString(), JsonObject.class);
                        executionIds.add(json.getAsJsonObject("execution").get("id").getAsString());*/
                        executionIds.add(response.getBody().jsonPath().getString("execution.id"));
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }
                });
        return executionIds;
    }

    public static void bulkExecutionUpdate() {
        TEST_STATUS.forEach((key, value) -> {
            BulkExecutionRequest executionRequest = BulkExecutionRequest
                    .builder()
                    .executions(value)
                    .status(ExecutionStatus.valueOf(key).getStatus())
                    .testStepStatusChangeFlag(true)
                    .stepStatus(ExecutionStatus.valueOf(key).getStatus()).build();
            try {
                String entity = mapper.writeValueAsString(executionRequest);
                Response response = postRequest(entity, "/public/rest/api/1.0/executions");
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }

        });
    }
}
