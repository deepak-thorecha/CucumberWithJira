package zephyr.pojo;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
public @Data class BulkExecutionRequest {
	private List<String> executions;
	private boolean testStepStatusChangeFlag;
	private int stepStatus;
	private int status;
	private boolean clearDefectMappingFlag;
}