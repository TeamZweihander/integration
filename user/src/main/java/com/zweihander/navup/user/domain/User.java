package com.zweihander.navup.user.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import org.hibernate.annotations.ColumnTransformer;

@Entity
@Table(name = "nav_user", schema = "User_module")
public class User implements Serializable {

    private static final long serialVersionUID = 6637087385433228063L;

    //Properties of User object to save in database

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    protected Long id;

    @Column(name="username", unique = true, length = 50, nullable=false)
    protected String username;

    @Column(name="password", length = 100, nullable=false)
    @ColumnTransformer(write = "crypt(?, gen_salt('bf',8))")
    protected String password;

    @Column(name="firstname", length = 50, nullable=false)
    protected String firstname;

    @Column(name="lastname", length = 50, nullable=false)
    protected String lastname;

    @Column(name="email", length = 50, nullable=false)
    protected String email;

    @Column(name="activated", columnDefinition="boolean NOT NULL DEFAULT true", nullable=false)
    protected boolean activated = true;

    @Column(name="activated_key", length = 50, nullable=true)
    protected String activated_key;

    @Column(name="reset_key", length = 50, nullable=true)
    protected String reset_key;

    @Column(name="reset_date", nullable=true)
    protected Date reset_date;

    @Column(name="is_admin", columnDefinition="boolean NOT NULL DEFAULT false", nullable=false)
    protected boolean is_admin;

    //Constructors

    public User() {
        super();
    }

    public User(Long id, String username, String password, String firstname, String lastname, String email) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.activated = true;
        this.is_admin = false;
    }

    public User(String username, String password, String firstname, String lastname, String email) {
        super();
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.activated = true;
        this.is_admin = false;
    }

    public User(String username, String password, String firstname, String lastname, String email, String activated_key,
                String reset_key) {
        super();
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.activated_key = activated_key;
        this.reset_key = reset_key;
        this.activated = true;
        this.is_admin = false;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", firstname=" + firstname + ", lastname="
                + lastname + ", email=" + email + ", activated=" + activated + ", is_admin=" + is_admin
                + ", activated_key=" + activated_key + ", reset_key=" + reset_key + ", reset_date=" + reset_date + "]";
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (id == null || obj == null || getClass() != obj.getClass())
            return false;
        User toCompare = (User) obj;
        return id.equals(toCompare.id);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public String getActivated_key() {
        return activated_key;
    }

    public void setActivated_key(String activated_key) {
        this.activated_key = activated_key;
    }

    public String getReset_key() {
        return reset_key;
    }

    public void setReset_key(String reset_key) {
        this.reset_key = reset_key;
    }

    public Date getReset_date() {
        return reset_date;
    }

    public void setReset_date(Date reset_date) {
        this.reset_date = reset_date;
    }


}