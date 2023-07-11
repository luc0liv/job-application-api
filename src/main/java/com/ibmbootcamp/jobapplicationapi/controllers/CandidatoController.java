package com.ibmbootcamp.jobapplicationapi.controllers;

import com.ibmbootcamp.jobapplicationapi.Entity.Candidato;
import com.ibmbootcamp.jobapplicationapi.dtos.CandidatoPayloadDTO;
import com.ibmbootcamp.jobapplicationapi.dtos.CandidatoResponseDTO;
import com.ibmbootcamp.jobapplicationapi.services.CandidatoService;

import java.util.HashMap;
import java.util.List;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/hiring")
public class CandidatoController {
  @Autowired
  private CandidatoService candidatoService;

  @PostMapping(path = "start")
  @ResponseStatus(code = HttpStatus.CREATED)
  public CandidatoResponseDTO startProcess(@RequestBody CandidatoPayloadDTO candidato) {
    return candidatoService.iniciarProcesso(candidato);
  }

  @PostMapping(path = "schedule")
  @ResponseStatus(code = HttpStatus.CREATED)
  public Map<String, String> scheduleInterview(@RequestBody CandidatoPayloadDTO candidato) {
    candidatoService.marcarEntrevista(candidato);
    return new HashMap<>(){
      {
        put("message", "Entrevista marcada");
      }
    };
  }

  @PostMapping(path = "disqualify")
  @ResponseStatus(code = HttpStatus.CREATED)
  public Map<String, String> disqualifyCandidate(@RequestBody CandidatoPayloadDTO candidato) {
    candidatoService.desqualificarCandidato(candidato);
    return new HashMap<>(){
      {
        put("message", "Candidato desqualificado");
      }
    };
  }

  @PostMapping(path = "approve")
  @ResponseStatus(code = HttpStatus.CREATED)
  public Map<String, String> approveCandidate(@RequestBody CandidatoPayloadDTO candidato) {
    candidatoService.aprovarCandidato(candidato);
    return new HashMap<>(){
      {
        put("message", "Candidato aprovado");
      }
    };
  }

  @GetMapping(path = "status/candidate/{codCandidato}")
  @ResponseStatus(code = HttpStatus.OK)
  public Map<String, String> getCandidateStatus(@PathVariable int codCandidato) {
    String status = candidatoService.verificarStatusCandidato(codCandidato);
    return new HashMap<>(){
      {
        put("status", status);
      }
    };
  }

  @GetMapping(path = "approved")
  @ResponseStatus(code = HttpStatus.OK)
  public List<String> getApprovedCandidates() throws Exception {
    return candidatoService.obterAprovados();
  }
}
