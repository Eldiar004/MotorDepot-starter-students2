package com.company.service;


import com.company.entities.Driver;
import com.company.entities.State;
import com.company.entities.Truck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.company.Main.*;
//import static com.company.entities.State.ROUTE;

public class ServiceImpl implements Service {
    List<Truck> trucks = new ArrayList<>(List.of(GSON.fromJson(readTtuck(), Truck[].class)));
    List<Truck> truckList = new ArrayList<>(trucks);
    List<Driver> drivers = new ArrayList<>(List.of(GSON.fromJson(readDriver(), Driver[].class)));
    List<Driver> driverList = new ArrayList<>(drivers);

    public List<Truck> getTrucks() {
        return trucks;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public Truck findTruckById(int id) {
        Truck truck = truckList.stream().filter(x -> x.getId() == id).findAny().orElse(null);
        try {
            if (truck == null) {
                throw new Exception("Myndai ID menen truck jok");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return truck;
    }







    public Driver findDriverById() {
        Driver driver = driverList.stream().filter( x -> x.getTruckName().equals("")).findFirst().orElse(null);
        try {
            if (driver == null) {
                throw new Exception("Myndai ID menen driver jok je myndai driver bosh emes");
            }
        }
        catch ( Exception e ) {
            System.out.println(e.getMessage());
        }
        return driver;
    }





    @Override
    public void changeDriver(int truckId) {
        Truck truck = findTruckById(truckId);
        Driver driver = findDriverById();
        if (State.ROUTE != truck.getState()) {
            if ( driver != null ) {
                driver.setTruckName(truck.getTruckName());
                if (truck.getDriver() != null) {
                    String q = truck.getDriver();
                    driverList.stream().filter(x -> x.getTruckName().equals(q)).findAny();
                }
                truck.setDriver(driver.getName());
                System.out.println("--------------------------\n" +
                        "Driver changed successfully!\n" +
                        "--------------------------");
                for ( Truck truck1 : trucks ){
                    System.out.println("ID    |Truck             |Driver           |State");
                    System.out.println(truck1.getId() + "     |" + truck1.getTruckName() + "          |" + truck1.getDriver() + "            |" + truck1.getState());
                    System.out.println("------+------------------+-------------------------+");
                }
                System.out.println(driver);
            }
        }else {
            try {
                throw new Exception("Truck on the Road!!" +
                        "You cannot change driver!!");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }








    @Override
    public void startDriving(int truckId) {
        Truck truck = findTruckById(truckId);
        if ( !truck.getDriver().equals (" ") ) {
            truck.setState(State.ROUTE);
            System.out.println("Successfully truck on in the ROUTE!");
            System.out.println("ID    |Truck             |Driver           |State");
            System.out.println(truck.getId()+"     |"+truck.getTruckName()+"          |"+truck.getDriver()+"            |"+truck.getState());
            System.out.println("------+------------------+-------------------------+");
        }else
            System.err.println("------------------------\n" +
                    "Truck don't have driver!\n"+
                    "You cannot change driver!!\n" +
                    "-------------------------");
    }






    @Override
    public void startRepair(int truckId) {
        Truck truck = findTruckById(truckId);
        if ( truck.getState() == State.BASE || truck.getState() == State.ROUTE ) {
            truck.setState(State.REPAIR);
        } else {
            try {
                throw new Exception("Truck in Repair!");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println("Successfully truck on in the REPAIR!");
        System.out.println("ID    |Truck             |Driver           |State");
        System.out.println(truck.getId()+"     |"+truck.getTruckName()+"          |"+truck.getDriver()+"            |"+truck.getState());
        System.out.println("------+------------------+-------------------------+");    }







    @Override
    public void changeTruckState(int truckId, int state) {
        Truck truck = findTruckById(truckId);
        if ( state == 1 ) {
            truck.setState(State.BASE);
        } else if ( state == 2 ) {
            truck.setState(State.ROUTE);
        } else if ( state == 3 ) {
            truck.setState(State.REPAIR);
        }else
            System.err.println("Don't have State like this!");
    }
}











