import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Main application for the Data Analysis Mini‑Project.
 *
 * TODO:
 *  - Update the path to your dataset file
 *  - Read the CSV file using Scanner
 *  - Parse each row and extract the correct columns
 *  - Construct Pokemon objects from each row
 *  - Store them in an array
 *  - Write methods to analyze the dataset (min, max, average, filters, etc.)
 *  - Print insights and answer your guiding question
 *  - Add Javadoc comments for any methods you create
 */
public class App {

    /**
     * Loads Pokemon data from CSV, runs analysis, and prints results.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        File file = new File("data/pokemon.csv");

        Pokemon[] data = loadPokemonData(file);

        if (data.length == 0) {
            System.out.println("No Pokemon rows were loaded. Check your file path and data format.");
            return;
        }

        String mostCommonPrimary = findMostCommonType(data, true);
        String mostCommonSecondary = findMostCommonType(data, false);
        double averageTotal = computeAverageTotal(data);
        Pokemon strongest = findPokemonWithMaxTotal(data);

        System.out.println("Rows loaded: " + data.length);
        System.out.println("Most common primary type: " + mostCommonPrimary);
        System.out.println("Most common secondary type: " + mostCommonSecondary);
        System.out.printf("Average total stat: %.2f%n", averageTotal);
        System.out.println("Pokemon with highest total: " + strongest);

        System.out.println();
        System.out.println("Guiding question answer:");
        System.out.println("The most common primary type is " + mostCommonPrimary
                + ", and the most common secondary type is " + mostCommonSecondary + ".");
    }

    /**
     * Reads the CSV file and builds an array of Pokemon objects.
     *
     * @param file the CSV file to read
     * @return an array containing all successfully parsed Pokemon rows
     */
    public static Pokemon[] loadPokemonData(File file) {
        Pokemon[] temp = new Pokemon[16];
        int size = 0;

        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] columns = line.split(",", -1);
                if (columns.length < 4) {
                    continue;
                }

                String name = columns[0].trim();
                String primaryType = columns[1].trim();
                String secondaryType = columns[2].trim();

                int total;
                try {
                    total = Integer.parseInt(columns[3].trim());
                } catch (NumberFormatException ex) {
                    continue;
                }

                if (size == temp.length) {
                    temp = growArray(temp);
                }

                temp[size] = new Pokemon(name, primaryType, secondaryType, total);
                size++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + file.getPath());
            return new Pokemon[0];
        }

        return Arrays.copyOf(temp, size);
    }

    /**
     * Creates a larger array and copies all elements into it.
     *
     * @param original the original Pokemon array
     * @return a new array with double capacity
     */
    public static Pokemon[] growArray(Pokemon[] original) {
        return Arrays.copyOf(original, original.length * 2);
    }

    /**
     * Finds the most common type in the dataset.
     *
     * @param data the Pokemon array
     * @param usePrimary true for primary type, false for secondary type
     * @return the most common type label
     */
    public static String findMostCommonType(Pokemon[] data, boolean usePrimary) {
        Map<String, Integer> counts = new HashMap<>();

        for (Pokemon pokemon : data) {
            String type = usePrimary ? pokemon.getPrimaryType() : pokemon.getSecondaryType();

            if (!usePrimary && type.isEmpty()) {
                continue;
            }

            counts.put(type, counts.getOrDefault(type, 0) + 1);
        }

        if (counts.isEmpty()) {
            return "None";
        }

        String mostCommon = "";
        int highestCount = -1;

        for (String type : counts.keySet()) {
            int count = counts.get(type);
            if (count > highestCount) {
                highestCount = count;
                mostCommon = type;
            }
        }

        return mostCommon + " (" + highestCount + ")";
    }

    /**
     * Computes the average total stat across all Pokemon.
     *
     * @param data the Pokemon array
     * @return the average total stat value
     */
    public static double computeAverageTotal(Pokemon[] data) {
        if (data.length == 0) {
            return 0.0;
        }

        int sum = 0;
        for (Pokemon pokemon : data) {
            sum += pokemon.getTotal();
        }
        return (double) sum / data.length;
    }

    /**
     * Finds the Pokemon with the highest total stat value.
     *
     * @param data the Pokemon array
     * @return the Pokemon with the largest total, or null if empty
     */
    public static Pokemon findPokemonWithMaxTotal(Pokemon[] data) {
        if (data.length == 0) {
            return null;
        }

        Pokemon best = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i].getTotal() > best.getTotal()) {
                best = data[i];
            }
        }
        return best;
    }

}