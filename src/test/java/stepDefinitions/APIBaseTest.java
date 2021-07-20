package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.JiraClient;
import org.apache.http.entity.ContentType;

import static Util.Constants.*;

public class APIBaseTest {

    protected static final BasicCredentials creds = new BasicCredentials(JIRA_USER, JIRA_KEY);
    protected static final JiraClient jira = new JiraClient(JIRA_BASE_URL, creds);

    public static Response response = null;

    public static RequestSpecification requestSpecification;
    static {
        requestSpecification = RestAssured.given()
            .header("Content-Type", ContentType.APPLICATION_JSON.getMimeType())
            .header("Accept", ContentType.APPLICATION_JSON.getMimeType());
    }


}
