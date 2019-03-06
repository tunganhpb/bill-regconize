package com.pmone.demo.rest.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Bill {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "super_market")
  private String superMarket;

  @Column(name = "sum")
  private Double sum;

  @Column
  private Date createdTime;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "bill", orphanRemoval = true)
  @JsonManagedReference
  private List<Item> items;

}
