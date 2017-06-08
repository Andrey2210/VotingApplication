package com.example.demo.services;

import com.example.demo.entities.Voting;

import java.util.List;

/**
 * This interface defines special methods for VotingService
 *
 * @param <T> Child of Voting class
 */
public interface VotingService<T extends Voting> {

    /**
     * This method defines some actions before saving Voting's object
     * and then call appropriate VotingRepository method
     *
     * @param voting Voting's object for saving
     */
    T save(T voting);

    /**
     * This method returns all Votings
     *
     * @return List of Voting's objects
     */
    List<T> findAllActiveVotes();

    T findById(long id);

    void closeVote(T voting);
}