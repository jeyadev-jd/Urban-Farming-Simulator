package simulator.systems;

import simulator.models.Crop;
import java.util.Random;

public class Market {
    private Random rand;

    public Market() {
        rand = new Random();
    }

    public double calculateSellPrice(Crop crop) {
        // Price fluctuates randomly between 0.8x and 1.5x of base value
        double fluctuation = 0.8 + (rand.nextDouble() * 0.7);
        double price = crop.getBaseValue() * fluctuation;
        
        // Quality factor based on health
        double qualityMultiplier = crop.getHealth() / 100.0;
        
        return price * qualityMultiplier;
    }
}
