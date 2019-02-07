package edu.metrostate.ics425.atu588.hw5.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author adam
 */
public class MancalaBean implements Serializable {
    private Player playerTurn;
    private Player winner;
    private final BoardBean board;
    
    private boolean invalidMove(PitBean pit) {
        return pit.getOwner() != playerTurn 
                || pit.getSeeds() < 1 
                || pit.isStore() 
                || isFinished();
    }
    
    private void changePlayer() {
        playerTurn = Player.flip(playerTurn);
    }
    
    private void tryNeighborCapture(PitBean lastPit) {
        if (!lastPit.isStore() 
                && lastPit.getSeeds() == 1
                && lastPit.getOwner() == playerTurn ) {
            ArrayList<PitBean> playerPits = board.getPlayerHouses(playerTurn);
            ArrayList<PitBean> opponentPits = board.getPlayerHouses(Player.flip(playerTurn));
            
            int index = playerPits.indexOf(lastPit);
            PitBean opponentPit = opponentPits.get(index);
            
            if (opponentPit.getSeeds() > 0) {
                int seedsStolen = opponentPit.takeSeeds() + lastPit.takeSeeds();
                PitBean playerStore = board.getPlayersStore(playerTurn);
                playerStore.addSeeds(seedsStolen);
            }
        }
    }
    
    private void endGame() {
        int otherHouses = board.takeAllHouseSeeds(Player.flip(playerTurn));
        board.getPlayersStore(Player.flip(playerTurn)).addSeeds(otherHouses);
        
        int redSeeds = board.getPlayersStore(Player.RED).getSeeds();
        int blueSeeds = board.getPlayersStore(Player.BLUE).getSeeds();
        if (redSeeds != blueSeeds) {
            winner = redSeeds > blueSeeds ? Player.RED : Player.BLUE;
        }
    }
    
    public MancalaBean() {
        super();
        playerTurn = Player.RED;
        board = new BoardBean();
    }
    
    /**
     * Gets the game board
     * @return board
     */
    public BoardBean getBoard() {
        return board;
    }
    
    /**
     * Get the current Player who's turn it is
     * @return player
     */
    public Player getPlayerTurn() {
        return playerTurn;
    }
    
    /**
     * Gets the Player who won, returns null if tie
     * @return player or null
     */
    public Player getWinner() {
        return winner;
    }
    
    /**
     * Gets the RED Player's score
     * @return score
     */
    public int getRedScore() {
        return board.getPlayersStore(Player.RED).getSeeds();
    }
    
    /**
     * Gets the BLUE Player's score
     * @return score
     */
    public int getBlueScore() {
        return board.getPlayersStore(Player.BLUE).getSeeds();
    }
    
    /**
     * Check if the game is finished
     * @return whether game is finished or not
     */
    public boolean isFinished() {
        return board.getTotalHouseSeeds(Player.RED) == 0
                || board.getTotalHouseSeeds(Player.BLUE) == 0;
    }
    
    /**
     * Make a move in the game
     * @param pit - index of the pit to play
     * @throws InvalidMoveException - if selected pit is an invalid move
     */
    public void makeMove(int pit) throws InvalidMoveException {
        ArrayList<PitBean> pits = board.getPits();
        PitBean selectedPit;
        try {
            selectedPit = pits.get(pit);
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidMoveException("Pit does not exist!");
        }
        
        if (invalidMove(selectedPit)) {
            throw new InvalidMoveException();
        }
        
        int seeds = selectedPit.takeSeeds();
        int firstPitIndex = pit - 1;
        int finalPitIndex = pit - seeds;
        int i;
        PitBean lastPit = null;
        // looping through array backwards so it is counter clockwise
        for (i = firstPitIndex; i >= finalPitIndex; i--) {
            // once begining of array is reached, go back to the end
            int arrayIndex = i < 0 ? pits.size() - (-i) : i;
            lastPit = pits.get(arrayIndex);
            
            if (lastPit.isStore() && lastPit.getOwner() != playerTurn) {
                // skip this pit, add to final pit to make up for the skipped pit
                finalPitIndex--;
            } else {
                lastPit.addSeeds(1);
            }
        }
        
        // capture if last pit was empty and neighbor has seeds
        tryNeighborCapture(lastPit);
        
        if (isFinished()) {
            endGame();
            return;
        }
        
        // don't change player if the last pit was their store
        if ( !(lastPit.isStore() && lastPit.getOwner() == playerTurn) ) {
            changePlayer();
        }
    }
}
