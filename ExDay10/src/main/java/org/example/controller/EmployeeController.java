package org.example.controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.example.dao.EmployeeDAO;
import org.example.models.Department;
import org.example.models.Employee;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("/employees")
public class EmployeeController {

//    @GET
//    public ArrayList<Employee> getAllEmployees(@PathParam("deptId") Integer deptId) {
//        System.out.println(deptId);
//        ArrayList<Employee> employees = new ArrayList<>();
//        employees.add(new Employee());
//        employees.add(new Employee());
//        employees.add(new Employee());
//        employees.add(new Employee());
//        return employees;
//    }
@GET
@Produces( MediaType.APPLICATION_JSON)
public ArrayList<Employee> getAllEmployees(
        @QueryParam("employee_id") Integer employee_id

) {try {
    EmployeeDAO dao = new EmployeeDAO();
    return dao.selectAllEMP(employee_id);
} catch (Exception e) {
    throw new RuntimeException(e);
}
}

    @POST
    public void insertEmployee(@PathParam("deptId") Integer deptId, Employee emp) {

        System.out.println(deptId);
        System.out.println(emp);
    }

}
