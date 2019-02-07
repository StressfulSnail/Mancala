package edu.metrostate.ics425.atu588.hw5.model;

/**
 * Exception to throw when an invalid move is made
 * @author adam
 */
public class InvalidMoveException extends Exception {

    /**
     * Creates a new instance of <code>InvalidMoveException</code> without
     * detail message.
     */
    public InvalidMoveException() {
    }

    /**
     * Constructs an instance of <code>InvalidMoveException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidMoveException(String msg) {
        super(msg);
    }
}
