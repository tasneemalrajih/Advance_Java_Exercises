package org.example.controller;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.dao.JobsDAO;
import org.example.dto.JobsFilterDto;
import org.example.models.jobs;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/jobs")
public class JobsController {

    JobsDAO dao = new JobsDAO();

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public ArrayList<jobs> getAlljobs(
//            @QueryParam("min_salary") Double min_salary
//
//    ) {
//        try {
//
//            return dao.selectAllJobs(min_salary);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<jobs> getAlljobs(

            @BeanParam JobsFilterDto filter
    ) {
        try {
            return dao.selectAllJobs(filter.get_min_salary());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{Job_id}")
    public jobs getjob(@PathParam("Job_id") int Job_id) {

        try {

            return dao.selectjob(Job_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @DELETE
    @Path("{job_id}")
    public void deleteJobs(@PathParam("job_id") int job_id) {

        try {
            dao.deletjob(job_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertDepartment(jobs j) {

        try {

            dao.insertjob(j);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @GET
//    @Path("{job_id}")
//    public jobs getJobs(@PathParam("job_id") int job_id) {
//
//        try {
//            return dao.selectjob(job_id);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }



//    @POST
//    public void insertJobs(jobs j) {
//
//        try {
//            dao.insertjob(j);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @PUT
    @Path("{job_id}")
    public void updateJobs(@PathParam("job_id") int job_id, jobs j ) {

        try {

            j.setJob_id(job_id);
            dao.updatejob(j);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
