package com.kaio.agendadortarefas.business.mapper;

import com.kaio.agendadortarefas.business.dto.TarefaDTO;
import com.kaio.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateMapper {

    void updateTarefas(TarefaDTO tarefaDTO, @MappingTarget TarefaEntity tarefaEntity);
}
