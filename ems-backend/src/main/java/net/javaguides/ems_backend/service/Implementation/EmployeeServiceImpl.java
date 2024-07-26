package net.javaguides.ems_backend.service.Implementation;

import lombok.AllArgsConstructor;
import net.javaguides.ems_backend.dto.EmployeeDto;
import net.javaguides.ems_backend.entity.Employee;
import net.javaguides.ems_backend.exception.ResourceNotFoundException;
import net.javaguides.ems_backend.mapper.EmployeeMapper;
import net.javaguides.ems_backend.repository.EmployeeRepository;
import net.javaguides.ems_backend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


//1.First implement employee service and map employeeDto into employee entity
//2.Then return saved object to client using mapper method.

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    // Service layer to create a new employee
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);// convert employeedto into employee entity using map method.
        Employee savedEmployee = employeeRepository.save(employee); // then save the employee object using save method of employeerepository
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);  //
    }

    // Service layer to  get employee by employee id
    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).
                orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with given id:" + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee is not exist with given id:"+employeeId));

         employee.setFirst_name(updatedEmployee.getFirstName());
         employee.setLast_name(updatedEmployee.getLastName());
         employee.setEmail(updatedEmployee.getEmail());

         Employee updatedEmployeeObj=employeeRepository.save(employee);

         return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }
}

