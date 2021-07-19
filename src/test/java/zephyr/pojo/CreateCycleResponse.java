package zephyr.pojo;

import lombok.Data;

public @Data class CreateCycleResponse {
	private String createdByAccountId;
	private String endDate;
	private String description;
	private String cycleIndex;
	private String creationDate;
	private String projectCycleVersionIndex;
	private String environment;
	private int versionId;
	private String createdBy;
	private String name;
	private String id;
	private long projectId;
	private String startDate;
}