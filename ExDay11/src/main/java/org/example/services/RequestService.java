package org.example.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class RequestService {

    private int count;

    public int getCount() {
        return ++count;
    }
    @PostConstruct
    public void init(){
        System.out.println("Reques object created");
    }

    @PreDestroy
    public void Kill(){
        System.out.println("Request object Will be Killed");
    }
}