package org.example.dto;

import jakarta.ws.rs.QueryParam;

public class JobsFilterDto {
    private @QueryParam("min_salary") Double min_salary;


    public Double get_min_salary() {
        return min_salary;
    }

    public void set_min_salary(Double min_salary) {
        this.min_salary = min_salary;
    }


}
