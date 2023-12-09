package undecided.account.model.employee;

public class Employee {
    private Long EmployeeId;
    private String name;
    private Long departmentId;

    public Employee(Long employeeId, String name, Long departmentId) {
        EmployeeId = employeeId;
        this.name = name;
        this.departmentId = departmentId;
    }

    public Long getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(Long employeeId) {
        EmployeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public static Employee create(long employeeId, String name, long departmentId) {
        return new Employee(employeeId, name, departmentId);
    }

}
