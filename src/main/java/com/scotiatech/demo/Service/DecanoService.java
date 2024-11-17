package com.scotiatech.demo.Service;

import com.scotiatech.demo.dto.ResponseDto;
import com.scotiatech.demo.dto.interfazDto.IAgendaDto;
import com.scotiatech.demo.entity.AgendaEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DecanoService {

        ResponseDto<List<IAgendaDto>> getAgendasByFacultadForDecano(Long facultadId);
        ResponseDto<List<IAgendaDto>> getPendingAgendasForDecanoApproval(Long idUsuario);
        ResponseDto<AgendaEntity> updateDecanoAgendaStatus(MultipartFile file, boolean decision, Long id);
    }
