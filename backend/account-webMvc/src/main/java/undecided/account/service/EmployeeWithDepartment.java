package undecided.account.service;

import undecided.account.model.department.Department;
import undecided.account.model.employee.Employee;
import undecided.account.model.prefecture.PrefectureDto;

public class EmployeeWithDepartment {
    private Employee employee;
    private Department department;
    private PrefectureDto prefecture;

    public EmployeeWithDepartment(Employee employee, Department department, PrefectureDto prefecture) {
        this.employee = employee;
        this.department = department;
        this.prefecture = prefecture;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public PrefectureDto getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(PrefectureDto prefecture) {
        this.prefecture = prefecture;
    }

    public static EmployeeWithDepartment reconstruct(Employee employee, Department department, PrefectureDto prefecture) {
        return new EmployeeWithDepartment(employee, department, prefecture);
    }
}
