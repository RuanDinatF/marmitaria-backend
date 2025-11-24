package com.ifsp.marmitaria.service;

import com.ifsp.marmitaria.dto.insumo.TipoInsumoDTO;
import com.ifsp.marmitaria.entity.TipoInsumo;
import com.ifsp.marmitaria.mapper.TipoInsumoMapper;
import com.ifsp.marmitaria.repository.TipoInsumoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoInsumoService {

    private static final Logger logger = LoggerFactory.getLogger(TipoInsumoService.class);

    private final TipoInsumoRepository tipoInsumoRepository;
    private final TipoInsumoMapper tipoInsumoMapper;

    public List<TipoInsumoDTO> findAll() {
        logger.info("Buscando todos os tipos de insumo");
        List<TipoInsumo> tipos = tipoInsumoRepository.findAll();
        return tipoInsumoMapper.toDTOs(tipos);
    }
}
