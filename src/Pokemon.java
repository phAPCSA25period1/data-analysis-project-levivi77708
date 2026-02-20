/**
 * Represents one Pokémon row from the dataset.
 */
public class Pokemon {

    private final String name;
    private final String primaryType;
    private final String secondaryType;
    private final int total;

    /**
     * Constructs a Pokemon object.
     *
     * @param name the Pokémon name
     * @param primaryType the primary type
     * @param secondaryType the secondary type (may be empty)
     * @param total the total stats value (or -1 if unknown)
     */
    public Pokemon(String name, String primaryType, String secondaryType, int total) {
        this.name = name;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
        this.total = total;
    }

    /**
     * Gets the Pokémon name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the primary type.
     *
     * @return the primary type
     */
    public String getPrimaryType() {
        return primaryType;
    }

    /**
     * Gets the secondary type.
     *
     * @return the secondary type, or an empty string if none
     */
    public String getSecondaryType() {
        return secondaryType;
    }

    /**
     * Gets the total stats value.
     *
     * @return the total stats, or -1 if unknown
     */
    public int getTotal() {
        return total;
    }

    /**
     * Returns a readable representation of this Pokémon.
     *
     * @return a string containing Pokémon data
     */
    @Override
    public String toString() {
        return name + " (" + primaryType + (secondaryType == null || secondaryType.isEmpty() ? "" : "/" + secondaryType)
                + ")";
    }
}