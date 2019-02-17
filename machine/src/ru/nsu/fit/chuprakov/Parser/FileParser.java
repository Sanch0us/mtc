package ru.nsu.fit.chuprakov.Parser;

import ru.nsu.fit.chuprakov.Exceptions.IncorrectMachineException;
import ru.nsu.fit.chuprakov.Machine.Machine;

import java.io.*;
import java.util.*;

public class FileParser {

    private static List<Integer> getEndStates(String line){
        List<Integer> endStates = new ArrayList<>();
        String states[] = line.split(" ");
        for(String s: states){
            if(s.length() > 0){
                int st = Integer.parseInt(s);
                endStates.add(st);
            }
        }
        return endStates;
    }

    private static Map<Integer, Map<Character, Integer>> getTransitions(BufferedReader reader) throws IOException {
        String line;
        Map<Integer, Map<Character, Integer>> transitions = new HashMap<>();
        while (reader.ready()){
            line = reader.readLine();
            String transition[] = line.split(" ");
            int beginState = Integer.parseInt(transition[0]);
            char symbol = transition[1].charAt(0);
            int endState = Integer.parseInt(transition[2]);
            if(!transitions.containsKey(beginState)){
                transitions.put(beginState, new HashMap<>());
            }
            Map<Character, Integer> map = transitions.get(beginState);
            map.put(symbol, endState);
        }
        return transitions;
    }

    private static boolean areTransitionsCorrect(Map<Integer, Map<Character, Integer>> transitions) {
        for(Map<Character, Integer> map: transitions.values()){
            Set<Character> keys = map.keySet();
            for(char i = 'A'; i <= 'Z'; i++){
                if(!keys.contains(i)){
                    return false;
                }
            }
            Collection<Integer> values =  map.values();
            for (int value : values) {
                if (!transitions.containsKey(value)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Machine getMachine(String filename) throws IOException, IncorrectMachineException {
        try(FileReader fileReader = new FileReader(filename)) {
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            List<Integer> endStates = getEndStates(line);
            Map<Integer, Map<Character, Integer>> transitions = getTransitions(reader);
            if(!areTransitionsCorrect(transitions)){
                throw new IncorrectMachineException();
            }
            return new Machine(endStates, transitions);
        }
    }

    public static String getString(String filename) throws IOException {
        try(FileReader fileReader = new FileReader(filename)) {
            return new BufferedReader(fileReader).readLine();
        }
    }

}
