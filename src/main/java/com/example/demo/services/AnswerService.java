package com.example.demo.services;


import com.example.demo.entities.Answer;

/**
 * This interface defines special methods for AnswerService
 *
 * @param <T> Child of Answer class
 */
public interface AnswerService<T extends Answer> {

    /**
     * This method  call appropriate UserRepository method
     *
     * @param answer Answer's object for saving
     */
    void save(T answer);

    /**
     * This method returns Answer object by id
     *
     * @param id Answer id
     * @return Appropriate Answer object
     */
    T findById(long id);

    /**
     * This method adds a voice to the selected answer and
     * saves information about the voted user and sends him cookies with
     *
     * @param answer Answer object
     * @param ip  user's IP address
     *
     */
    void addVoice(T answer, String ip);
}
