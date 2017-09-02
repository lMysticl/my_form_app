package com.logiston.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
public class Theme implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long themeId;

    private String title;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "theme_comments",
            joinColumns = {@JoinColumn(name = "themeId")},
            inverseJoinColumns = {@JoinColumn(name = "commentId")})
    private List<Comment> comments = new ArrayList<>();

}
