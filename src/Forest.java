import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * represents forest containing trees
 */

public class Forest implements Serializable {
    /**
     * name of forest
     */
    public String name;
    /**
     * list of trees in the forest
     **/
    private static List<Tree> trees;

    /**
     * Converts a string representation of tree species to the corresponding enum
     *
     * @param stringTreeSpecies The string representation of the tree species
     * @return TreeSpecies enum value
     */
    private TreeSpecies stringToEnum(String stringTreeSpecies) {
        switch (stringTreeSpecies) {
            case "Maple":
                return TreeSpecies.MAPLE;
            case "Fir":
                return TreeSpecies.FIR;
            case "Birch":
                return TreeSpecies.MAPLE;
        }
        return null;
    }

    /**
     * Constructor for creating a forest object from a file
     *
     * @param name the name of the forest
     * @param ext  The file extension indicating the format of the forest data
     * @throws FileNotFoundException if the file is not found
     */

    public Forest(String name, String ext) throws FileNotFoundException {
        this.name = name;
        this.trees = new ArrayList<>();

        File file = new File(name + ext);
        Scanner sc = new Scanner(file);
        String line;
        String[] data;

        while (sc.hasNextLine()) {
            line = sc.nextLine();
            data = line.split(",");
            trees.add(new Tree(stringToEnum(data[0]), Integer.parseInt(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3])));
        }
        sc.close();
    }

    /**
     * Adds a randomly generated tree to the forest
     */
    public void addTree() {
        TreeSpecies species = getRandomSpecies();
        int yearOfPlanting = getRandomYear();
        double height = getRandomHeight();
        double growthRate = getRandomGrowthRate();
        Tree newTree = new Tree(species, yearOfPlanting, height, growthRate);
        trees.add(newTree);
    } // end of add tree class

    /**
     * generates a random tree species
     *
     * @return a random TreeSpecies enum value
     */

    private TreeSpecies getRandomSpecies() {
        Random random = new Random();
        return TreeSpecies.values()[random.nextInt(TreeSpecies.values().length)];
    }

    /**
     * Generates random year between 2000 and 2024
     *
     * @return a random year
     */
    private int getRandomYear() {
        Random random = new Random();
        return 2025 - random.nextInt(25); // year between 2000 & 2024
    } // end of getRandomYear

    /**
     * Generates a random height between 10 and 20 ft
     *
     * @return random tree height
     */

    private double getRandomHeight() {
        Random random = new Random();
        return 10 + random.nextDouble() * 10; // return height between 10 & 20 ft
    } // end of getRandomHeight

    /**
     * Generates a random growth rate between 10 and 20%
     *
     * @return random growth rate
     */

    private double getRandomGrowthRate() {
        Random random = new Random();
        return 0.1 + random.nextDouble() * 0.1; // return growth rate between 10-20%
    } // end of getRandomGrowthRate

    /**
     * Removes a tree from the forest and a specific index
     *
     * @param index
     */
    public static void cutTree(int index) {

        if (index >= 0 && index < trees.size()) {
            trees.remove(index);

        } // end of if statement
        else {
            System.out.println("Tree number " + index + " does not exist");
        } // end of else statement

    } // end of cut tree

    /**
     * simulates a year of growth for all tress in forest
     */
    public void yearGrowth() {
        for (Tree tree : trees) {
            tree.grow();
        } // end of for
    } // end of yearGrowth


    /**
     * Prints details of the forest (name, tress, and average height)
     */

    public void printForest() {
        System.out.println("Forest name: " + name);
        for (int i = 0; i < trees.size(); i++) {
            System.out.printf("%5d %s\n", i, trees.get(i).toString());
        }// end of for loop
        double avgHeight = trees.stream().mapToDouble(Tree::getHeight).average().orElse(0);
        System.out.printf("There are %d trees, with an average height of %.2f\n", trees.size(), avgHeight);
    } // end of print forest


    /**
     * replaces trees taller than the max height with a new randomly generated tree
     *
     * @param heightToReap the maximum height for the trees to be reaped
     */

    public void reapATree(double heightToReap) {

        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            if (tree.getHeight() > heightToReap) {
                TreeSpecies species = getRandomSpecies();
                int yearOfPlanting = getRandomYear();
                double height = getRandomHeight();
                double growthRate = getRandomGrowthRate();
                Tree newTree = new Tree(species, yearOfPlanting, height, growthRate);
                trees.set(i, newTree);
                System.out.println("Reaping the tall tree " + i + " : " + tree);
                System.out.println("Replaced with new tree " + i + " : " + newTree);
            } // end of if statement
        } // end of for loop


    } // end of reapATree


    /**
     * Saves forest to file
     *
     * @throws IOException if an I/O error occurs while saving the forest
     */
    public void saveForest() throws IOException {
        FileWriter writer = new FileWriter(name + ".db");
        for (int i = 0; i < trees.size(); i++) {
            writer.write(trees.get(i).toCSV());
        } // end of for loop
        writer.close();
    } // end of save forest


} // end of Forest class



