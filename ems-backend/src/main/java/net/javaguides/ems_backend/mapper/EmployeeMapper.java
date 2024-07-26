package net.javaguides.ems_backend.mapper;


import net.javaguides.ems_backend.dto.EmployeeDto;
import net.javaguides.ems_backend.entity.Employee;

// used to communication between client and server
public class EmployeeMapper {


    // convert Employee JPA entity into employeeDto
    public static EmployeeDto mapToEmployeeDto(Employee employee){
         return new EmployeeDto(
                 employee.getId(),
                 employee.getFirst_name(),
                 employee.getLast_name(),
                 employee.getEmail()
         );
    }

     // convert employeeDto into Employee JPA entity
    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}

