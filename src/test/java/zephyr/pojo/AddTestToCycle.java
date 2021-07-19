package zephyr.pojo;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
public @Data class AddTestToCycle{
	private String assigneeAccountId;
	private int versionId;
	private int method;
	private String assigneeType;
//	private String assignee;
	private List<String> issues;
	private long projectId;
}