package com.example.demo.repository;

import com.example.demo.entities.Voting;
import com.example.demo.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This interface defines special methods for working with the entity Voting
 *
 */
public interface VotingRepository extends JpaRepository<Voting, Long> {

    /**
     * This method returns List of Voting objects by state
     *
     * @param state Voting State(ACTIVE or LOCKED)
     * @return List of Voting
     */
    List<Voting> findByState(State state);

}
