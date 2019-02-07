package edu.metrostate.ics425.atu588.hw5.model;

import java.io.Serializable;

/**
 *
 * @author adam
 */
public class PitBean implements Serializable {
    private final boolean isStore;
    private Player owner;
    private int seeds;
    
    public PitBean() {
        this(null, 0, false);
    }
    
    public PitBean(Player initialOwner, int initialSeeds, boolean isStore) {
        super();
        owner = initialOwner;
        seeds = initialSeeds;
        this.isStore = isStore;
    }
    
    /**
     * Check if Pit is a Store
     * @return if Pit is Store
     */
    public boolean isStore() {
        return isStore;
    }
    
    /**
     * Get the Player who owns Pit
     * @return owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Set the Pit's owner
     * @param owner 
     */
    protected void setOwner(Player owner) {
        this.owner = owner;
    }
    
    /**
     * Get the current count of seeds in Pit
     * @return count of seeds
     */
    public int getSeeds() {
        return seeds;
    }
    
    /**
     * Take all of the seeds out of the Pit
     * @return number of seeds taken
     */
    protected int takeSeeds() {
        int seedsTaken = seeds;
        seeds = 0;
        return seedsTaken;
    }
    
    /**
     * Add seeds to Pit
     * @param seedCount - count of seeds to add
     */
    protected void addSeeds(int seedCount) {
        seeds += seedCount;
    }
    
    @Override
    public String toString() {
        return "[ " + (isStore ? "Store" : "House") + ": " + seeds + " " + owner + " ]";
    }
}

 