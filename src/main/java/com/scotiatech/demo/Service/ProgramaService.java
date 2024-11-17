package com.scotiatech.demo.Service;

import com.scotiatech.demo.dto.ResponseDto;
import com.scotiatech.demo.dto.interfazDto.IFacultadDto;
import com.scotiatech.demo.dto.interfazDto.IProgramaDto;
import com.scotiatech.demo.entity.AgendaEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProgramaService {

    ResponseDto<List<IFacultadDto>> getAllFaculties();

    ResponseDto<List<IProgramaDto>> getProgramsByFacultyId(Long idFacultad);

    ResponseDto<AgendaEntity> updateAgendaStatus(MultipartFile file, boolean decision, Long id);
}

