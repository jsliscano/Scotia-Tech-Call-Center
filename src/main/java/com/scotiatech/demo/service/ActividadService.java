package com.scotiatech.demo.service;

import com.scotiatech.demo.entity.ActividadEntity;

import java.util.List;
import java.util.Optional;

public interface ActividadService {
    List<ActividadEntity> findAll();
    Optional<ActividadEntity> findById(Long id);
    String[] getProductos(Long id);
}
