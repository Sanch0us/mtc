package ru.nsu.fit.chuprakov.Machine;

import ru.nsu.fit.chuprakov.Exceptions.IncorrectMachineException;

import java.util.List;
import java.util.Map;

public class Machine {

    private List<Integer> endStates;

    private Map<Integer, Map<Character, Integer>> transitions;

    private static final int BEGIN_STATE = 1;

    public Machine(List<Integer> endStates, Map<Integer, Map<Character, Integer>> transitions){
        this.endStates = endStates;
        this.transitions = transitions;
    }

    public boolean isRecognized(String str) throws IncorrectMachineException {
        int currentState = BEGIN_STATE;
        for(int i = 0; i < str.length(); i++){
            if(!transitions.containsKey(currentState)){
                throw new IncorrectMachineException();
            }
            Map<Character, Integer> map = transitions.get(currentState);
            if(!map.containsKey(str.charAt(i))){
                throw new IncorrectMachineException();
            }
            currentState = map.get(str.charAt(i));
        }
        return endStates.contains(currentState);
    }

}
