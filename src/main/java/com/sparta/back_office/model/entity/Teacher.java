package com.sparta.back_office.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teachers")
@NoArgsConstructor
@Getter
public class Teacher {
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private int career;

    @Column
    private String company;

    @Column
    private int phone_number;

    @Column
    private String intro;
}
