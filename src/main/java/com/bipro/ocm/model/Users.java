package com.bipro.ocm.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class Users implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="username",unique = true, nullable=false)
    private String username;
    private String password;
    @Transient
    private String passwordConfirm;

    @ManyToMany
    @JoinTable(name="users_roles_join",
            joinColumns={@JoinColumn(name="users_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="roles_id", referencedColumnName="id")})
    private Set<Roles> roles;

	@Column(name="full_name", nullable=false)
	private String fullName;


	@Column(name="email",unique = true, nullable=false)
	private String email;

    @Column(name="phone",unique = true, nullable=false)
    private String phone;

    @Column(name="enabled", nullable=false)
    private boolean enabled = true;


}

