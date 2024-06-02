package org.example.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.User;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pokedex {
    @Id
    private int id;

    @OneToOne(mappedBy = "pokedex")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER,  cascade = CascadeType.MERGE)
    @JoinTable(name = "pokemon_in_dex", joinColumns = @JoinColumn(name = "id"),
    inverseJoinColumns = @JoinColumn(name = "dex_number"))
    private Set<Pokemon> pokemon = new HashSet<>();

}
