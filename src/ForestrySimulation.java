import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;


/**
 * Represents a simulation of forestry activities.
 */

public class ForestrySimulation implements Serializable {

    /**
     * Scanner object for user input
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * The current forest in the simulation.
     */
    public static Forest currentForest;


    /**
     * Checks if a file with the given name and extension exists.
     *
     * @param fileName  The name of the file.
     * @param extension The file extension.
     * @return True if the file exists, false otherwise.
     */
    public static boolean fileExists(String fileName, String extension) {
        File file = new File(fileName + extension);
        return file.exists();
    }

    /**
     * Main method to run the forestry simulation.
     *
     * @param args Command-line arguments.
     * @throws IOException if an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {


        System.out.println("Welcome to the Forestry Simulation");
        System.out.println("----------------------------------");


        char option = 0;
        String forestName;
        int argsIndex = 0;
        while (option != 'X') {
            if (option == 'L') {
                System.out.println("Enter forest name: ");
                forestName = scanner.next();
                if (fileExists(forestName, ".db")) {
                    currentForest = new Forest(forestName, ".db");
                } else {
                    System.out.println("Error opening/reading " + forestName + ".db");
                    System.out.println("Old forest retained");
                }  // end of else statemnt
            } // end of if statement
            else {
                forestName = args[argsIndex];
                if (fileExists(forestName, ".csv")) {
                    currentForest = new Forest(forestName, ".csv");
                    System.out.println("Initializing from " + forestName);
                } // end of if statement
                else {
                    System.out.println("Initializing from " + forestName);
                    System.out.println("Error opening/reading " + forestName + ".csv");
                    argsIndex++;
                    forestName = args[argsIndex];
                    currentForest = new Forest(forestName, ".csv");


                } // end of else statement
            } // end of else statement
            do {
                System.out.println("\n(P)rint, (A)dd, (C)ut, (G)row, (R)eap, (S)ave, (L)oad, (N)ext, e(X)it : ");
                option = Character.toUpperCase(scanner.next().charAt(0));
                switch (option) {
                    case 'P':
                        currentForest.printForest();
                        break;
                    case 'A':
                        currentForest.addTree();
                        break;
                    case 'C':
                        int index = 0;
                        boolean validInput = false;
                        while (!validInput) {
                            System.out.print("Tree number to cut down: ");
                            String input = scanner.nextLine().trim();
                            try {
                                index = Integer.parseInt(input);
                                validInput = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter a valid integer.");
                            }
                        }
                        currentForest.cutTree(index);
                        break;
                    case 'G':
                        currentForest.yearGrowth();
                        break;
                    case 'R':
                        double maxHeight = 0;
                        try {
                            System.out.print("Height to reap from: ");
                            while (!scanner.hasNextDouble()) {
                                System.out.println("That is not an integer.");
                                scanner.next();
                                System.out.println("Height to reap from: ");
                            }
                            maxHeight = scanner.nextDouble();
                        } catch (InputMismatchException e) {
                            System.out.println("That is not an integer.");
                        }
                        scanner.nextLine();
                        currentForest.reapATree(maxHeight);
                        break;
                    case 'S':
                        currentForest.saveForest();
                        break;
                    case 'L':
                        break;
                    case 'N':
                        argsIndex++;
                        System.out.println("Moving to the next forest");
                        break;
                    case 'X':
                        System.out.println("Exiting the Forestry Simulation");
                        break;
                    default:
                        System.out.println("Invalid option, try again.");
                } // end of switch statement
                if (option == 'N' || option == 'L')
                    break;
            } while (option != 'X'); // end of do-while loop


        } // end of while loop


    } // end of main


} // end of forestrySimulation class



