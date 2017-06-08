package com.example.demo.entities;


import com.example.demo.entities.enums.State;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "T_VOTING")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
public class Voting implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_VOTING_ID")
    private Long id;

    @Column(name = "F_QUESTION", nullable = false)
    private String question;

    @OneToMany(mappedBy = "voting", cascade = CascadeType.ALL)
    private Set<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "F_USER_ID", nullable = false)
    @JsonIgnore
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "F_STATE", nullable = false)
    private State state;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Voting voting = (Voting) o;

        return id != null ? id.equals(voting.id) : voting.id == null &&
                (question != null ? question.equals(voting.question) : voting.question == null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        return result;
    }
}
