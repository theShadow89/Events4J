package it.shadow;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EventEmitterTest {

    EventEmitter eventEmitter;

    @Before
    public void setUp(){
        eventEmitter = new EventEmitter();
    }

    @Test
    public void testAddOneListenerForEvent(){
        eventEmitter.on("test_event", arg -> System.out.println("test out"));
        assertEquals(eventEmitter.listenerCount("test_event"),1);
    }

    @Test
    public void testAddMoreListenersForEvent(){
        eventEmitter.on("test_event", arg -> System.out.println("test out"));
        eventEmitter.on("test_event", arg -> System.out.println("test out"));
        eventEmitter.on("test_event", arg -> System.out.println("test out"));
        assertEquals(eventEmitter.listenerCount("test_event"),3);
    }

    @Test
    public void testEmitEvent(){
        List<String> eventsEmitted = new ArrayList<>();
        eventEmitter.on("test_event1", arg -> eventsEmitted.add("one"));
        eventEmitter.on("test_event2", arg -> eventsEmitted.add("two"));
        eventEmitter.on("test_event3", arg -> eventsEmitted.add("three"));

        eventEmitter.emit("test_event1");
        eventEmitter.emit("test_event2");
        eventEmitter.emit("test_event3");

        assertEquals(Arrays.asList("one","two","three"),eventsEmitted);
    }

    @Test
    public void testEmitNotRegisterEvent(){
        List<String> eventsEmitted = new ArrayList<>();
        eventEmitter.on("test_event", arg -> eventsEmitted.add("one"));
        eventEmitter.emit("test_event_not_registered");
        assertEquals("Emit event not exist, so it can't be emitted",eventsEmitted.size(),0);
    }

    @Test
    public void testEmitOneTimeEvent(){
        List<String> eventsEmitted = new ArrayList<>();
        eventEmitter.once("test_event", arg -> eventsEmitted.add("one"));

        eventEmitter.emit("test_event");
        eventEmitter.emit("test_event");
        eventEmitter.emit("test_event");

        assertEquals("Event should be emitted only once",1,eventsEmitted.size());
    }


    @Test
    public void testEmitEventWithArgs(){
        List<String> eventsEmitted = new ArrayList<>();
        eventEmitter.once("test_event", arg -> eventsEmitted.add((String) arg.getArgument("test").getValue()));

        Arguments args = Arguments.of(Arrays.asList(Argument.of("test","hello")));
        eventEmitter.emit("test_event",args);

        assertEquals(Arrays.asList("hello"),eventsEmitted);
    }

    @Test
    public void testRemoveOneEventListener(){
        EventListener eventListener = arg -> System.out.println("test");
        eventEmitter.on("test_event", eventListener);

        eventEmitter.removeListener("test_event", eventListener);

        assertEquals(0,eventEmitter.listenerCount("test_event"));
    }

    @Test
    public void testListenersCountForNotRegisteredEvent(){
        assertEquals(0,eventEmitter.listenerCount("not_registered_event"));
    }
}
