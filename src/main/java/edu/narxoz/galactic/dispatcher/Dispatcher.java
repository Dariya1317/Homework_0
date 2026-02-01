package edu.narxoz.galactic.dispatcher;

import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.drones.DroneStatus;
import edu.narxoz.galactic.task.DeliveryTask;
import edu.narxoz.galactic.task.TaskState;

public class Dispatcher {
   public Result assignTask(DeliveryTask task, Drone drone) {
        if (task == null || drone == null) {
            return new Result(false, "task or drone can't be null");
        }
        if (drone.getStatus() != DroneStatus.IDLE) {
            return new Result(false, "drone isn't IDLE");
        }
        if (task.getCargo().getWeightKg() > drone.getMaxPayloadKg()) {
            return new Result(false, "cargo exceeds drone capacity");
        }
        if (task.getState() != TaskState.CREATED) {
            return new Result(false, "task isn't in CREATED state");
        }
        task.setAssignedDrone(drone);
        task.setState(TaskState.ASSIGNED);
        drone.setStatus(DroneStatus.IN_FLIGHT);

        return new Result(true, null);
    }

    public Result completeTask(DeliveryTask task) {
        if (task == null) {
            return new Result(false, "task can't be null");
        }
        if (task.getState() != TaskState.ASSIGNED) {
            return new Result(false, "task isn't ASSIGNED");
        }
        if (task.getAssignedDrone() == null) {
            return new Result(false, "no drone assigned to task");
        }
        if (task.getAssignedDrone().getStatus() != DroneStatus.IN_FLIGHT) {
            return new Result(false, "assigned drone isn't IN_FLIGHT");
        }
        task.setState(TaskState.DONE);
        task.getAssignedDrone().setStatus(DroneStatus.IDLE);

        return new Result(true, null);
    }
  
}