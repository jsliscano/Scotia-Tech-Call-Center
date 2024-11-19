package com.scotiatech.demo.controller;

import com.scotiatech.demo.Service.ProgramaService;
import com.scotiatech.demo.dto.ResponseDto;
import com.scotiatech.demo.dto.interfazDto.IFacultadDto;
import com.scotiatech.demo.dto.interfazDto.IProgramaDto;
import com.scotiatech.demo.entity.AgendaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/api/programs")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProgramaController {

    private final ProgramaService programaService;

    @GetMapping(path = "/faculties")
    public ResponseDto<List<IFacultadDto>> getAllFaculties() {
        return programaService.getAllFaculties();
    }

    @GetMapping(path = "/by-faculty")
    public ResponseDto<List<IProgramaDto>> getProgramsByFacultyId(@RequestParam("facultadId") Long facultadId) {
        return programaService.getProgramsByFacultyId(facultadId);
    }

    @PostMapping(path = "/update-agenda")
    public ResponseDto<AgendaEntity> updateAgendaStatus(@RequestParam("file") MultipartFile file,
                                                        @RequestParam("decision") boolean decision,
                                                        @RequestParam("idAgenda") Long idAgenda) {
        return programaService.updateAgendaStatus(file, decision, idAgenda);
    }
}
