package ru.nsu.fit.chuprakov;


import ru.nsu.fit.chuprakov.Exceptions.IncorrectMachineException;
import ru.nsu.fit.chuprakov.Machine.Machine;
import ru.nsu.fit.chuprakov.Parser.FileParser;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Machine machine;
        try{
            machine = FileParser.getMachine(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + args[0]);
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (IncorrectMachineException e) {
            System.out.println("Machine is incorrect1");
            return;
        }
        String str;
        try {
            str = FileParser.getString(args[1]);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + args[1]);
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }try {
            if (machine.isRecognized(str)) {
                System.out.println("String is recognized");
            } else {
                System.out.println("String is not recognized");
            }
        } catch (IncorrectMachineException e) {
            System.out.println("Machine is incorrect2");
        }
    }
}
