package CallCenter;

public class Call {

    private Employee assignedEmployee = null;
    private CallStatus status;

    public Call() {
        this.status = CallStatus.OPEN;
    }

    public void setStatus(CallStatus status) {
        this.status = status;
    }

    public void setAssignedEmployee(Employee employee) {
        this.assignedEmployee = employee;
    }
}
