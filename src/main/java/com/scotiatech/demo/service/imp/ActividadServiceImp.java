package com.scotiatech.demo.service.imp;

import com.scotiatech.demo.entity.ActividadEntity;
import com.scotiatech.demo.repository.ActividadRepository;
import com.scotiatech.demo.service.ActividadService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ActividadServiceImp implements ActividadService {

    private final ActividadRepository actividadRepository;

    @Override
    public List<ActividadEntity> findAll() {
        return actividadRepository.findAll();
    }

    @Override
    public Optional<ActividadEntity> findById(Long id) {
        return actividadRepository.findById(id);
    }

    @Override
    public String[] getProductos(Long id) {
        return actividadRepository.getprdocutos(id);
    }
}
