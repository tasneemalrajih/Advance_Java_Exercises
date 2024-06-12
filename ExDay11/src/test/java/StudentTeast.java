
import org.example.models.Person;
import org.example.models.Student;
import org.junit.jupiter.api.*;


public class StudentTeast {

    //    private int count;
    private static Student s1;

    @BeforeEach
    public void createObjects() {


        s1 = new Student("Tasneem", 25, "SA", 123456, "IS");
        System.out.println("creating before each");
    }

    @Test
    public void setName() {
        String actual = s1.getName();
        String expected = "Suad";
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void setcourse() {
        String  actual = s1.getCourse();
        String expected = "CS";
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void setaddress() {
        String  actual = s1.getAddress();
        String expected = "SA";
        Assertions.assertEquals(expected, actual);
    }
}
