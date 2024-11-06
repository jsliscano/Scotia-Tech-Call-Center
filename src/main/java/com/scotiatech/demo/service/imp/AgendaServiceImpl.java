package com.scotiatech.demo.service.imp; // Asegúrate de que este paquete sea correcto

import com.scotiatech.demo.entity.AgendaEntity;
import com.scotiatech.demo.entity.FacultadEntity;
import com.scotiatech.demo.repository.AgendaRepository;
import com.scotiatech.demo.service.AgendaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service; // Importa la anotación

import java.util.List;

@AllArgsConstructor
@Service

public class AgendaServiceImpl implements AgendaService {

    final private AgendaRepository agendaRepository;

    @Override
    public List<AgendaEntity> obtenerAgendasPorFacultad(FacultadEntity facultad) {
        return agendaRepository.findByFacultad(facultad);
    }
}
