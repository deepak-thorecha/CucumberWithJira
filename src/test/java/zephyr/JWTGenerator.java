package zephyr;

import com.thed.zephyr.cloud.rest.ZFJCloudRestClient;
import com.thed.zephyr.cloud.rest.client.JwtGenerator;
import org.openqa.selenium.remote.http.HttpMethod;

import java.net.URI;
import java.net.URISyntaxException;

import static Util.Constants.*;

public class JWTGenerator {

    private final static String zephyrBaseUrl = ZAPI_BASE_URL;
    private final static String accessKey = ACCESS_KEY;
    private final static String secretKey = SECRET_KEY;
    private final static String userName = ACCOUNT_ID;

    public static String getJWTToken(HttpMethod method, String uriPath) throws URISyntaxException {
        ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, userName)
                .build();
        JwtGenerator jwtGenerator = client.getJwtGenerator();
        String createCycleUri = zephyrBaseUrl + uriPath;
        URI uri = new URI(createCycleUri);
        int expirationInSec = 360;
        String jwt = jwtGenerator.generateJWT(method.name(), uri, expirationInSec);
        System.out.println("JWT Token : " + jwt);
        return jwt;
    }
}
