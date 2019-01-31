package com.pmone.demo.rest.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "categorySample", orphanRemoval = true)
  @JsonManagedReference
  private List<CategorySample> categorySamples;
}
