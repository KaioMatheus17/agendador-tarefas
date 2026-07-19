package com.kaio.agendadortarefas.controller;


import com.kaio.agendadortarefas.business.TarefaService;
import com.kaio.agendadortarefas.business.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO>gravaTarefas(@RequestBody TarefaDTO tarefaDTO,
                                                @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.gravaTarefas(token, tarefaDTO));
    }

}
