/**
 * Represents one row from the Pokemon dataset.
 *
 * <p>Each object stores a Pokemon's name, primary type, secondary type (if present),
 * and total stat value.
 */
public class Pokemon {

    /** The Pokemon name. */
    private final String name;

    /** The Pokemon primary type. */
    private final String primaryType;

    /** The Pokemon secondary type (empty if none). */
    private final String secondaryType;

    /** The Pokemon total stat value. */
    private final int total;

    /**
     * Creates a new Pokemon object.
     *
     * @param name the Pokemon name
     * @param primaryType the primary type
     * @param secondaryType the secondary type (use empty string if none)
     * @param total the total stat value
     */
    public Pokemon(String name, String primaryType, String secondaryType, int total) {
        this.name = name;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType == null ? "" : secondaryType;
        this.total = total;
    }

    /**
     * Returns the Pokemon name.
     *
     * @return the Pokemon name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the Pokemon primary type.
     *
     * @return the primary type
     */
    public String getPrimaryType() {
        return primaryType;
    }

    /**
     * Returns the Pokemon secondary type.
     *
     * @return the secondary type, or an empty string if none
     */
    public String getSecondaryType() {
        return secondaryType;
    }

    /**
     * Returns the Pokemon total stat value.
     *
     * @return the total stat value
     */
    public int getTotal() {
        return total;
    }

    /**
     * Returns whether this Pokemon has a secondary type.
     *
     * @return true if a secondary type exists, otherwise false
     */
    public boolean hasSecondaryType() {
        return !secondaryType.isEmpty();
    }

    /**
     * Returns a readable text representation of this Pokemon object.
     *
     * @return a formatted Pokemon summary
     */
    @Override
    public String toString() {
        String secondary = hasSecondaryType() ? secondaryType : "None";
        return "Pokemon{name='" + name + "', primaryType='" + primaryType
                + "', secondaryType='" + secondary + "', total=" + total + "}";
    }
}
