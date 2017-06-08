package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "T_VOTERINFO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoterInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_VOTER_ID")
    private Long id;

    @Email
    @Column(name = "F_IP_ADDRESS", nullable = false, unique = true)
    private String ipAddress;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "F_VOTED_DATE")
    private Date votedDate;

    @ManyToMany(mappedBy = "voterInfos")
    private Set<Answer> answers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        VoterInfo voterInfo = (VoterInfo) o;

        return id != null ? id.equals(voterInfo.id) : voterInfo.id == null &&
                (ipAddress != null ? ipAddress.equals(voterInfo.ipAddress) : voterInfo.ipAddress == null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ipAddress != null ? ipAddress.hashCode() : 0);
        return result;
    }
}
