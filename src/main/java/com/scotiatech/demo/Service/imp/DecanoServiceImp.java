package com.scotiatech.demo.Service.imp;

import com.scotiatech.demo.Contantes.ConstanResponses;
import com.scotiatech.demo.Service.DecanoService;
import com.scotiatech.demo.dto.ResponseDto;
import com.scotiatech.demo.dto.interfazDto.IAgendaDto;
import com.scotiatech.demo.entity.AgendaEntity;
import com.scotiatech.demo.entity.UsuarioEntity;
import com.scotiatech.demo.repository.AgendaValidaRepository;
import com.scotiatech.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor

public class DecanoServiceImp implements DecanoService {

    private final AgendaValidaRepository agendasValidaRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public ResponseDto<List<IAgendaDto>> getAgendasByFacultadForDecano(Long facultadId) {
        try {
            UsuarioEntity user = usuarioRepository.findById(facultadId)
                    .orElseThrow(() -> new RuntimeException(ConstanResponses.USER_NOT_FOUND));
            String role = usuarioRepository.getRole(facultadId);
            if (role.equals(ConstanResponses.Dean)){
                List<IAgendaDto> agendas = agendasValidaRepository.findAllForApproveDecano(user.getFaculty().getId());
                return new ResponseDto<>(agendas, HttpStatus.OK.value(), ConstanResponses.OK);
            }else {
                return new ResponseDto<>(null,HttpStatus.BAD_REQUEST.value(), ConstanResponses.VALIDATE_ROL);
            }
        } catch (Exception e) {
            return new ResponseDto<>(null, HttpStatus.BAD_REQUEST.value(), ConstanResponses.SERVICE_ERROR);
        }
    }

    @Override
    public ResponseDto<List<IAgendaDto>> getPendingAgendasForDecanoApproval(Long idUsuario) {
        try {
            UsuarioEntity user = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException(ConstanResponses.USER_NOT_FOUND));
            String role = usuarioRepository.getRole(idUsuario);
            if (role.equals(ConstanResponses.Dean)){
                List<IAgendaDto> agendas = agendasValidaRepository.findAllForHistoricoDecano(user.getFaculty().getId());
                return new ResponseDto<>(agendas, HttpStatus.OK.value(), ConstanResponses.Dean);
            }else {
                return new ResponseDto<>(null,HttpStatus.BAD_REQUEST.value(), ConstanResponses.VALIDATE_ROL);
            }
        } catch (Exception e) {
            return new ResponseDto<>(null,HttpStatus.BAD_REQUEST.value(), ConstanResponses.SERVICE_ERROR);
        }
    }

    @Override
    public ResponseDto<AgendaEntity> updateDecanoAgendaStatus(MultipartFile file, boolean decision, Long id) {
        try {
            AgendaEntity agenda = agendasValidaRepository.findById(id).orElseThrow(() -> new RuntimeException(ConstanResponses.AGENDA_NOT_FOUND));
            agenda.setFile(file.getBytes());
            agenda.setDeanApproval(decision);
            agendasValidaRepository.save(agenda);
            return new ResponseDto<>(null, HttpStatus.CREATED.value(), ConstanResponses.APROVE_DEAN);
        } catch (Exception e){
            return new ResponseDto<>(null,HttpStatus.BAD_REQUEST.value(), ConstanResponses.SERVICE_ERROR+e.getMessage());
        }
    }
}

