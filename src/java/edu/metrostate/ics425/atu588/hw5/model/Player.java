package edu.metrostate.ics425.atu588.hw5.model;

/**
 *
 * @author adam
 */
public enum Player {
    RED ("RED"),
    BLUE ("BLUE");
    
    String str;
    Player(String str) {
        this.str = str;
    }
    
    /**
     * Return the opposite player
     * @param player
     * @return opposite player
     */
    public static Player flip(Player player) {
        return player == RED ? BLUE : RED;
    }
    
    @Override
    public String toString() {
        return str;
    }
}
