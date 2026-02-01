package edu.narxoz.galactic.drones;

public class HeavyDrone extends Drone {
public HeavyDrone(String id, double maxPlayloadKg){
        super(id, maxPlayloadKg);
    } 

    @Override
    public double speedKmPerMin(){
        return 5.0;
    }
}  