package org.hibernate.bugs.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(
        mappedBy = "parent",
        cascade = {CascadeType.MERGE}
    )
    @OrderColumn
    private List<Child> children;

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }
}
