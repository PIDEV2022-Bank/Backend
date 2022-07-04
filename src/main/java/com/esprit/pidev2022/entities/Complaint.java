
package com.esprit.pidev2022.entities;

import com.esprit.pidev2022.security.model.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Complaint {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
private Long idComplaint;
private String Subject;
private String Message;
private String Status;
@ManyToOne
@JoinColumn(name = "idUser" ,referencedColumnName = "id")
private User user;
}