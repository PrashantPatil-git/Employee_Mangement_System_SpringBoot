package net.javaguides.ems_backend.service.Implementation;

import lombok.AllArgsConstructor;
import net.javaguides.ems_backend.dto.EmployeeDto;
import net.javaguides.ems_backend.entity.Employee;
import net.javaguides.ems_backend.mapper.EmployeeMapper;
import net.javaguides.ems_backend.repository.EmployeeRepository;
import net.javaguides.ems_backend.service.EmployeeService;
import org.springframework.stereotype.Service;


//1.First implement employee service and map employeeDto into employee entity
//2.Then return saved object to client using mapper method.

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

     private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);// convert employeedto into employee entity using map method.
        Employee savedEmployee= employeeRepository.save(employee); // then save the employee object using save method of employeerepository
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);  //
    }
}
