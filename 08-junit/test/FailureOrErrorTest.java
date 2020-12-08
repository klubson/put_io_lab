import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.*;


public class FailureOrErrorTest {
    @Test
    public void test1(){
        assertEquals(1, 0);//failure
    }
    @Test
    public void test2() throws Exception {
        throw new Exception("Exception message"); //error
    }
    @Test
    public void test3(){//pytanie 4.2: oczekuje na AssertionFailedError
        try{
            fail();
        } catch (AssertionFailedError e) {
            e.printStackTrace();
        }
    }

}
