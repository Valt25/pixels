package ru.simbirsoft.summerintensive.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "pixels")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pixel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int x;
    private int y;
    private String color;
    private LocalDateTime created;

    @ManyToOne
    private User user;

    public String getColor() {
        return color;
    }

    public User getUser() {
        return  user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
