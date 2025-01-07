package org.example.bookstore.controllers;

import org.example.bookstore.models.User;

import java.util.List;
import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.Controller;
import org.example.bookstore.views.Index;

import org.example.bookstore.views.Login;
import org.example.bookstore.views.Register;
import org.example.bookstore.views.Customer;

public class HomeController extends Controller{

    public HomeController() {
        // Subject subject  = new Subject();
                                                                                                                                                                                                                                                                                                             
        // Dictionary dict = new Dictionary();
        // dict.put("subject", "old 888");
        // Dictionary dict2 = new Dictionary();
        // dict2.put("subject", "new 335");

        // Subject subject2 = new Subject().where("id", "=", "21").update(dict);
        // Subject subject3 = new Subject().create(dict2);

        // System.out.println("updated\n");
        
        // List<Subject> subjects = subject.get();
        // for (Subject u : subjects) {
        //     System.out.println("User ID: " + u.id);
        //     System.out.println("User subject: " + u.subject);
        //     System.out.println("User deleted_at: " + u.deleted_at);
        // }

        switchAction(null);
        
    }

    public static void switchAction(Integer choice){
        if(choice == null){
            choice  = Index.menu();
        }
        switch(choice){
            case 1:
                while(true){
                    Dictionary login = Login.menu();
                    User user = AuthController.login(login);

                    if(user == null){
                        println("Invalid email or password");
                        if(!tryAgain()){
                            break;
                        }
                    }
                    if(user != null){
                        while(true){
                            Integer op = Index.menuAfterLogin(user);
                            if(user.is_admin == 1){
                                AdminController.switchAction(op, user);
                            }else{
                                CustomerController.switchAction(op, user);
                            }
                        }
                    }
                }
                switchAction(null);
                break;
            case 2:
                while(true){
                    Dictionary register = Register.menu();
                    if(!AuthController.register(register)){
                        println("User Already exists\n");
                        if(!tryAgain()){
                            break;
                        }
                    }else{
                        break;
                    }
                }   
                switchAction(null);
                break;
            case 0:
                break;
            default:
                println("Invalid choice");
                switchAction(null);
        }
    }



   
}
