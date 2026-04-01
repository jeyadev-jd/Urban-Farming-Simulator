package simulator;

import simulator.models.Crop;
import simulator.systems.Farm;
import simulator.systems.Market;
import simulator.systems.WeatherSystem;

import java.util.Iterator;
import java.util.List;

public class GameEngine {
    private Farm farm;
    private WeatherSystem weatherSystem;
    private Market market;
    private int dayCount;
    private final double DAILY_EXPENSES = 5.0; // Fixed operating cost

    public GameEngine(Farm farm, WeatherSystem weatherSystem, Market market) {
        this.farm = farm;
        this.weatherSystem = weatherSystem;
        this.market = market;
        this.dayCount = 1;
    }

    public void advanceDay() {
        System.out.println("\n--- End of Day " + dayCount + " ---");
        
        // 1. Weather resolves
        weatherSystem.generateWeather();
        String weather = weatherSystem.getCurrentWeather();
        System.out.println("Weather system changing... It is now " + weather + ".");
        
        // 2. Adjust central water reservoir based on weather
        int waterChange = weatherSystem.getWaterIncome();
        farm.adjustWater(waterChange);
        if (waterChange > 0) {
            System.out.println("The weather added " + waterChange + " units to the reservoir.");
        } else if (waterChange < 0) {
            System.out.println("The weather evaporated " + Math.abs(waterChange) + " units from the reservoir.");
        }

        // 3. Process crops
        List<Crop> crops = farm.getPlantedCrops();
        Iterator<Crop> iterator = crops.iterator();
        
        int totalWaterConsumed = 0;
        
        while (iterator.hasNext()) {
            Crop c = iterator.next();
            
            // Check if we have enough water for this crop
            boolean watered = false;
            if (farm.getWaterReservoir() >= c.getWaterNeedDaily()) {
                farm.adjustWater(-c.getWaterNeedDaily());
                totalWaterConsumed += c.getWaterNeedDaily();
                watered = true;
            }
            
            c.grow(weather, watered);
            
            if (c.isDead()) {
                System.out.println("A " + c.getName() + " crop has died.");
                iterator.remove();
            }
        }
        
        if (totalWaterConsumed > 0) {
            System.out.println("Crops consumed " + totalWaterConsumed + " units of water today.");
        }
        
        // 4. Daily expenses
        farm.deductCapital(DAILY_EXPENSES);
        System.out.println("Daily operating expenses deducted: $" + String.format("%.2f", DAILY_EXPENSES));
        
        dayCount++;
    }

    public int getDayCount() {
        return dayCount;
    }

    public boolean isGameOver() {
        return farm.isBankrupt();
    }
}
