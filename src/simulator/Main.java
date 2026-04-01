package simulator;

import simulator.models.Crop;
import simulator.models.Lettuce;
import simulator.models.Strawberry;
import simulator.systems.Farm;
import simulator.systems.Market;
import simulator.systems.WeatherSystem;

import java.util.Scanner;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=========================================");
        System.out.println(" Welcome to the Urban Farming Simulator! ");
        System.out.println("=========================================");
        
        Farm farm = new Farm(50.0, 100); // Start with $50 and 100 water
        WeatherSystem weatherSystem = new WeatherSystem();
        Market market = new Market();
        GameEngine engine = new GameEngine(farm, weatherSystem, market);

        while (!engine.isGameOver()) {
            System.out.println("\n--- Day " + engine.getDayCount() + " ---");
            System.out.println("Weather forecast: " + weatherSystem.getCurrentWeather());
            System.out.printf("Capital: $%.2f%n", farm.getCapital());
            System.out.println("Water Reservoir: " + farm.getWaterReservoir() + " units");
            System.out.println("Planted Crops: " + farm.getPlantedCrops().size());
            
            System.out.println("\nActions:");
            System.out.println("1. View Crops Status");
            System.out.println("2. Plant Lettuce (Cost: $2.0, Water: 10/day)");
            System.out.println("3. Plant Strawberry (Cost: $8.0, Water: 15/day)");
            System.out.println("4. Harvest Ready Crops");
            System.out.println("5. End Day");
            System.out.println("6. Quit Game");
            
            System.out.print("\nChoose an action: ");
            String input = scanner.nextLine();
            
            switch (input.trim()) {
                case "1":
                    viewCrops(farm);
                    break;
                case "2":
                    if (farm.plantCrop(new Lettuce())) {
                        System.out.println("Successfully planted Lettuce.");
                    } else {
                        System.out.println("Not enough capital to plant Lettuce!");
                    }
                    break;
                case "3":
                    if (farm.plantCrop(new Strawberry())) {
                        System.out.println("Successfully planted Strawberry.");
                    } else {
                        System.out.println("Not enough capital to plant Strawberry!");
                    }
                    break;
                case "4":
                    harvestCrops(farm, market);
                    break;
                case "5":
                    engine.advanceDay();
                    break;
                case "6":
                    System.out.println("Thanks for playing!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Please choose a number from 1 to 6.");
            }
        }
        
        System.out.println("\n=========================================");
        System.out.println(" GAME OVER! You have gone bankrupt.");
        System.out.printf(" You survived for %d days.%n", engine.getDayCount() - 1);
        System.out.println("=========================================");
        scanner.close();
    }
    
    private static void viewCrops(Farm farm) {
        if (farm.getPlantedCrops().isEmpty()) {
            System.out.println("No crops currently planted.");
            return;
        }
        for (int i = 0; i < farm.getPlantedCrops().size(); i++) {
            System.out.println((i + 1) + ". " + farm.getPlantedCrops().get(i).getName() + " - " + farm.getPlantedCrops().get(i).getStatus());
        }
    }
    
    private static void harvestCrops(Farm farm, Market market) {
        Iterator<Crop> iterator = farm.getPlantedCrops().iterator();
        int harvestedCount = 0;
        double totalEarned = 0;
        
        while (iterator.hasNext()) {
            Crop crop = iterator.next();
            if (crop.isReadyToHarvest()) {
                double price = market.calculateSellPrice(crop);
                totalEarned += price;
                harvestedCount++;
                System.out.printf("Harvested %s for $%.2f%n", crop.getName(), price);
                iterator.remove();
            }
        }
        
        if (harvestedCount > 0) {
            farm.addCapital(totalEarned);
            System.out.printf("Total earned from harvest: $%.2f%n", totalEarned);
        } else {
            System.out.println("No crops are ready to harvest yet.");
        }
    }
}
