package org.example.helpers;

import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.example.dao.DepartmentDAO;
import org.example.dao.EmployeeDAO;
import org.example.dto.ErrorMessage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Inject
    EmployeeDAO dao ;



    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if(!requestContext.getUriInfo().getPath().contains("secures")) return;

        List<String> authHeaders = requestContext.getHeaders().get("Authorization");
        if (authHeaders != null && authHeaders.size() != 0) {
            String authHeader = authHeaders.get(0);
            authHeader = authHeader.replace("Basic ", "");
            authHeader = new String(Base64.getDecoder().decode(authHeader));
            StringTokenizer tokenizer = new StringTokenizer(authHeader, ":");
            String username = tokenizer.nextToken();
            String password = tokenizer.nextToken();
            try {
                if(dao.selectEMP_name(username)!= null &&password.equals("App@123456")){return;}

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        ErrorMessage err = new ErrorMessage();
        err.setErrorContent("Please login");
        err.setErrorCode(Response.Status.UNAUTHORIZED.getStatusCode());
        err.setDocumentationUrl("https://google.com");

        Response res = Response.status(Response.Status.UNAUTHORIZED)
                .entity(err)
                .build();

        requestContext.abortWith(res);
    }
}
