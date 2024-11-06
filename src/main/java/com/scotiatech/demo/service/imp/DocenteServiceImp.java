package com.scotiatech.demo.service.imp;

import com.scotiatech.demo.entity.DocenteEntity;
import com.scotiatech.demo.repository.DocenteRepository;
import com.scotiatech.demo.service.DocenteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class DocenteServiceImp implements DocenteService {


    final private DocenteRepository docenteRepository;

    @Override
    public DocenteEntity saveDocente(DocenteEntity docente) {
        return docenteRepository.save(docente);

    }

}

