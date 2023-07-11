package com.ibmbootcamp.jobapplicationapi.dtos;

import com.ibmbootcamp.jobapplicationapi.Entity.Candidato;

public record CandidatoResponseDTO(int codCandidato) {

  public static CandidatoResponseDTO fromEntity(Candidato candidato) {
    return new CandidatoResponseDTO(candidato.getCodCandidato());
  }
}
