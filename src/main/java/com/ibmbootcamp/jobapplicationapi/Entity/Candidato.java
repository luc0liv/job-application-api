package com.ibmbootcamp.jobapplicationapi.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Candidato {
  private int id;
  private String nome;
  private String status;

//  public Candidato(String nome, String status) {
//    this.nome = nome;
//    this.status = status;
//  }
}
