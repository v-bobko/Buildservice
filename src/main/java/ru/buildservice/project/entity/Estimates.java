package ru.buildservice.project.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "estimates")
public class Estimates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estimate_id")
    private int estimateId;


    @Column(name = "estimate")
    private String estimate;

    @Column(name = "name_of_estimate")
    private String nameOfEstimate;

    @ManyToOne
    @JoinColumn(name = "object_id")
    private Objects objects;

    public Estimates() {
    }

    public Estimates(String estimate, Objects objects) {
        this.estimate = estimate;
        this.objects = objects;
    }
}
