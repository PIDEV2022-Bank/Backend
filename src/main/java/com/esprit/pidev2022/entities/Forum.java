package com.esprit.pidev2022.entities;
import com.esprit.pidev2022.security.model.User;
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

public class Forum implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     String title;
    Date dateCreated;

    @OneToMany(mappedBy = "forum")
    private List<Post> Posts = new ArrayList<>();
 @ManyToOne(cascade =  CascadeType.REMOVE)
@JoinColumn (name = "userId")
 User user;
}

