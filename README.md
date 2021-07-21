# CucumberWithJira
#### _Sample code to Create Test Cycle in JIRA using ZAPI, Linking Test Cases to Cycle and Executing/Updating Test Case status based on Scneario status._

Use CucumberRunner to execute scenarios. Required VM Args:

- -DPROJECT_NAME="projectNameIdentifier"
- -DJIRA_BASE_URL=""
- -DJIRA_USER=<userName used for JIRA Login>
- -DJIRA_KEY=<JIRA API Token>
- -DZAPI_BASE_URL="https://prod-api.zephyr4jiracloud.com/connect"
- -DACCESS_KEY=<ZAPI Access Key>
- -DSECRET_KEY=<ZAPI Secret Key>
- -DACCOUNT_ID=<accountId for User>
- -DHDR_AUTHORIZATION="Authorization"
- -DHDR_ACCESS_KEY="zapiAccessKey"
- -DPROJECT_ID=<projectId>
- -DVERSION_ID=<versionId>

## References:
- Refer [Manage API Token](https://support.atlassian.com/atlassian-account/docs/manage-api-tokens-for-your-atlassian-account/) to generate JIRA API Token
- Refer [ZAPI JWT Token](https://support.smartbear.com/zephyr-squad-cloud/docs/api/jwt-token.html) to generate ZAPI Access  and Secret Keys. Both will be required to generate JWT Token.
- Use JIRA APIs to find JIRA Project ID. Postman Collection can be found [here](https://apitools.zeroqode.com/)

## Pre-Requisites:
Assuming Test Cases are already existing in JIRA. Use the Test issue name as a Tag for your scenarios.
