package ru.solonchev.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String description;
    @Column(name = "img_link", nullable = false)
    private String imgLink;
    @ManyToMany(mappedBy = "cars")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        this.users.add(user);
        user.getCars().add(this);

    }

    public void removeUser(User user) {
        user.getCars().remove(this);
        users.remove(user);
    }
}
