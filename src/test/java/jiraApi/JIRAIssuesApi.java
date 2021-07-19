package jiraApi;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.entity.ContentType;

import java.io.IOException;

import static Util.Constants.JIRA_BASE_URL;

public class JIRAIssuesApi {

    public static Long getJiraIdForIssueName(String issueName) throws IOException {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(JIRA_BASE_URL)
                .setBasePath("/rest/api/3/issue/" + issueName)
                .addHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType()).build();
        Response response = RestAssured.given(requestSpecification).get();
        return response.getBody().jsonPath().getLong("id");
        /*CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpPost = new HttpGet(JIRA_BASE_URL + "/rest/api/3/issue/" + issueName);
        httpPost.addHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
        CloseableHttpResponse response = client.execute(httpPost);
        client.close();
        JsonObject json = new ObjectMapper().readValue(response.getEntity().getContent(), JsonObject.class);
        return json.get("id").getAsLong();*/
    }

}
