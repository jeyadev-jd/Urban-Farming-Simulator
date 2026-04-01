# Urban Farming Simulator

Urban Farming Simulator is a text-based management project that utilizes Object-Oriented Programming (OOP) in Java to model the complexities of sustainable city agriculture. It abstracts crop biology, seasonal weather, and market fluctuations into scalable software entities.

## Team Members
* Sahasra
* Charishma
* Mokshitha

## Project Architecture
The architecture leverages encapsulation and polymorphism to manage specific plant requirements (e.g., Lettuce, Strawberries) and environmental stressors. 

Players must balance daily resource management with strategic financial planning to ensure the survival of their rooftop farm. The system’s modular design, built on SOLID principles, ensures that new agricultural techniques can be seamlessly integrated.

### Key Components:
* **Crop Management**: Encapsulates biological data (growth time, water needs) for different species.
* **Weather System**: Simulates seasonal transitions and daily atmospheric changes that impact resource availability.
* **Market Economics**: Simulates fluctuating prices and demand, requiring risk management.
* **Resource Management**: A logic-driven system for monitoring water reservoirs and capital flow, enforcing "Game Over" states upon bankruptcy.

## How to Run

1. Open a terminal in the project root directory.
2. Compile the files:
   ```bash
   Current-Directory> javac -d out src/simulator/*.java src/simulator/models/*.java src/simulator/systems/*.java
   ```
3. Run the simulator:
   ```bash
   Current-Directory> java -cp out simulator.Main
   ```
