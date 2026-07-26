package com.kaio.agendadortarefas.controller;


import com.kaio.agendadortarefas.business.TarefaService;
import com.kaio.agendadortarefas.business.dto.TarefaDTO;
import com.kaio.agendadortarefas.infrastructure.enums.StatusNotificacaoEnums;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> gravaTarefas(@RequestBody TarefaDTO tarefaDTO,
                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.gravaTarefas(token, tarefaDTO));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTO>> buscaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
        return ResponseEntity.ok(tarefaService.buscaTarefasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscaTarefaPorEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.buscaTarefaPorEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTarefa(@RequestParam("id") String id) {
        tarefaService.deletaTarefa(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefaDTO> alteraStatus(@RequestParam("status") StatusNotificacaoEnums status,
                                                  @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.alteraStatus(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefaDTO> updateTarefas(@RequestBody TarefaDTO tarefaDTO,
                                                   @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.updateTarefas(tarefaDTO, id));
    }

}
