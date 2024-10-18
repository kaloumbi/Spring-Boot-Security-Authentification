package com.abs.SpringSecurityJWT.enitty;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "nom")
    private String nom;

    @Column(name = "tel", unique = true)
    private String tel;

    @Column(name = "login", unique = true)
    @NotNull(message = "The User should have an email !")
    @Email(message = "You have to provide a valid email ! ")
    private String login;

    @Column(name = "password")
    private String password;

    /*@Column(name = "role")
    private String role;*/

    @Column(name = "etat")
    private String etat;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles = new ArrayList<>();




    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_association",
            joinColumns = @JoinColumn(name = "user_login", referencedColumnName = "login"),
            inverseJoinColumns = @JoinColumn(name = "association_id", referencedColumnName = "id")
    )
    private List<Association> associations = new ArrayList<>() ;

















    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return List.of(new SimpleGrantedAuthority(roles.toString())); //modification du champ roles toString
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getNom()))
                .collect(Collectors.toList());
    }



    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
