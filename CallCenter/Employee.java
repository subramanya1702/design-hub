package CallCenter;

import java.util.concurrent.CompletableFuture;

public class Employee {

    private Call currentCall = null;
    private final EmployeeType employeeType;

    public Employee(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public EmployeeType getEmployeeType() {
        return this.employeeType;
    }

    public Call getCurrentCall() {
        return this.currentCall;
    }

    public void assignCall(Call call) {
        this.currentCall = call;
        this.currentCall.setStatus(CallStatus.ASSIGNED);
        this.currentCall.setAssignedEmployee(this);

        // Process the call asynchronously
        CompletableFuture.runAsync(this::processCall);
    }

    public boolean isFree() {
        return this.currentCall == null;
    }

    // Logic to check if an employee can handle the call
    public boolean canHandleCall(Call call) {
        return true;
    }

    private void processCall() {
        // Process the call

        // Finish and un-assign the call after processing
        this.currentCall.setStatus(CallStatus.COMPLETE);
        this.currentCall = null;
    }
}
