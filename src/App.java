import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Main application for the Data Analysis Mini‑Project.
 *
 * This program loads a Pokémon CSV dataset, analyzes the most common primary
 * and secondary types, and prints insights that answer the guiding question.
 */
public class App {

    /**
     * Runs the program and prints the analysis results.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Update this if your CSV is stored elsewhere.
        File file = new File("data/pokemon.csv");

        ArrayList<Data> pokemonList = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            if (!scanner.hasNextLine()) {
                System.out.println("The CSV file is empty.");
                return;
            }

            String headerLine = scanner.nextLine();
            String[] headers = splitCsvLine(headerLine);

            int nameIndex = findColumnIndex(headers, "Name");
            int primaryTypeIndex = findColumnIndex(headers, "Type 1", "Type1", "Primary Type", "PrimaryType");
            int secondaryTypeIndex = findColumnIndex(headers, "Type 2", "Type2", "Secondary Type", "SecondaryType");
            int totalIndex = findColumnIndex(headers, "Total", "Total Stats", "TotalStats");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = splitCsvLine(line);
                if (!hasRequiredColumns(parts, nameIndex, primaryTypeIndex)) {
                    continue;
                }

                String name = safeGet(parts, nameIndex).trim();
                String primaryType = safeGet(parts, primaryTypeIndex).trim();
                String secondaryType = secondaryTypeIndex >= 0 ? safeGet(parts, secondaryTypeIndex).trim() : "";
                int total = totalIndex >= 0 ? parseIntSafe(safeGet(parts, totalIndex).trim(), -1) : -1;

                if (name.isEmpty() || primaryType.isEmpty()) {
                    continue;
                }

                pokemonList.add(new Data(name, primaryType, secondaryType, total));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find the CSV file at: " + file.getPath());
            return;
        }

        if (pokemonList.isEmpty()) {
            System.out.println("No data rows were loaded. Check your CSV file format.");
            return;
        }

        Map<String, Integer> primaryCounts = countTypes(pokemonList, false);
        Map<String, Integer> secondaryCounts = countTypes(pokemonList, true);

        String mostCommonPrimary = findMostCommonKey(primaryCounts);
        String mostCommonSecondary = findMostCommonKey(secondaryCounts);

        Integer primaryCountValue = mostCommonPrimary.isEmpty() ? null : primaryCounts.get(mostCommonPrimary);
        Integer secondaryCountValue = mostCommonSecondary.isEmpty() ? null : secondaryCounts.get(mostCommonSecondary);
        int mostCommonPrimaryCount = primaryCountValue == null ? 0 : primaryCountValue;
        int mostCommonSecondaryCount = secondaryCountValue == null ? 0 : secondaryCountValue;
        int singleTypeCount = countSingleType(pokemonList);
        double averageTotal = computeAverageTotal(pokemonList);

        System.out.println("Rows loaded: " + pokemonList.size());
        System.out.println("Most common primary type: " + mostCommonPrimary + " (" + mostCommonPrimaryCount + ")");
        System.out.println("Most common secondary type: " + mostCommonSecondary + " (" + mostCommonSecondaryCount + ")");
        System.out.println("Single-type Pokémon count: " + singleTypeCount);

        if (averageTotal >= 0) {
            System.out.printf("Average total stats: %.2f%n", averageTotal);
        }

        System.out.println();
        System.out.println("Answer: The most common primary type is " + mostCommonPrimary
                + ", and the most common secondary type is " + mostCommonSecondary + ".");
    }

    /**
     * Splits a CSV line using a simple comma delimiter.
     *
     * @param line the CSV line
     * @return an array of column values
     */
    public static String[] splitCsvLine(String line) {
        return line.split(",", -1);
    }

    /**
     * Finds the index of a column name from a header row.
     *
     * @param headers array of header names
     * @param possibleNames possible header names for the column
     * @return the index if found; otherwise -1
     */
    public static int findColumnIndex(String[] headers, String... possibleNames) {
        for (int i = 0; i < headers.length; i++) {
            String header = headers[i].trim().toLowerCase();
            for (String possible : possibleNames) {
                if (header.equals(possible.toLowerCase())) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Checks that required columns exist for the current row.
     *
     * @param parts the CSV row split into parts
     * @param requiredIndices required column indices
     * @return true if all required indices are valid for this row
     */
    public static boolean hasRequiredColumns(String[] parts, int... requiredIndices) {
        for (int index : requiredIndices) {
            if (index < 0 || index >= parts.length) {
                return false;
            }
        }
        return true;
    }

    /**
     * Safely gets a column value or an empty string if out of range.
     *
     * @param parts the CSV row split into parts
     * @param index the column index
     * @return the column value or empty string
     */
    public static String safeGet(String[] parts, int index) {
        if (index < 0 || index >= parts.length) {
            return "";
        }
        return parts[index];
    }

    /**
     * Parses an integer, returning a fallback if parsing fails.
     *
     * @param value the string value
     * @param fallback the fallback value
     * @return parsed integer or fallback
     */
    public static int parseIntSafe(String value, int fallback) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    /**
     * Counts primary or secondary type frequencies.
     *
     * @param list list of Pokémon
     * @param useSecondary whether to count secondary types
     * @return a map of type name to count
     */
    public static Map<String, Integer> countTypes(ArrayList<Data> list, boolean useSecondary) {
        Map<String, Integer> counts = new HashMap<>();
        for (Data pokemon : list) {
            String type = useSecondary ? pokemon.getSecondaryType() : pokemon.getPrimaryType();
            if (type == null || type.trim().isEmpty()) {
                continue;
            }
            String normalized = type.trim();
            counts.put(normalized, counts.getOrDefault(normalized, 0) + 1);
        }
        return counts;
    }

    /**
     * Finds the key with the highest value in a map.
     *
     * @param counts map of string keys to counts
     * @return the key with the highest count, or an empty string if none
     */
    public static String findMostCommonKey(Map<String, Integer> counts) {
        String bestKey = "";
        int bestCount = -1;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > bestCount) {
                bestKey = entry.getKey();
                bestCount = entry.getValue();
            }
        }
        return bestKey;
    }

    /**
     * Counts Pokémon that do not have a secondary type.
     *
     * @param list list of Pokémon
     * @return number of single-type Pokémon
     */
    public static int countSingleType(ArrayList<Data> list) {
        int count = 0;
        for (Data pokemon : list) {
            String secondary = pokemon.getSecondaryType();
            if (secondary == null || secondary.trim().isEmpty()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Computes the average of the total stats column, if available.
     *
     * @param list list of Pokémon
     * @return average total stats, or -1 if no totals were available
     */
    public static double computeAverageTotal(ArrayList<Data> list) {
        int count = 0;
        int sum = 0;
        for (Data pokemon : list) {
            int total = pokemon.getTotal();
            if (total >= 0) {
                sum += total;
                count++;
            }
        }
        if (count == 0) {
            return -1;
        }
        return (double) sum / count;
    }


}