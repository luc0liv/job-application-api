package com.ibmbootcamp.jobapplicationapi.services;

import com.ibmbootcamp.jobapplicationapi.Entity.Candidato;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CandidatoService {
  public List<Candidato> candidatos = new ArrayList<>();
  private long newId = 1;

  public int iniciarProcesso(String nome) throws Exception {
    if (nome == null || nome.isEmpty()) {
      System.out.println("passou aqui");
      throw new Exception("Nome inválido.");
    }

    for (Candidato candidato : candidatos) {
      if (candidato.getNome().equals(nome)) {
        throw new Exception("Candidato já participa do processo.");
      }
    }

    Candidato novoCandidato = new Candidato();
    novoCandidato.setNome(nome);
    novoCandidato.setStatus("Recebido");
    novoCandidato.setId((int) newId++);


    candidatos.add(novoCandidato);
//    System.out.println(novoCandidato.getStatus());
//    System.out.println("Candidato: " + novoCandidato.getNome() + "," + novoCandidato.getId());
//    System.out.println("candidatos: " + candidatos.size());
    return novoCandidato.getId();
  }
}
