package org.example.rest;

import org.example.rest.config.Config;
import org.example.rest.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        Communication communication = applicationContext.getBean("communication", Communication.class);



        System.out.println(communication.key());


        //result = communication.saveUsers(new User(3l,"James","Brown",(byte)25));
        //result += communication.updateUsers(new User(3l,"Thomas","Shelby",(byte)25),session);

    }
}
