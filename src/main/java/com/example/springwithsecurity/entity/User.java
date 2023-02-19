package com.example.springwithsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "email", nullable = false, length = 200, unique = true)
    private String email;
    @Column(name = "full_name")
    private String fullname;
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable (name = "users_roles",
            joinColumns = @JoinColumn (name = "user_id"),
            inverseJoinColumns = @JoinColumn (name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    public User(String email, String fullname, String password){
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }
    public void addRole(String roleName) {
        Role newRole = new Role(roleName);
        this.roles.add(newRole);
    }

}
