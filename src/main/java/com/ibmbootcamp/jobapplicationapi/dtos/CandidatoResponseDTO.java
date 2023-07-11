package com.ibmbootcamp.jobapplicationapi.dtos;

import com.ibmbootcamp.jobapplicationapi.Entity.Candidato;

public record CandidatoResponseDTO(int codCandidato, String nome) {

  public static CandidatoResponseDTO fromEntity(Candidato candidato) {
    return new CandidatoResponseDTO(candidato.getCodCandidato(), candidato.getNome());
  }
}
