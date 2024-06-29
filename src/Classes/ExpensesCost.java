package Classes;

@FunctionalInterface
public interface ExpensesCost {
    public double computeCost(double up_water_in, double up_water_out, double up_recycled_waste, double up_waste, double up_shared_electricity);


}
