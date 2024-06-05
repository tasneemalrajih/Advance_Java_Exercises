package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import org.example.dao.JobsDAO;
import org.example.dto.JobsDto;
import org.example.dto.JobsFilterDto;
import org.example.models.jobs;
import java.net.URI;

import java.sql.SQLException;

import java.util.ArrayList;

@Path("/jobs")
public class JobsController {

    JobsDAO dao = new JobsDAO();
    @Context
    UriInfo uriInfo;
    @Context HttpHeaders headers;


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
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAlljobs(

            @BeanParam JobsFilterDto filter
    ) {
        try {
            GenericEntity<ArrayList<org.example.models.jobs>> j = new GenericEntity<ArrayList<jobs>>(dao.selectAllJobs(filter.get_min_salary())) {};
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(j)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }

            return Response
//                    .ok()
//                    .entity(depts)
//                    .type(MediaType.APPLICATION_JSON)
                    .ok(j, MediaType.APPLICATION_JSON)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @GET
//    @Path("{Job_id}")
//    public Response jobs getjob(@PathParam("Job_id") int Job_id
//        @BeanParam JobsFilterDto filter
//    ) {
//        if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
//            return Response
//                    .ok(Job_id)
//                    .type(MediaType.APPLICATION_XML)
//                    .build();
//        }
//
//        return Response
////                    .ok()
////                    .entity(depts)
////                    .type(MediaType.APPLICATION_JSON)
//                .ok(Job_id, MediaType.APPLICATION_JSON)
//                .build();
//    }
//        @GET
//        @Path("{Job_id}")
//        public Response getjob(@PathParam("Job_id") int Job_id, @BeanParam JobsFilterDto filter) {
//            if (headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
//                return Response
//                        .ok(Job_id)
//                        .type(MediaType.APPLICATION_XML)
//                        .build();
//            } else {
//                // Construct JSON response
//                return Response
//                        .ok("{\"Job_id\": " + Job_id + "}")
//                        .type(MediaType.APPLICATION_JSON)
//                        .build();
//            }
//        }
        @GET
        @Path("{Job_id}")
        public Response getjob(@PathParam("Job_id") int Job_id) throws SQLException {

            try {
                jobs j = dao.selectjob(Job_id);

                if (j == null) {
                    //throw new DataNotFoundException("Job " + Job_id + "Not found");
                }

                JobsDto dto = new JobsDto();
                dto.setJob_id(j.getJob_id());
                dto.setJob_title(j.getJob_title());
                dto.setMax_salary(j.getMax_salary());
                dto.setMin_salary(j.getMin_salary());
                addLinks(dto);

                return Response.ok(dto).build();
            }
            catch (ClassNotFoundException e) {
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



    @POST
    public Response insertJobs(jobs j) {

        try {
            dao.insertjob(j);
            return Response
                    .ok(j)
                    .status(Response.Status.CREATED)
                    .build();
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
    private void addLinks(JobsDto dto) {
        URI selfUri = uriInfo.getAbsolutePath();
        URI empsUri = uriInfo.getAbsolutePathBuilder()
                .path(JobsController.class)
                .build();

        dto.addLink(selfUri.toString(), "self");
        dto.addLink(empsUri.toString(), "employees");
    }

}
