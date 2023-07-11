package com.ibmbootcamp.jobapplicationapi.services;

import com.ibmbootcamp.jobapplicationapi.Entity.Candidato;
import com.ibmbootcamp.jobapplicationapi.dtos.CandidatoPayloadDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class CandidatoService {
  public List<Candidato> candidatos = new ArrayList<>();
  private static final List<String> aprovados = new ArrayList<>();
  private int newId = 1;

  public int iniciarProcesso(CandidatoPayloadDTO candidatoDTO) throws Exception {
    if (candidatoDTO.nome() == null || candidatoDTO.nome().isEmpty()) {
      throw new Exception("Nome inválido.");
    }

    for (Candidato candidato : candidatos) {
      if (candidato.getNome().equals(candidatoDTO.nome())) {
        throw new Exception("Candidato já participa do processo.");
      }
    }

    Candidato novoCandidato = new Candidato();
    novoCandidato.setNome(candidatoDTO.nome());
    novoCandidato.setStatus("Recebido");
    novoCandidato.setCodCandidato(newId++);

    candidatos.add(novoCandidato);
    return candidatoDTO.codCandidato();
  }

  public void marcarEntrevista(int codCandidato) throws Exception {
    boolean encontrado = false;

    for (Candidato candidato : candidatos) {
      if (candidato.getCodCandidato() == codCandidato) {
        candidato.setStatus("Qualificado");
        encontrado = true;
        break;
      }
    }

    if (!encontrado) {
      throw new Exception("Candidato não encontrado");
    }
  }

  public void desqualificarCandidato(int codCandidato) throws Exception {
    boolean encontrado = false;
    for (int i = 0; i < candidatos.size(); i++) {
      if (Objects.equals(candidatos.get(i).getCodCandidato(), codCandidato)) {
        candidatos.remove(i);
        encontrado = true;
        break;
      }
    }

    if (!encontrado) {
      throw new Exception("Candidato não foi encontrado");
    }
  }

  public void aprovarCandidato(int codCandidato) throws Exception {
    boolean encontrado = false;
    for (Candidato candidato : candidatos) {
      if (Objects.equals(candidato.getCodCandidato(), codCandidato)) {
        if (Objects.equals(candidato.getStatus(), "Qualificado")) {
          candidato.setStatus("Aprovado");
          encontrado = true;
          break;
        }
      }
    }

    if (!encontrado) {
      throw new Exception("Candidato não encontrado");
    }
  }

  public String verificarStatusCandidato(int codCandidato) throws Exception {
    boolean encontrado = false;
    String statusDoCandidato = "";
    for (Candidato candidato : candidatos) {
      if (Objects.equals(candidato.getCodCandidato(), codCandidato)) {
        statusDoCandidato = candidato.getStatus();
        encontrado = true;
      }
    }
    if (!encontrado) {
      throw new Exception("Candidato não encontrado");
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
