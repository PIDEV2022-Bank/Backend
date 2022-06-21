
package com.esprit.pidev2022.entities;

import javax.persistence.*;

@Entity
public class Complaint {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
private int idComplaint;
private String Subject;
private String Message;
private String Status;
@ManyToOne
private User user;
}