package com.kaio.agendadortarefas.business.mapper;


import com.kaio.agendadortarefas.business.dto.TarefaDTO;
import com.kaio.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

    TarefaEntity paraTarefaEntity (TarefaDTO tarefaDTO);

    TarefaDTO paraTarefaDTO (TarefaEntity tarefaEntity);
}
