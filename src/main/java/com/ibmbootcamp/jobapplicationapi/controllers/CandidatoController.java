package com.ibmbootcamp.jobapplicationapi.controllers;

import com.ibmbootcamp.jobapplicationapi.Entity.Candidato;
import com.ibmbootcamp.jobapplicationapi.dtos.CandidatoPayloadDTO;
import com.ibmbootcamp.jobapplicationapi.services.CandidatoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/hiring")
public class CandidatoController {
  @Autowired
  private CandidatoService candidatoService;

  @PostMapping(path = "start")
  public int startProcess(@RequestBody CandidatoPayloadDTO candidato) throws Exception {
//    String nome = candidato.nome();
    return candidatoService.iniciarProcesso(candidato);
  }

  @PostMapping(path = "schedule")
  public void scheduleInterview(@RequestBody Candidato candidato) throws Exception {
    int codCandidato = candidato.getCodCandidato();
    candidatoService.marcarEntrevista(codCandidato);
  }

  @PostMapping(path = "disqualify")
  public void disqualifyCandidate(@RequestBody Candidato candidato) throws Exception {
    int codCandidato = candidato.getCodCandidato();
    candidatoService.desqualificarCandidato(codCandidato);
  }

  @PostMapping(path = "approve")
  public void approveCandidate(@RequestBody Candidato candidato) throws Exception {
    int codCandidato = candidato.getCodCandidato();
    candidatoService.aprovarCandidato(codCandidato);
  }

  @GetMapping(path = "status/candidate/{codCandidato}")
  public String getCandidateStatus(@PathVariable int codCandidato) throws Exception {
    return candidatoService.verificarStatusCandidato(codCandidato);
  }

  @GetMapping(path = "approved")
  public List<String> getApprovedCandidates() throws Exception {
    return candidatoService.obterAprovados();
  }
}
