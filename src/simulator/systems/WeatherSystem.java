package simulator.systems;

import java.util.Random;

public class WeatherSystem {
    private String[] conditions = {"Sunny", "Rainy", "Drought", "Cold"};
    private String currentWeather;
    private Random rand;

    public WeatherSystem() {
        rand = new Random();
        generateWeather();
    }

    public void generateWeather() {
        currentWeather = conditions[rand.nextInt(conditions.length)];
    }

    public String getCurrentWeather() {
        return currentWeather;
    }

    public int getWaterIncome() {
        switch(currentWeather) {
            case "Rainy": return 60;
            case "Sunny": return 0;
            case "Drought": return -20; // Evaporation loss
            case "Cold": return 10;     // Less evaporation, maybe condensation
            default: return 0;
        }
    }
}
