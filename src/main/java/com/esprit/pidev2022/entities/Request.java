package com.esprit.pidev2022.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
public class Request implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idRequest;
    private String name;
    private Date date;
    private String state;
    private String message;

    @ManyToOne
    private User user;

}