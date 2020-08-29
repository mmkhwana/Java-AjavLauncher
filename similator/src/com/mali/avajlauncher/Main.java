package com.mali.avajlauncher;

import com.mali.avajlauncher.aircraft.AircraftFactory;
import com.mali.avajlauncher.tower.WeatherTower;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    public static int positeInt;
    public static WeatherTower wTower = new WeatherTower();
    public static void main(String[] args) {
        System.out.println("Begin avaj launcher");

        String textFile = args[0];
        if(textFile != args[0]){
            System.out.println("Invalid input");
        }
        else{
        Scanner reader = new Scanner(textFile);
        ReadFile(reader.nextLine());
        changeCondtions();
        reader.close();
        }
    }

    public static void ReadFile(String fileName) {
        AircraftFactory aircraftFactory = new AircraftFactory();

        String buf;

        try {
            BufferedReader bReader = new BufferedReader(new FileReader(fileName));
            String spaces[];
            boolean x = true;
            int y = 1;

            while ((buf = bReader.readLine()) != null) {
                if (x == true && y == 1) {
                    try {
                        positeInt = Integer.parseInt(buf);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                        return;
                    }
                    if (positeInt < 0){
                        System.out.println("Iteration has to be a positive integer");
                        return;
                    }
                } else {
                    spaces = buf.split(" ");
                    if (spaces.length == 1)
                        continue;
                    if (spaces.length != 5) {
                        System.out.println("5 parameter inputs expected");
                    }
                    try {
                        aircraftFactory.newAircraft(spaces[0], spaces[1], Integer.parseInt(spaces[2]),
                                Integer.parseInt(spaces[3]), Integer.parseInt(spaces[4])).registerTower(wTower);
                        ;
                    } catch (Exception e) {
                        System.out.println("Some wrong with inputs and its this " + e);
                        System.out.println("This whats wrong " + buf);
                    }
                }
                y++;
            }
            bReader.close();
        } catch (Exception e) {
            System.out.println("Some wrong with file reading");
        }
    }

    public static void changeCondtions() {
        while (positeInt > 0) {
            wTower.changeWeather();
            positeInt--;
        }
    }
}
