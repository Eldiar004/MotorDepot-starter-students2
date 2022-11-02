package com.company;

import com.company.entities.Driver;
import com.company.entities.Truck;
import com.company.service.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final GsonBuilder BUILDER = new GsonBuilder();
    public static final Gson GSON = BUILDER.setPrettyPrinting().create();
    public static final Path WRITE_PATH = Paths.get("./truck.json");
    public static final Path WRITE_PATH1 = Paths.get("./driver.json");

    public static void main (String[] args) throws Exception {


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        ServiceImpl service = new ServiceImpl();
        Scanner scanner = new Scanner(System.in);
        for (Truck truck : service.getTrucks()) {
            System.out.println("ID    |Truck             |Driver           |State");
            System.out.println(truck.getId()+"     |"+truck.getTruckName()+"          |"+truck.getDriver()+"                |"+truck.getState());
            System.out.println("------+------------------+-------------------------+");
            }
        int com = 0 ;
        while ( com != 9 ) {
            buttons();
            System.err.println("Choose one of the commands");
            com = scanner.nextInt();
            switch (com) {
                case 1 -> {
                    System.err.println("Write truck id that you wanna change driver");
                    service.changeDriver(scanner.nextInt());
                }
                case 2 -> {
                    System.err.println("Write truck id that you wanna send to the Route");
                    service.startDriving(scanner.nextInt());
                }
                case 3 -> {
                    System.err.println("Write truck id that you wanna send to the Repair");
                    service.startRepair(scanner.nextInt());
                }
                default -> System.err.println("Don't have button like this!");
            }
        }
    }

    public static void buttons(){
        System.out.println("------------------------------\n" +
                "Press '1' to change Driver\n" +
        "Press '2' to send to the Route\n" +
        "Press '3' to send to the Repairing\n"+
                "Press '9' to stop the program\n" +
                "------------------------------");
    }

   public static String readTtuck() {
       return getString(WRITE_PATH);
   }

   public static String readDriver() {
       return getString(WRITE_PATH1);
   }

    private static String getString(Path writePath1) {
        StringBuilder json = new StringBuilder();
        try (FileReader fr = new FileReader(String.valueOf(writePath1))){
            int a;
            while ((a = fr.read()) != -1) {
                json.append((char) a);
            }
            return json.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return json.toString();
    }
}