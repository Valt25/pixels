package ru.simbirsoft.summerintensive.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Pixel {

    @Id
    private Long id;
    private int x;
    private int y;
    private Timestamp created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
