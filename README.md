# Events4J
Porting of Node.js events library (https://nodejs.org/api/events.html) using new Java 8 API

### Feature
- lighweith. No dependencies.
- thread safe.

### Documentation

#### Create Event Emitter

To create an event emitter you could instantiate the class `EventEmitter`
or extend it (best way)

```java
EventEmitter eventEmitter = new EventEmitter();
```

#### Create And Register An Event Listener

An `EventListener` is a Java 8 `@FunctionalInterface` that have a `call` method
that accept optional `Arguments` and return `void`. So you can create an event listener as 
a lambda expression that accept only one parameter.

Any `EventListener` can be attached to an event identified by a name.

```java
eventEmitter.on("event_name", arg -> System.out.println("I am an event!"));`
```

A listener may require arguments.

```java
//user argument with name "a"
eventEmitter.on("event_name", arg -> System.out.println("Event argument: " + arg.getArgument("a")));`
```

An `Argument` value can be retrieved by its name. If argument's name not exist return `null`.
`Argument` instances are immutable to avoid values changing in a event listener function.


An event can have more than one listener. When the event is emitted, listeners are called
following the register order
 
```java
//#1
eventEmitter.on("event_name", args -> System.out.println("I am an event by listener1!");`
//#2
eventEmitter.on("event_name", args -> System.out.println("I am an event by listener2!");`
``` 

#### Only Once Event

An event can be emitted only once time

```java
eventEmitter.once("event_name", args -> System.out.println("I am an event!");`
```


#### Emit Event

A registered event can be emitted using its name. Not registered events will be ignored.

```java
eventEmitter.emit("event_name");`
```
        
`Arguments` can be passed when an event is emitted.

```java
//create argument with name "a" and value "b"
//A value can be any Java Type
Argument argA = Argument.of("a", "b");

//create instance of arguments
Arguments args = Arguments.of(Arrays.asList(argA));

eventEmitter.emit("event_name", args);
```

`Arguments` and `Argument` are immutable to avoid problems 
when use it on `EventListener`


#### Remove Event Listener

Remove a `EventListener` for an event. 
This operation require pointer to instance of the listener to be removed


```java
EventListener eventListener = System.out.println("I am an event!");

//add event listener
eventEmitter.on("event_name", eventListener);

//remove event listener
eventEmitter.removeListener("event_name", eventListener);
```

###TODO
- emit events when a listener is registered or is removed for an event
- delete all listener of an event
- async emit
- limit number of listeners per event
- return `Option` on retrieve an argument value. This remove casting problem of `null` values
