package com.kaio.agendadortarefas.business;


import com.kaio.agendadortarefas.business.dto.TarefaDTO;
import com.kaio.agendadortarefas.business.mapper.TarefaMapper;
import com.kaio.agendadortarefas.infrastructure.entity.TarefaEntity;
import com.kaio.agendadortarefas.infrastructure.enums.StatusNotificacaoEnums;
import com.kaio.agendadortarefas.infrastructure.repository.TarefaRepository;
import com.kaio.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final JwtUtil jwtUtil;
    private final TarefaMapper tarefaMapper;


    public TarefaDTO gravaTarefas (String token, TarefaDTO tarefaDTO){
        String email = jwtUtil.extractUsername(token.substring(7));

        tarefaDTO.setDataCriacao(LocalDateTime.now());
        tarefaDTO.setStatusNotificacaoEnums(StatusNotificacaoEnums.PENDENTE);
        tarefaDTO.setEmailUsuario(email);
        TarefaEntity tarefaEntity = tarefaMapper.paraTarefaEntity(tarefaDTO);
        return tarefaMapper.paraTarefaDTO(tarefaRepository.save(tarefaEntity));


    }

}
