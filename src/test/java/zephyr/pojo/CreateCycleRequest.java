package zephyr.pojo;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
public @Data
class CreateCycleRequest implements Serializable {
	private String environment;
	private int versionId;
	private String endDate;
	private String name;
	private String description;
	private long projectId;
	private String startDate;
}