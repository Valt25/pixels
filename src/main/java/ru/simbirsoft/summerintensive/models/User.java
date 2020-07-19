package ru.simbirsoft.summerintensive.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "project_user")
public class User implements Serializable, Comparable<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String country;
    private String city;
    private long cellsPainted; //дебаг

    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private LocalDateTime lastColorTime;

    public long getCellsPainted() {
        return cellsPainted;
    } // дебаг

    public void setCellsPainted(long cellsPainted) {
        this.cellsPainted = cellsPainted;
    } // дебаг
    public void incCellsPainted(){cellsPainted++;} // дебаг
    @Override
    public int compareTo(User o) {
        return (int) (id - o.id);
    }

    @Override
    public String toString() {
        return "Id: " + id + " name: " + name;
    }
}
