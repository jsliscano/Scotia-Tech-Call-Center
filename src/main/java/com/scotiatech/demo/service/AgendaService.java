package com.scotiatech.demo.service;


import com.scotiatech.demo.entity.AgendaEntity;
import com.scotiatech.demo.entity.FacultadEntity;

import java.util.List;

public interface AgendaService {
    List<AgendaEntity> obtenerAgendasPorFacultad(FacultadEntity facultad);
}


