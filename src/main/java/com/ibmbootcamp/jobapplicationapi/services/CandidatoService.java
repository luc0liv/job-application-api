package com.ibmbootcamp.jobapplicationapi.services;

import com.ibmbootcamp.jobapplicationapi.Entity.Candidato;
import com.ibmbootcamp.jobapplicationapi.dtos.CandidatoPayloadDTO;
import com.ibmbootcamp.jobapplicationapi.dtos.CandidatoResponseDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class CandidatoService {
  public List<Candidato> candidatos = new ArrayList<>();
  private static final List<String> aprovados = new ArrayList<>();
  private int newId = 1;

  public CandidatoResponseDTO iniciarProcesso(CandidatoPayloadDTO candidatoDTO) {
    if (candidatoDTO.nome() == null || candidatoDTO.nome().isEmpty()) {
      throw new RuntimeException("Nome inválido.");
    }

    for (Candidato candidato : candidatos) {
      if (candidato.getNome().equals(candidatoDTO.nome())) {
        throw new RuntimeException("Candidato já participa do processo.");
      }
    }

    Candidato novoCandidato = new Candidato();
    novoCandidato.setNome(candidatoDTO.nome());
    novoCandidato.setStatus("Recebido");
    novoCandidato.setCodCandidato(newId++);

    candidatos.add(novoCandidato);
    return CandidatoResponseDTO.fromEntity(novoCandidato);
  }

  public void marcarEntrevista(CandidatoPayloadDTO candidato) {
    boolean encontrado = false;

    for (Candidato cand : candidatos) {
      if (cand.getCodCandidato() == candidato.codCandidato() && cand.getStatus().equals("Recebido")) {
        cand.setStatus("Qualificado");
        encontrado = true;
        break;
      }
    }

    if (!encontrado) {
      throw new RuntimeException("Candidato não encontrado");
    }
  }

  public void desqualificarCandidato(CandidatoPayloadDTO candidato) {
    boolean encontrado = false;
    for (int i = 0; i < candidatos.size(); i++) {
      if (Objects.equals(candidatos.get(i).getCodCandidato(), candidato.codCandidato())
      && candidatos.get(i).getStatus().equals("Qualificado")) {
        candidatos.remove(i);
        encontrado = true;
        break;
      }
    }

    if (!encontrado) {
      throw new RuntimeException("Candidato não encontrado");
    }
  }

  public void aprovarCandidato(CandidatoPayloadDTO candidato) {
    boolean encontrado = false;
    for (Candidato cand : candidatos) {
      if (Objects.equals(cand.getCodCandidato(), candidato.codCandidato()) && cand.getStatus().equals("Qualificado")) {
          cand.setStatus("Aprovado");
          encontrado = true;
          break;
        }
//      }
    }

    if (!encontrado) {
      throw new RuntimeException("Candidato não encontrado");
    }
  }

  public String verificarStatusCandidato(int codCandidato) {
    boolean encontrado = false;
    String statusDoCandidato = "";
    for (Candidato cand : candidatos) {
      if (Objects.equals(cand.getCodCandidato(), codCandidato)) {
        statusDoCandidato = cand.getStatus();
        encontrado = true;
      }
    }
    if (!encontrado) {
      throw new RuntimeException("Candidato não encontrado");
    }
    return statusDoCandidato;
  }

  public List<String> obterAprovados() {
    for (Candidato candidato : candidatos) {
      if (Objects.equals(candidato.getStatus(), "Aprovado")) {
        aprovados.add(candidato.getNome());
      }
    }

    return aprovados;
  }
}
