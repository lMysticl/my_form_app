package com.logiston.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.transaction.Transactional;

/**
 * @author Pavel Putrenkov
 */
@Entity
@Table(name = "role")
@Getter
@Setter
@ToString(exclude = "id")
@Transactional
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "role")
    private String role;

}
