package Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {

    public static final String PROJECT_NAME = System.getProperty("PROJECT_NAME");

    public static final String JIRA_BASE_URL = System.getProperty("JIRA_BASE_URL");
    public static final String JIRA_USER = System.getProperty("JIRA_USER");
    public static final String JIRA_KEY = System.getProperty("JIRA_KEY");

    public static final String ZAPI_BASE_URL = System.getProperty("ZAPI_BASE_URL");
    public static final String ACCESS_KEY = System.getProperty("ACCESS_KEY");
    public static final String SECRET_KEY = System.getProperty("SECRET_KEY");
    public static final String ACCOUNT_ID = System.getProperty("ACCOUNT_ID");

    public static final String HDR_AUTHORIZATION = System.getProperty("HDR_AUTHORIZATION");
    public static final String HDR_ACCESS_KEY = System.getProperty("HDR_ACCESS_KEY");

    public static final Long PROJECT_ID = Long.parseLong(System.getProperty("PROJECT_ID"));
    public static final Integer VERSION_ID = Integer.parseInt(System.getProperty("VERSION_ID"));

    public static String CYCLE_ID = null;
    public static String ADD_TEST_REF_ID = null;
    public static Map<String , List<String>> TEST_STATUS = new HashMap<>();
}
