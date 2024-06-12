
import org.example.dao.JobsDAO;

import org.example.models.jobs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;


@ExtendWith(MockitoExtension.class)
public class JobsDaoTeast {


    @InjectMocks
    JobsDAO dao;


//    @Test
//    public void testupdatejob() throws SQLException, ClassNotFoundException {
//
//        jobs j = new jobs(1,"Public Accountant1",4200,9000);
//
//        Assertions.assertDoesNotThrow(() -> dao.updatejob(j));
//
//
//        jobs jj=dao.selectjob(1);
//
//        Assertions.assertNotNull(jj);
//        Assertions.assertEquals(1, jj.getJob_id());
//    }

    @Test
    public void testUpdateDept() throws SQLException, ClassNotFoundException {
        jobs d = new jobs(1,"Public Accountant1",4200,9000);

        Assertions.assertDoesNotThrow(() -> dao.updatejob(d));

        jobs newD = dao.selectjob(d.getJob_id());

        Assertions.assertNotNull(newD);
        Assertions.assertEquals(newD.getJob_title(), d.getJob_title());
        Assertions.assertEquals(newD.getMax_salary(), d.getMax_salary());

    }

}
