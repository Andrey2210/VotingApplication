package com.example.demo.repository;


import com.example.demo.entities.VoterInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface defines special methods for working with the entity VoterInfo
 *
 */
public interface VoterInfoReposiroty extends JpaRepository<VoterInfo, Long> {
}
