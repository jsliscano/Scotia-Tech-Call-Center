package com.scotiatech.demo.Service.imp;

import com.scotiatech.demo.Contantes.ConstanResponses;
import com.scotiatech.demo.Service.ProgramaService;
import com.scotiatech.demo.dto.ResponseDto;
import com.scotiatech.demo.dto.interfazDto.IFacultadDto;
import com.scotiatech.demo.dto.interfazDto.IProgramaDto;
import com.scotiatech.demo.entity.AgendaEntity;
import com.scotiatech.demo.repository.AgendaValidaRepository;
import com.scotiatech.demo.repository.ProgramaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ProgramaServiceImp implements ProgramaService {

    private final AgendaValidaRepository agendasValidRepository;
    private final ProgramaRepository programaRepository;


    @Override
    public ResponseDto<List<IFacultadDto>> getAllFaculties() {
        try{
            List<IFacultadDto> facultadEntities = programaRepository.finAllFacultad();
            return new ResponseDto<>(facultadEntities, HttpStatus.OK.value(), ConstanResponses.OK);
        }catch (Exception e){
            return new ResponseDto<>(null, HttpStatus.BAD_REQUEST.value(), ConstanResponses.SERVICE_ERROR+e.getMessage());
        }
    }

    @Override
    public ResponseDto<List<IProgramaDto>> getProgramsByFacultyId(Long idFacultad) {
        try{
            List<IProgramaDto> programas = programaRepository.findByFacultadId(idFacultad);
            return new ResponseDto<>(programas,HttpStatus.OK.value(),ConstanResponses.OK);
        }catch (Exception e){
            return new ResponseDto<>(null, HttpStatus.BAD_REQUEST.value(), ConstanResponses.SERVICE_ERROR+e.getMessage());
        }
    }

    @Override
    public ResponseDto<AgendaEntity> updateAgendaStatus(MultipartFile file, boolean decision, Long id) {
        try {
            AgendaEntity agenda = agendasValidRepository.findById(id).orElseThrow(() -> new RuntimeException(ConstanResponses.AGENDA_NOT_FOUND));
            agenda.setFile(file.getBytes());
            agenda.setProgramDirectorApproval(decision);
            agendasValidRepository.save(agenda);
            return new ResponseDto<>(null, HttpStatus.CREATED.value(), ConstanResponses.OK);
        } catch (Exception e){
            return new ResponseDto<>(null,HttpStatus.BAD_REQUEST.value(), ConstanResponses.SERVICE_ERROR+e.getMessage());
        }
    }
}





