package zephyr.apiImpl;

public enum ExecutionStatus {
    UNEXECUTED(-1),
    PASS(1),
    FAIL(2),
    WIP(3),
    BLOCKED(4);

    private final int status;
    ExecutionStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
