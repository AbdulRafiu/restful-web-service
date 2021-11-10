package com.abdul;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employee")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    EmployeeRepository employeeRepository;

    @GET
    @Path("/{id}")
    public Response getEmployee(@PathParam("id") long id) {
        return employeeRepository
                .findEmployeeById(id)
                .map(Response::ok)
                .orElseGet( () -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @GET
    public Response findAll() {
        return Response.ok(employeeRepository.findAll()).build();
    }


    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer countEmployees() {
        return employeeRepository.count();
    }

    @POST
    @Path("/add")
    public Response addEmployee(Employee employee) {
        return Response.ok(employeeRepository.create(employee)).build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@PathParam("id") long id) {
        return Response.ok(employeeRepository.update(id)).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("id") long id) {
        boolean removed = employeeRepository.delete(id);
        if (removed)
            return Response.ok().build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
