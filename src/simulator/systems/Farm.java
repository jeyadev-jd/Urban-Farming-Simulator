package simulator.systems;

import simulator.models.Crop;
import java.util.ArrayList;
import java.util.List;

public class Farm {
    private double capital;
    private int waterReservoir;
    private List<Crop> plantedCrops;
    
    private final int MAX_WATER = 200;

    public Farm(double initialCapital, int initialWater) {
        this.capital = initialCapital;
        this.waterReservoir = initialWater;
        this.plantedCrops = new ArrayList<>();
    }

    public double getCapital() { return capital; }
    public int getWaterReservoir() { return waterReservoir; }
    public List<Crop> getPlantedCrops() { return plantedCrops; }

    public void addCapital(double amount) {
        capital += amount;
    }

    public void deductCapital(double amount) {
        capital -= amount;
    }

    public void adjustWater(int amount) {
        waterReservoir += amount;
        if (waterReservoir > MAX_WATER) waterReservoir = MAX_WATER;
        if (waterReservoir < 0) waterReservoir = 0;
    }

    public boolean canAfford(double amount) {
        return capital >= amount;
    }

    public boolean plantCrop(Crop crop) {
        if (canAfford(crop.getSeedCost())) {
            deductCapital(crop.getSeedCost());
            plantedCrops.add(crop);
            return true;
        }
        return false;
    }

    public void removeCrop(Crop crop) {
        plantedCrops.remove(crop);
    }
    
    public boolean isBankrupt() {
        return capital < 0;
    }
}
