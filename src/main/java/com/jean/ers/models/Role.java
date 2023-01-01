package com.jean.ers.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "roles")
public class Role {
    @Id
    private String id;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToMany(
            mappedBy = "role",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<User> users;

    public Role(String id, String role) {
        this.id = id;
        this.role = role;
    }
}
