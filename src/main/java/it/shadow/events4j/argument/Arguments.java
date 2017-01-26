package it.shadow.events4j.argument;


import java.util.*;


/**
 * Event Arguments.The argument of an event.
 */
public final class Arguments {

    private final List<Argument> arguments;

    private Arguments(List<Argument> arguments) {
        this.arguments = Collections.unmodifiableList(arguments);
    }

    /**
     * Create {@link Arguments} from a list of {@link Argument}
     * @param argumentsList list of {Argument}
     */
    public static Arguments of(List<Argument> argumentsList){
        return newBuilder().setArguments(argumentsList.stream().toArray(s -> new Argument[s])).build();
    }

    /**
     * Create empty {@link Arguments}
     */
    public static Arguments of(){
        return newBuilder().build();
    }

    /**
     * Return {{@link Argument}} where name is {argId}. Return null if
     * argument not exist
     * @param argId argument name used to identify argument
     */
    public Argument getArgument(String argId){
        try {
            return arguments.stream()
                    .filter(a -> a.getName().equals(argId))
                    .findFirst().get();
        }catch(NoSuchElementException e){
            return null;
        }
    }

    /**
     * Return the number of argument
     */
    public int size() {
        return arguments.size();
    }


    static Builder newBuilder(){
        return new Builder();
    }


    public static class Builder{

        private List<Argument> arguments = new ArrayList<>();;

        /**
         * Add argument to argument.
         */
        public Builder addArgument(Argument argument){
            arguments.add(argument);
            return this;
        }

        public Arguments build(){
            return new Arguments(arguments);
        }

        /**
         * Set argument. Previous set argument will be overwritten
         */
        public Builder setArguments(Argument... args) {
            this.arguments = Arrays.asList(args);
            return this;
        }
    }
}
