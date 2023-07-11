package com.ibmbootcamp.jobapplicationapi.controllers;

import com.ibmbootcamp.jobapplicationapi.Entity.Candidato;
import com.ibmbootcamp.jobapplicationapi.services.CandidatoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    System.out.println("startProcess: " + nome);
    return candidatoService.iniciarProcesso(nome);
  }
}
