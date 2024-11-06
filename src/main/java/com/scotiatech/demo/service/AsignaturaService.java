package com.scotiatech.demo.service;

import com.scotiatech.demo.entity.AsignaturaEntity;

public interface AsignaturaService {
    AsignaturaEntity saveAsignatura (AsignaturaEntity asignatura);
    AsignaturaEntity update(Long id, AsignaturaEntity clienteEntity);
}
