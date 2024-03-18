package org.hibernate.bugs.entity;

import jakarta.persistence.*;

@Entity
public class Child {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
