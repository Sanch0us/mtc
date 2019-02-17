package ru.nsu.fit.chuprakov.Machine;

import java.util.*;

class MachineTest {

    @org.junit.jupiter.api.Test
    void isRecognized() {
        Map<Integer, Map<Character, Integer>> transitions = new HashMap<>();
        transitions.put(1, new HashMap<>());
        transitions.put(2, new HashMap<>());
        transitions.put(3, new HashMap<>());
        transitions.get(1).put('A', 1);
        transitions.get(1).put('B', 2);
        transitions.get(2).put('A', 2);
        transitions.get(2).put('B', 3);
        transitions.get(3).put('A', 3);
        transitions.get(3).put('B', 1);
        List<Integer> endStates = new ArrayList<>();
        endStates.add(1);
        Machine machine = new Machine(endStates, transitions);
        try {
            assert machine.isRecognized("ABABBAABABAAB");
            assert !machine.isRecognized("ABABBAABABAA");
            assert !machine.isRecognized("ABABBAABAAA");
            assert machine.isRecognized("ABABBAAAAA");
        }catch (Exception ignored){}
    }
}