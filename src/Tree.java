import java.io.Serializable;


public class Tree implements Serializable {

    /**
     * represents a tree in a forest
     */
    TreeSpecies species;
    int yearOfPlanting;
    double height;
    double growthRate;


    /**
     * Constructor for creating a Tree object
     *
     * @param species        the tree species
     * @param yearOfPlanting year tree was planted
     * @param height         height of tree
     * @param growthRate     annual growth rate of the tree
     */


    public Tree(TreeSpecies species, int yearOfPlanting, double height, double growthRate) {
        this.species = species;
        this.yearOfPlanting = yearOfPlanting;
        this.height = height;
        this.growthRate = growthRate;
    } // end of tree class


    public TreeSpecies getSpecies() {

        return species;

    } // end of tree species

    public int getYearOfPlanting() {

        return yearOfPlanting;

    } // end of get year of tree planting

    /**
     * Gets height of the tree
     *
     * @return height of tree
     */

    public double getHeight() {

        return height;
    } // end of get height

    public double getGrowthRate() {

        return growthRate;
    } // end of get growth rate


    public void grow() {
        double growthFactor = 1 + (growthRate / 100);
        height *= growthFactor;
    } // end of grow

    /**
     * returns a string representation of the tree
     *
     * @return a string representation of the tree
     */

    @Override
    public String toString() {
        return String.format("g" +
                "%s   %d   %.2f'  %.1f%%", species, yearOfPlanting, height, growthRate);
    } // end of toString to format

    /**
     * returns a CSV representation of the tree to store in a file
     *
     * @return a CSV representation of tree
     */

    public String toCSV() {
        String str = species.toString();
        str = str.charAt(0) + str.substring(1).toLowerCase();
        return String.format("%s, %d, %.2f, %.1f\n", str, yearOfPlanting, height, growthRate);

    }

} // end of Tree class

