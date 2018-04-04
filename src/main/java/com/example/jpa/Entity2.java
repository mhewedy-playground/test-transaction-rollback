package com.example.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Entity2 {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    private Entity1 entity1;

    public void assignToEntity1(Long entity1Id) {
        Entity1 entity1 = new Entity1();
        entity1.setId(entity1Id);
        this.entity1 = entity1;
    }

    @Override
    public String toString() {
        return "Entity2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", entity1=" + entity1 +
                '}';
    }
}
