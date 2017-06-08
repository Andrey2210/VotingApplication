package com.example.demo.services;


import com.example.demo.entities.VoterInfo;

/**
 * This interface defines special methods for VoterInfo
 *
 * @param <T> Child of VoterInfo class
 */
public interface VoterInfoService<T extends VoterInfo> {

    void save(T VoterInfo);

    T findById(Long id);
}
