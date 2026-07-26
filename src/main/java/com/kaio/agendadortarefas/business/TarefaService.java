package com.kaio.agendadortarefas.business;


import com.kaio.agendadortarefas.business.dto.TarefaDTO;
import com.kaio.agendadortarefas.business.mapper.TarefaMapper;
import com.kaio.agendadortarefas.business.mapper.TarefaUpdateMapper;
import com.kaio.agendadortarefas.infrastructure.entity.TarefaEntity;
import com.kaio.agendadortarefas.infrastructure.enums.StatusNotificacaoEnums;
import com.kaio.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.kaio.agendadortarefas.infrastructure.repository.TarefaRepository;
import com.kaio.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final JwtUtil jwtUtil;
    private final TarefaMapper tarefaMapper;
    private final TarefaUpdateMapper tarefaUpdateMapper;


    public TarefaDTO gravaTarefas(String token, TarefaDTO tarefaDTO) {
        String email = jwtUtil.extractUsername(token.substring(7));

        tarefaDTO.setDataCriacao(LocalDateTime.now());
        tarefaDTO.setStatusNotificacaoEnums(StatusNotificacaoEnums.PENDENTE);
        tarefaDTO.setEmailUsuario(email);
        TarefaEntity tarefaEntity = tarefaMapper.paraTarefaEntity(tarefaDTO);
        return tarefaMapper.paraTarefaDTO(tarefaRepository.save(tarefaEntity));

    }

    public List<TarefaDTO> buscaTarefasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {

        return tarefaMapper.paraListaTarefaDTO(
                tarefaRepository.findByDataEventoBetween(dataInicial, dataFinal));

    }

    public List<TarefaDTO> buscaTarefaPorEmail(String token) {

        String email = jwtUtil.extractUsername(token.substring(7));

        return tarefaMapper.paraListaTarefaDTO(
                tarefaRepository.findByemailUsuario(email));
    }

    public void deletaTarefa(String id) {

        try {
            tarefaRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por ID. ID não localizado: " + id, e.getCause());
        }

    }

    public TarefaDTO alteraStatus(StatusNotificacaoEnums status, String id) {
        try {
            TarefaEntity tarefaEntity = tarefaRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("ID não localizado: " + id));
            tarefaEntity.setStatusNotificacaoEnums(status);
            return tarefaMapper.paraTarefaDTO(tarefaRepository.save(tarefaEntity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status: " + id, e.getCause());
        }

    }

    public TarefaDTO updateTarefas(TarefaDTO tarefaDTO, String id) {
        try {
            TarefaEntity tarefaEntity = tarefaRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("ID não localizado: " + id));

            tarefaUpdateMapper.updateTarefas(tarefaDTO, tarefaEntity);
            return tarefaMapper.paraTarefaDTO(tarefaRepository.save(tarefaEntity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Id não localizado: " + id, e.getCause());
        }


    }

}
