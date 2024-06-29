package Classes;

import java.io.Serializable;

public class House extends Household implements Serializable, ExpensesCost {
    public int id_house;
    public String location;
    public String admin_company;


    public House(int id_house, String location, String admin_company, double water_in_consumption, double water_out_consumption, double recycled_waste_garbadge, double waste_garbadge_weight, double shared_electricity) {
        super(water_in_consumption, water_out_consumption, recycled_waste_garbadge, waste_garbadge_weight, shared_electricity);
        this.id_house = id_house;
        this.location = location;
        this.admin_company = admin_company;
    }

    public int getId_house() {
        return id_house;
    }

    public void setId_house(int id_house) {
        this.id_house = id_house;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAdmin_company() {
        return admin_company;
    }

    public void setAdmin_company(String admin_company) {
        this.admin_company = admin_company;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append(getId_house());
        sb.append(", ");
        sb.append(getLocation());
        sb.append(", ");
        sb.append(getAdmin_company());
        sb.append(", ");
        sb.append(getWater_in_consumption());
        sb.append(", ");
        sb.append(getWater_out_consumption());
        sb.append(", ");
        sb.append(getRecycled_waste_garbage_weight());
        sb.append(", ");
        sb.append(getWaste_garbage_weight());
        sb.append(", ");
        sb.append(getShared_electricity());

        return sb.toString();
    }

    @Override
    public double computeCost(double up_water_in, double up_water_out, double up_recycled_waste, double up_waste, double up_shared_electricity) {
        return getWater_in_consumption() * up_water_in + getWater_out_consumption() * up_water_out + getRecycled_waste_garbage_weight() * up_recycled_waste + getWaste_garbage_weight() * up_waste + getShared_electricity() * up_shared_electricity ;

    }


}

