package it.shadow.events4j.argument;

import it.shadow.events4j.argument.Argument;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgumentTest {


    @Test
    public void testArgumentBuild(){
        Argument<String> arg = Argument.newBuilder().setName("test_name").setValue("test_value").build();
        assertEquals("Name argument not as expected","test_name",arg.getName());
        assertEquals("Value argument not as expected","test_value",arg.getValue());
    }

    @Test
    public void testOfCreation(){
        Argument actualArg = Argument.newBuilder().setName("test_name").setValue("test_value").build();

        Argument expectedArg = Argument.of("test_name","test_value");

        assertEquals("Name argument not as expected",expectedArg.getName(),actualArg.getName());
        assertEquals("Value argument not as expected",expectedArg.getValue(),actualArg.getValue());
    }

}
