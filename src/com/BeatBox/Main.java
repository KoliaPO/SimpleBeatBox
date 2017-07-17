package com.BeatBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args)  throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your Name: ");
        String name = reader.readLine();
        new StartUpProgramm().startUp(name);
    }
}
