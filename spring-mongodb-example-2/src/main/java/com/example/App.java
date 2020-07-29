package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.model.User;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private MongoTemplate mongoTemplate;
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
    
        User user = new User();
        user.setName("Eric");
        user.setAge(45);
        mongoTemplate.insert(user);
        user = new User();
        user.setName("Antony");
        user.setAge(55);
        mongoTemplate.insert(user);

        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("Eric"));
        List<User> users = mongoTemplate.find(query, User.class);

        System.out.println(users.size());
        
    }
    
}
