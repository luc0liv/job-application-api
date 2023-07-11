package com.ibmbootcamp.jobapplicationapi.controllers;

import com.ibmbootcamp.jobapplicationapi.Entity.Candidato;
import com.ibmbootcamp.jobapplicationapi.services.CandidatoService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/hiring")
public class CandidatoController {
  private final CandidatoService candidatoService;

  public CandidatoController(CandidatoService candidatoService) {
    this.candidatoService = candidatoService;
  }

  @PostMapping(path = "start")
  public int startProcess(@RequestBody Candidato candidato) throws Exception {
    String nome = candidato.getNome();
    return candidatoService.iniciarProcesso(nome);
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
