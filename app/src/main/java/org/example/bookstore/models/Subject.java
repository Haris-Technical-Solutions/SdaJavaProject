package org.example.bookstore.models;

import org.example.bookstore.util.Model;

public class Subject extends Model<Subject> {
    // static String table = "users";

    public java.math.BigInteger id;
    public String subject;
    public java.sql.Timestamp deleted_at;
    public java.sql.Timestamp created_at;
    public java.sql.Timestamp updated_at;

    public Subject() {
        super.table("subjects");
    }

    public void display() {
        System.out.println("ID: " + id);
        System.out.println("subject: " + subject);
        System.out.println("deleted_at: " + deleted_at);
    }

    // public Subject create() {
    //     this.subject = "Math";
    //     this.deleted_at = null;
    //     this.created_at = null;
    //     this.updated_at = null;
    //     return this;
    // }

}
