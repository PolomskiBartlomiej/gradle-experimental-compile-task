import org.junit.Assert;
import org.junit.Test;

public class ExperimentalFileTest {

    @Test
    public void testExperimentalName() {
        Assert.assertEquals(new ExperimentalFile().name(),"experimental");
    }
}
