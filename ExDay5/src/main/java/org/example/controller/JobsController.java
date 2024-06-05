package org.example.controller;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.dao.JobsDAO;
import org.example.models.jobs;

import java.util.ArrayList;

@Path("/jobs")
public class JobsController {

    JobsDAO dao = new JobsDAO();

    @GET
    public ArrayList<jobs> SELECT_ALL_JOB() {

        try {
            return dao.selectAlljob();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
}

    @GET
    @Path("{job_id}")
    public jobs getJobs(@PathParam("job_id") int job_id) {

        try {
            return dao.selectjob(job_id);
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
    public void insertJobs(jobs j) {

        try {
            dao.insertjob(j);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
