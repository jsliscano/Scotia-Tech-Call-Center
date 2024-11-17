package com.scotiatech.demo.Service;

import com.scotiatech.demo.dto.ResponseDto;
import com.scotiatech.demo.dto.interfazDto.IAgendaDto;
import com.scotiatech.demo.entity.AgendaEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AgendaValidaService {

    ResponseDto<AgendaEntity> saveAgenda(MultipartFile file, Long userId, String facultad, String programa);

    List<IAgendaDto> findUserAgenda(Long userId);

    AgendaEntity findAllByUser(Long id);

    ResponseDto<List<IAgendaDto>> findProgramAgendasForDirector(Long programaId);

    ResponseDto<List<IAgendaDto>> findHistoricalProgramAgendasForDirector(Long programaId);

}

