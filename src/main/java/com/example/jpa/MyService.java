package com.example.jpa;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyService {

    private final Entity1Repo entity1Repo;
    private final MyService2 myService2;

    public MyService(Entity1Repo entity1Repo, MyService2 myService2) {
        this.entity1Repo = entity1Repo;
        this.myService2 = myService2;
    }

    @Transactional
    public void create(String s) {
        Entity1 entity1 = new Entity1();
        entity1.setName(s);
        Long id = entity1Repo.save(entity1).getId();
        myService2.create(id);
    }
}
