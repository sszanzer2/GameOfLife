package ss;

import java.util.Scanner;

public class GameOfLifeMain {
    public static void main(String[] args) {
        GridInterface gameOfLifeGrid = new GameOfLifeBoard();
        Scanner scanner = new Scanner(System.in);

        int generations;
        // User input for the number of generations
        System.out.println("Enter the number of generations: ");
        generations = scanner.nextInt();
        while(generations <= 0) {
        	System.out.println("Please enter a number greater than 0");
        	System.out.println("Enter the number of generations: ");
            generations = scanner.nextInt();
        }

        for (int i = 0; i < generations; i++) {
            gameOfLifeGrid.printGrid();
            gameOfLifeGrid.updateGrid();

            // Put a sleep interval for visualization
            try {
                Thread.sleep(50); // sleep duration for 50 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // Close the scanner
        scanner.close();
    }
}
