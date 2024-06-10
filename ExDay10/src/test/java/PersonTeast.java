

import org.example.models.Person;
import org.junit.jupiter.api.*;
public class PersonTeast {
    private Person p;

    @BeforeEach
    public void createObjects() {

       p= new Person("Tasneem", 20,"SA");
        System.out.println("creating before each");
    }
    @Test
    public void setAge(){
        int actual = p.getAge();
        int expected = 20;
        Assertions.assertEquals(expected,actual);
    }


}
