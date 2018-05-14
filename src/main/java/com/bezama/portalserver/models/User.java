package com.bezama.portalserver.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name="users")
public class Users {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name="email",unique=true,length=255)

    private string email;

    @Column(name="first_name",length=255)
    private string firstName;

    @Column(name="last_name",length=255)
    private string lastName;

    public string getEmail() {
        return email;
    }

    public void setEmail(string email) {
        this.email = email;
    }

    public string getFirstName() {
        return firstName;
    }

    public void setFirstName(string firstName) {
        this.firstName = firstName;
    }

    public string getLastName() {
        return lastName;
    }

    public void setLastName(string lastName) {
        this.lastName = lastName;
    }
}
