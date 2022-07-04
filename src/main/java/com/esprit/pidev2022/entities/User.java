package com.esprit.pidev2022.entities;




import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
//@Entity
//@Table(name="users",
  //      uniqueConstraints = {
   //             @UniqueConstraint(columnNames = "name")
   //     })
public class User {
   // @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
   // @NotBlank
  //  @Size(min = 3, max = 20)
    private String name;
   // @NotBlank
   // @Size(min = 3, max = 20)
    private String lastName;
 //   @NotBlank
  //  @Size(max = 50)
  //  @Email
    private String email;
  //  @NotBlank
   // @Size(max=120)
    private String password;

    //@ManyToMany(fetch = FetchType.EAGER)
   // @JoinTable(name = "user_roles",
      //      joinColumns = @JoinColumn(name="user_id"),
     //       inverseJoinColumns = @JoinColumn(name = "role_id")
   // )
  //  private Set<Role> roles = new HashSet<>();

    //private Forum forum;
    @JsonIgnore
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    public List<Complaint> complaints;

    public User() {
    }

    public User(int idUser, String name, String lastName, String email, String password, Set<Role> roles) {
        this.idUser = idUser;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
      //  this.roles = roles;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  //  public Set<Role> getRoles() {
   //     return roles;
  ///  }

   // public void setRoles(Set<Role> roles) {
    //    this.roles = roles;
   // }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }



    @OneToMany(mappedBy = "user")
    public List<Request> requests;

    @OneToMany(mappedBy = "user")
    public List<product> products;

    @OneToMany(mappedBy = "client" , fetch = FetchType.LAZY)
    public List<Account> accounts;
}
*/