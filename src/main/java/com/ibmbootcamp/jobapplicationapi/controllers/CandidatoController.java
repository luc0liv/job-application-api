package com.ibmbootcamp.jobapplicationapi.controllers;

import com.ibmbootcamp.jobapplicationapi.Entity.Candidato;
import com.ibmbootcamp.jobapplicationapi.services.CandidatoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
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
}
