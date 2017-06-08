package com.example.demo.services.impl;


import com.example.demo.entities.VoterInfo;
import com.example.demo.repository.VoterInfoReposiroty;
import com.example.demo.services.VoterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoterInfoServiceImpl implements VoterInfoService<VoterInfo> {
    private final VoterInfoReposiroty voterInfoReposiroty;

    @Autowired
    public VoterInfoServiceImpl(VoterInfoReposiroty voterInfoReposiroty) {
        this.voterInfoReposiroty = voterInfoReposiroty;
    }

    @Override
    public void save(VoterInfo VoterInfo) {

    }

    @Override
    public VoterInfo findById(Long id) {
        return voterInfoReposiroty.findOne(id);
    }
}
