package CallCenter;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {

    private final List<List<Employee>> employees = new ArrayList<>(3);

    public void setEmployees(final List<Employee> employees) {
        for (Employee employee : employees) {
            switch (employee.getEmployeeType()) {
                case RESPONDENT -> this.employees.get(0).add(employee);
                case MANAGER -> this.employees.get(1).add(employee);
                case DIRECTOR -> this.employees.get(2).add(employee);
                default -> System.out.println("Employee type is not defined or invalid.");
            }
        }
    }

    public void dispatchCall(Call call) {
        Employee handler = getHandler(call);

        if (handler != null) {
            handler.assignCall(call);
        } else {
            System.out.println("No suitable employee found. Please try again");
        }
    }

    private Employee getHandler(Call call) {

        for (int rank = 0; rank < 3; rank++) {
            for (Employee employee : employees.get(rank)) {
                if (employee.isFree() && employee.canHandleCall(call)) {
                    return employee;
                }
            }
        }

        return null;
    }
}
