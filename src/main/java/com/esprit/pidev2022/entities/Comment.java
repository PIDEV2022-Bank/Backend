package com.esprit.pidev2022.entities;

import com.esprit.pidev2022.security.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date dateCreated;
    String contained;
    @ManyToOne(cascade = CascadeType.PERSIST)
    User user;
    @ManyToOne(cascade = CascadeType.PERSIST)
    Post post;
    @OneToMany
    private List<CommentChild> commentChildren = new ArrayList<>();

}
