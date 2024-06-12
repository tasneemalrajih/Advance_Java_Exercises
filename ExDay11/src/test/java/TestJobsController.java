import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import org.example.controller.JobsController;
import org.example.dao.JobsDAO;
import org.example.dto.DepartmentDto;
import org.example.dto.JobsDto;
import org.example.mappers.JobsMapper;
import org.example.models.Department;
import org.example.models.jobs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestJobsController {


    @InjectMocks
    JobsController JobsCont;

    @Mock
//    @Spy
    JobsDAO dao;

    @Mock
    JobsMapper mapper;

    @Mock
    UriInfo uriInfo;


    @Test
    public void testGetJobs() throws SQLException, ClassNotFoundException {
        jobs j = new jobs(1,"Public Accountant1",4200,9000);
       JobsDto jDto=new JobsDto();

        URI uri = URI.create("http://localhost/webapi/Jobs/1");

        when(dao.selectjob(1)).thenReturn(j);
        when(mapper.toJobsDto(j)).thenReturn(jDto);
        when(uriInfo.getAbsolutePath()).thenReturn(uri);
        when(uriInfo.getAbsolutePathBuilder()).thenReturn(UriBuilder.fromUri(uri));

        Assertions.assertDoesNotThrow(() -> JobsCont.getjob(1));

        jDto.getLinks().clear();
        Response res = JobsCont.getjob(1);

        verify(mapper, times(2)).toJobsDto(j);

        Assertions.assertEquals(200, res.getStatus());
        Assertions.assertNotNull(((JobsDto) res.getEntity()).getLinks());
        Assertions.assertEquals(2, ((JobsDto) res.getEntity()).getLinks().size());
    }

}