package com.turkcell.rentacar.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.turkcell.rentacar.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "brands")
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler","models"})
public class Brand extends BaseEntity {
    @Column (name="name")
    private String name;

    @OneToMany(mappedBy = "brand",cascade = CascadeType.ALL)
    private List<Model> models;
}