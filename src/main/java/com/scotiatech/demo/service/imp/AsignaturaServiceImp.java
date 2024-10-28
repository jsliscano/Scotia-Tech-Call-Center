package com.scotiatech.demo.service.imp;

import com.scotiatech.demo.entity.AsignaturaEntity;
import com.scotiatech.demo.exeption.AsignaturaNotFoundException;
import com.scotiatech.demo.repository.AsignaturaRepository;
import com.scotiatech.demo.service.AsignaturaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class AsignaturaServiceImp implements AsignaturaService {
    final private AsignaturaRepository asignaturaRepository;

    @Override
    public AsignaturaEntity saveAsignatura(AsignaturaEntity asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    @Override
    public AsignaturaEntity update(Long id, AsignaturaEntity asignaturaDetails) throws AsignaturaNotFoundException {
        AsignaturaEntity existingAsignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new AsignaturaNotFoundException("Asignatura not found with id " + id));

        existingAsignatura.setNombre(asignaturaDetails.getNombre());
        existingAsignatura.setPrograma(asignaturaDetails.getPrograma());
        existingAsignatura.setGrupo(asignaturaDetails.getGrupo());
        existingAsignatura.setSede(asignaturaDetails.getSede());
        existingAsignatura.setHorasSemanales(asignaturaDetails.getHorasSemanales());
        existingAsignatura.setHorasSemestres(asignaturaDetails.getHorasSemestres());

        return asignaturaRepository.save(existingAsignatura);
    }
}

