package tn.esprit.tpfoyer.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Chambre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idChambre;

    long numeroChambre;

    @Enumerated(EnumType.STRING)
    TypeChambre typeC;



    @OneToMany
    Set<Reservation> reservations;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bloc_id_bloc", referencedColumnName = "id")
    private Bloc bloc;


    public Chambre(String ch1, String ch2, Date date, TypeChambre typeChambre) {
    }
}
