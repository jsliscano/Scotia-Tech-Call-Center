package com.scotiatech.demo.Service.imp;

import com.scotiatech.demo.Contantes.ConstanResponses;
import com.scotiatech.demo.Service.AgendaValidaService;
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

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendaValidaServiceImp implements AgendaValidaService {

    private final AgendaValidaRepository agendaValidaRepository;
    private final UsuarioRepository usuarioRepository;


    @Override
    public List<IAgendaDto> findUserAgenda(Long id) {
        return agendaValidaRepository.findAllByUser(id);
    }

    @Override
    public AgendaEntity findAllByUser(Long id) {
        return agendaValidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(ConstanResponses.AGENDA_NOT_FOUND));
    }

    @Override
    public ResponseDto<AgendaEntity> saveAgenda(MultipartFile file, Long userId, String facultad, String programa) {
        try {
            UsuarioEntity user = usuarioRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException(ConstanResponses.USER_NOT_FOUND));

            AgendaEntity agenda = new AgendaEntity();
            agenda.setFileName(file.getOriginalFilename());
            agenda.setFaculty(user.getFaculty());
            agenda.setProgram(user.getPrograma());
            agenda.setCreationDate(LocalDateTime.now());
            agenda.setFile(file.getBytes());
            agenda.setTeacher(user);
            agendaValidaRepository.save(agenda);
            return new ResponseDto<>(null, HttpStatus.CREATED.value(), ConstanResponses.AGENDA_NOT_FOUND);
        } catch (Exception e){
            return new ResponseDto<>(null, HttpStatus.BAD_REQUEST.value(), ConstanResponses.SERVICE_ERROR +e.getMessage());
        }
    }

    @Override
    public ResponseDto<List<IAgendaDto>> findProgramAgendasForDirector(Long programaId) {
        try {UsuarioEntity user = usuarioRepository.findById(programaId)
                .orElseThrow(() -> new RuntimeException(ConstanResponses.USER_NOT_FOUND));
            String role = usuarioRepository.getRole(programaId);
            if (role.equals(ConstanResponses.DIRECTOR)){
                List<IAgendaDto> agendas = agendaValidaRepository.findAllToDirector(user.getPrograma().getId());
                return new ResponseDto<>(agendas,HttpStatus.OK.value(), ConstanResponses.OK);
            }else {
                return new ResponseDto<>(null,HttpStatus.BAD_REQUEST.value(), ConstanResponses.VALIDATE_ROL);
            }
        } catch (Exception e) {
            return new ResponseDto<>(null,HttpStatus.BAD_REQUEST.value(), ConstanResponses.SERVICE_ERROR);
        }
    }

    @Override
    public ResponseDto<List<IAgendaDto>> findHistoricalProgramAgendasForDirector(Long programaId) {
        try {UsuarioEntity user = usuarioRepository.findById(programaId)
                .orElseThrow(() -> new RuntimeException(ConstanResponses.USER_NOT_FOUND));
            String role = usuarioRepository.getRole(programaId);
            if (role.equals(ConstanResponses.DIRECTOR)){
                List<IAgendaDto> agendas = agendaValidaRepository.findAllForHistoricDirector(user.getPrograma().getId());
                return new ResponseDto<>(agendas,HttpStatus.OK.value(), ConstanResponses.OK);
            }else {
                return new ResponseDto<>(null,HttpStatus.BAD_REQUEST.value(), ConstanResponses.VALIDATE_ROL);
            }
        } catch (Exception e) {
            return new ResponseDto<>(null,HttpStatus.BAD_REQUEST.value(), ConstanResponses.SERVICE_ERROR);
        }
    }
}
