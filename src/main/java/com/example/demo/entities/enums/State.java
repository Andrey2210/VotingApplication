package com.example.demo.entities.enums;

/**
 * Types of states for Voting
 */
public enum State {
    ACTIVE("Active"),
    LOCKED("Locked");

    private String state;

    private State(final String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    @Override
    public String toString() {
        return this.state;
    }

}
