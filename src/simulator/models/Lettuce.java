package simulator.models;

public class Lettuce extends Crop {
    public Lettuce() {
        super("Lettuce", 3, 10, 5.0, 2.0); // Fast growth, moderate water, low value, cheap seeds
    }

    @Override
    public void grow(String weatherCondition, boolean isWatered) {
        if (!isWatered) {
            health -= 20; // Needs consistent water
        } else {
            currentGrowth++;
            if (weatherCondition.equals("Sunny")) {
                currentGrowth++; // Bonus growth in sun
            } else if (weatherCondition.equals("Cold")) {
                health -= 15; // Frost damage
            }
        }
    }
}
