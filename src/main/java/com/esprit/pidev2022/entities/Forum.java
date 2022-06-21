package com.esprit.pidev2022.entities;
import lombok.*;
import lombok.experimental.FieldDefaults;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level=AccessLevel.PRIVATE)

@Entity

public class Forum implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     String title;
     int idUser;

}

