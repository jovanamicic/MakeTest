package com.maketest.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jovana Micic on 01-Nov-16.
 */
@Entity
public class Authority {
    @Id
    @GeneratedValue
    private Long id;

    String name;

    @OneToMany(mappedBy = "authority", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set<UserAuthority> userAuthorities = new HashSet<UserAuthority>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserAuthority> getUserAuthorities() {
        return userAuthorities;
    }

    public void setUserAuthorities(Set<UserAuthority> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }
}
