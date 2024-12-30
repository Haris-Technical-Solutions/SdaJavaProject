package org.example.bookstore.models;

import org.example.bookstore.util.Model;
import java.math.BigInteger;

public class User extends Model<User> {
    // static String table = "users";

    public BigInteger id;
    public String name;
    public String email;
    public String password;
    public Integer is_admin;

    public User() {
        super.table("users");
    }

    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Is Admin: " + is_admin);
    }

}
