package org.example.bookstore.controllers;

import org.example.bookstore.models.User;
import org.example.bookstore.models.Subject;
import java.util.List;
import org.example.bookstore.util.Dictionary;

public class Home {

    public Home() {
        Subject subject  = new Subject();

        Dictionary dict = new Dictionary();
        dict.put("subject", "old 888");
        Dictionary dict2 = new Dictionary();
        dict2.put("subject", "new 335");

        Subject subject2 = new Subject().where("id", "=", "21").update(dict);
        Subject subject3 = new Subject().create(dict2);

        System.out.println("updated\n");
        
        List<Subject> subjects = subject.get();
        for (Subject u : subjects) {
            System.out.println("User ID: " + u.id);
            System.out.println("User subject: " + u.subject);
            System.out.println("User deleted_at: " + u.deleted_at);
        }
    }


   
}
