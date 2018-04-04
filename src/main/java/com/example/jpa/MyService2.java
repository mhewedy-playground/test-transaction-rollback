package com.example.jpa;

import org.springframework.stereotype.Service;

@Service
public class MyService2 {

    private final Entity2Repo entity2Repo;

    public MyService2(Entity2Repo entity2Repo) {
        this.entity2Repo = entity2Repo;
    }

    public void create(Long entity1Id) {
        Entity2 entity2 = new Entity2();
        entity2.assignToEntity1(entity1Id);
        entity2Repo.save(entity2);
    }
}
