package com.kaio.agendadortarefas.infrastructure.repository;


import com.kaio.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends MongoRepository<TarefaEntity, String> {


}
