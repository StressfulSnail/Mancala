package edu.metrostate.ics425.atu588.hw5.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author adam
 */
public class BoardBean implements Serializable {
    private PitBean redStore;
    private PitBean blueStore;
    
    private ArrayList<PitBean> allPits = new ArrayList();
    private ArrayList<PitBean> redHouses = new ArrayList();
    private ArrayList<PitBean> blueHouses = new ArrayList();
    
    public BoardBean() {
        super();
        redStore = new PitBean(Player.RED, 0, true);
        blueStore = new PitBean(Player.BLUE, 0, true);
        
        // pits are stored clockwise starting at the leftmost store
        allPits.add(redStore);
        for (int i = 0; i < 6; i++) {
            PitBean pit = new PitBean(Player.RED, 4, false);
            allPits.add(pit);
            redHouses.add(pit);
        }
        
        allPits.add(blueStore);
        for (int i = 0; i < 6; i++) {
            PitBean pit = new PitBean(Player.BLUE, 4, false);
            allPits.add(pit);
            blueHouses.add(pit);
        }
        
        // reverse to make up for counter-clockwiseness
        Collections.reverse(blueHouses);
    }
    
    /**
     * Get all pits on board
     * @return ArrayList of pits
     */
    public ArrayList<PitBean> getPits() {
        // return shallow copy
        return (ArrayList<PitBean>) allPits.clone();
    }
    
    /**
     * Get all House Pits owned by a given Player
     * @param player
     * @return ArrayList of pits
     */
    public ArrayList<PitBean> getPlayerHouses(Player player) {
        // return shallow copy
        return (ArrayList<PitBean>) (player == Player.RED ? redHouses.clone() : blueHouses.clone());
    }
    
    /**
     * Get Store Pit owned by a given Player
     * @param player
     * @return Store Pit
     */
    public PitBean getPlayersStore(Player player) {
        return player == Player.RED ? redStore : blueStore;
    }
    
    /**
     * Get the total amount of seeds a given Player has in their House Pits
     * @param player
     * @return number of seeds
     */
    public int getTotalHouseSeeds(Player player) {
        ArrayList<PitBean> houses = this.getPlayerHouses(player);
        return houses.stream()
                .map((pit) -> pit.getSeeds())
                .reduce(0, Integer::sum);      
    }
    
    /**
     * Takes all the seeds from a given Player's House Pits
     * @param player
     * @return number of seeds taken
     */
    protected int takeAllHouseSeeds(Player player) {
        ArrayList<PitBean> houses = this.getPlayerHouses(player);
        return houses.stream()
                .map((pit) -> pit.takeSeeds())
                .reduce(0, Integer::sum);  
    }
}
