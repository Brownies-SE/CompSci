//Joshua Brown
import java.util.Map;
import java.util.Set;

public class DFA {


    private final Set<State> states;
    private final Set<String> alphabet;
    private final Map<State, Map<String, State>> transitions;
    private final State initial;
    private final Set<State> accept;

    /**
     * Fully parameterized constructor for DFA objects.
     * @param states - the set of states of this DFA
     * @param alphabet - the alphabet of this DFA
     * @param transitions - the transition function of this DFA
     * @param initial - the start state of this DFA
     * @param accept - the set of accept states of this DFA
     */
    public DFA(Set<State> states, Set<String> alphabet, Map<State, Map<String, State>> transitions, State initial, Set<State> accept) {

        this.states = states;
        this.alphabet = alphabet;
        this.transitions = transitions;
        this.initial = initial;
        this.accept = accept;
    }

    /**
     * Determines whether given string is accepted or rejected by this DFA. Each state's activity counter is
     * initialized to zero, then incremented by one each time the state is entered.
     * @param input the input string; allows both "" and null to indicate the empty string
     * @return true if the string is in the language recognized by this DFA; false otherwise
     */
    public boolean accepts(String input) {
        return canAccept(input, initialState());

    }

    /**
     * Retrieves the set of accept states.
     * @return accept states
     */
    public Set<State> acceptStates() {
        return this.accept;
    }

    /**
     * Retrieves the alphabet of this DFA.
     * @return the alphabet
     */
    public Set<String> alphabet() {
        return this.alphabet;
    }

    /**
     * Retrieves the initial state of this DFA, if any.
     * @return the initial state; null if none
     */
    public State initialState() {
        return this.initial;
    }

    /**
     * Lookup transition for specified state and input.
     * @param source the source state
     * @param input the input symbol
     * @return the destination state; null if none
     */
    public State nextState(State source, String input) {
        Map<String, State> currentState = this.transitions.getOrDefault(source, null);

        if (currentState == null) {
            return null;
        }

        return currentState.getOrDefault(input, null);
    }

    /**
     * Retrieves the set of all states.
     * @return the states
     */
    public Set<State> states() {
        return this.states;
    }

    /**
     * Retrieves the transition function of this DFA.
     * @return the transition function
     */
    public Map<State, Map<String,State>> transitionFunction() {
        return this.transitions;
    }

    private boolean canAccept(String input, State current) {

        /*
		 * Initializes activity counter to zero for each state
		 */

        for(State temp : states) {
            temp.reset();
        }
        /*
        *checks to see if the input is empty checks the state
        */
        if(input.isEmpty() && acceptStates().contains(current)) {
            return true;
        }
        /*
        *traverses through the state given the string
        */
        for (String symbol: alphabet()) {
            if (symbol == null) {
                if (canAccept(input, nextState(current, symbol))) {
                    return true;
                }
            }
            /* has gone through all the string to obtain the final state
		    * will return false if current is not an accept state
		    * returns true if current is an accept state
		    */

            else if (input.startsWith(symbol)) {
                if(canAccept(input.substring(symbol.length()), nextState(current, symbol))) {
                    return true;
                }
            }
        }
        return false;
    }
}
