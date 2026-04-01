package simulator.models;

public class Strawberry extends Crop {
    public Strawberry() {
        super("Strawberry", 6, 15, 18.0, 8.0); // Slow growth, high water, high value, expensive seeds
    }

    @Override
    public void grow(String weatherCondition, boolean isWatered) {
        if (!isWatered) {
            health -= 35; // Very sensitive to lack of water
        } else {
            currentGrowth++;
            if (weatherCondition.equals("Sunny")) {
                // Standard growth
            } else if (weatherCondition.equals("Rainy")) {
                health -= 10; // Too much rain can cause rot
            } else if (weatherCondition.equals("Cold")) {
                health -= 25; // Very sensitive to cold
            } else if (weatherCondition.equals("Drought")) {
                health -= 15; // Extra stress even if watered
            }
        }
    }
}
