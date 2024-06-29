package Classes;

import java.io.Serializable;
import java.util.concurrent.ConcurrentMap;

public class Household implements Serializable{
    public double water_in_consumption;
    public double water_out_consumption;
    public double recycled_waste_garbage_weight;
    public double waste_garbage_weight;
    public double shared_electricity;


    public Household(double water_in_consumption, double water_out_consumption, double recycled_waste_garbage_weight, double waste_garbage_weight, double shared_electricity) {
        this.water_in_consumption = water_in_consumption;
        this.water_out_consumption = water_out_consumption;
        this.recycled_waste_garbage_weight = recycled_waste_garbage_weight;
        this.waste_garbage_weight = waste_garbage_weight;
        this.shared_electricity = shared_electricity;
    }

    public double getWater_in_consumption() {
        return water_in_consumption;
    }

    public void setWater_in_consumption(double water_in_consumption) {
        this.water_in_consumption = water_in_consumption;
    }

    public double getWater_out_consumption() {
        return water_out_consumption;
    }

    public void setWater_out_consumption(double water_out_consumption) {
        if(water_out_consumption < 0) throw new IllegalArgumentException("Water consumption can't be negative");
        this.water_out_consumption = water_out_consumption;
    }

    public double getRecycled_waste_garbage_weight() {
        return recycled_waste_garbage_weight;
    }

    public void setRecycled_waste_garbage_weight(double recycled_waste_garbage_weight) {
        if(recycled_waste_garbage_weight < 0) throw new IllegalArgumentException("Recycled garbadge can't be negative");
        this.recycled_waste_garbage_weight = recycled_waste_garbage_weight;
    }

    public double getWaste_garbage_weight() {
        return waste_garbage_weight;
    }

    public void setWaste_garbage_weight(double waste_garbage_weight) {
        if(waste_garbage_weight < 0) throw new IllegalArgumentException("Waste garbage weight can't be negative");
        this.waste_garbage_weight = waste_garbage_weight;
    }

    public double getShared_electricity() {
        return shared_electricity;
    }

    public void setShared_electricity(double shared_electricity) {
        if(shared_electricity < 0) throw new IllegalArgumentException("Shared electricity can't be negative");
        this.shared_electricity = shared_electricity;
    }


}
