package com.example.demo.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "T_ANSWER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"voting"})
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ANSWER_ID")
    private Long id;

    @Column(name = "F_TEXT", nullable = false)
    private String text;

    @Column(name = "F_VOTERS_NUMBER")
    private int votersNumber;

    @ManyToOne
    @JoinColumn(name = "F_VOTING_ID", nullable = false)
    @JsonIgnore
    private Voting voting;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private Set<VoterInfo> voterInfos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Answer answer = (Answer) o;

        return id != null ? id.equals(answer.id) : answer.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
