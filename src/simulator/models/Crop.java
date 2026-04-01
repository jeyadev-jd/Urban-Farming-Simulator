package simulator.models;

public abstract class Crop {
    protected String name;
    protected int growthTimeRequired;
    protected int currentGrowth;
    protected int waterNeedDaily;
    protected int health;
    protected double baseValue;
    protected double seedCost;

    public Crop(String name, int growthTimeRequired, int waterNeedDaily, double baseValue, double seedCost) {
        this.name = name;
        this.growthTimeRequired = growthTimeRequired;
        this.waterNeedDaily = waterNeedDaily;
        this.baseValue = baseValue;
        this.seedCost = seedCost;
        this.currentGrowth = 0;
        this.health = 100;
    }

    public abstract void grow(String weatherCondition, boolean isWatered);

    public boolean isReadyToHarvest() {
        return currentGrowth >= growthTimeRequired && health > 0;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public String getName() { return name; }
    public int getWaterNeedDaily() { return waterNeedDaily; }
    public double getBaseValue() { return baseValue; }
    public double getSeedCost() { return seedCost; }
    public int getHealth() { return health; }
    
    public String getStatus() {
        if (isDead()) return "Dead";
        if (isReadyToHarvest()) return "Ready to Harvest";
        return "Growing (" + currentGrowth + "/" + growthTimeRequired + ") Health: " + health + "%";
    }
}
