package it.shadow;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ArgumentsTest {

    @Test
    public void testArgumentsBuildWithSomeArguments() {
        Argument arg1 = Argument.of("test_name", "test_value");
        Arguments args = Arguments.of(Arrays.asList(arg1));
            assertEquals(1,args.size());
        assertEquals(arg1,args.getArgument("test_name"));
    }

    @Test
    public void testArgumentsBuildWithNoArguments() {
        Arguments args = Arguments.of();
        assertEquals("Argument size must be 0",0,args.size());
    }

    @Test
    public void testAddOneArgumentToArgumentsBuilder(){
        Argument arg1 = Argument.of("test_name", "test_value");
        Arguments.Builder argBuilder = new Arguments.Builder();

        Arguments args = argBuilder.build();


        argBuilder.addArgument(arg1);
        assertEquals("Argument size must be 1",1,args.size());
    }


    @Test
    public void testAddMultipleArgumentToArgumentsBuilder(){
        Argument arg1 = Argument.of("test_name1", "test_value1");
        Argument arg2 = Argument.of("test_name2", "test_value2");
        Arguments.Builder argBuilder = new Arguments.Builder();

        Arguments args = argBuilder.setArguments(arg1,arg2).build();


        assertEquals("Argument size must be 1",2,args.size());
    }

    @Test
    public void testGetNotExistingArgument(){
        Arguments args = Arguments.of();
        assertEquals(null,args.getArgument("test_name"));
    }

}
