package edu.narxoz.galactic;
import edu.narxoz.galactic.bodies.Planet;
import edu.narxoz.galactic.bodies.SpaceStation;
import edu.narxoz.galactic.cargo.Cargo;
import edu.narxoz.galactic.dispatcher.Dispatcher;
import edu.narxoz.galactic.dispatcher.Result;
import edu.narxoz.galactic.drones.HeavyDrone;
import edu.narxoz.galactic.drones.LightDrone;
import edu.narxoz.galactic.task.DeliveryTask;

public class Main{
     public static void main(String[] args) {
        Planet mars = new Planet("Mars", 0, 0, "Carbon dioxide");
        SpaceStation station = new SpaceStation("Mars Station", 120, 15, 8);
        Cargo cargo = new Cargo(20, "Scientific research equipment");
        LightDrone lightDrone = new LightDrone("white", 10);
        HeavyDrone heavyDrone = new HeavyDrone("black", 100);
        DeliveryTask task = new DeliveryTask(mars, station, cargo);
        Dispatcher dispatcher = new Dispatcher();

        Result res1 = dispatcher.assignTask(task, lightDrone);
        System.out.println("LightDrone assign: ok=" + res1.ok() + " reason " + res1.reason());

        Result res2 = dispatcher.assignTask(task, heavyDrone);
        System.out.println("HeavyDrone assign: ok=" + res2.ok() + " reason " + res2.reason());

        System.out.println("estimated time: " + task.estimateTime() + " min");

        Result res3 = dispatcher.completeTask(task);
        System.out.println("task completed: ok=" + res3.ok());

        System.out.println("task state: " + task.getState());
        System.out.println("drone status: " + heavyDrone.getStatus());
    }
}