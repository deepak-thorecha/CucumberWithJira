package zephyr.pojo;

import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
public @Data class CreateExecutionIdRequest{
	private long issueId;
	private long versionId;
	private String cycleId;
	private String id;
	private String assigneeType;
	private String assignee;
	private long projectId;
	private Status status;
}