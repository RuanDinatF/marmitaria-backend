package com.ifsp.marmitaria.service;

import com.ifsp.marmitaria.dto.unidademedida.UnidadeMedidaDTO;
import com.ifsp.marmitaria.entity.UnidadeMedida;
import com.ifsp.marmitaria.mapper.UnidadeMedidaMapper;
import com.ifsp.marmitaria.repository.UnidadeMedidaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnidadeMedidaService {

    private static final Logger logger = LoggerFactory.getLogger(UnidadeMedidaService.class);

    private final UnidadeMedidaRepository unidadeMedidaRepository;
    private final UnidadeMedidaMapper unidadeMedidaMapper;

    public List<UnidadeMedidaDTO> findAll() {
        logger.info("Buscando todas as unidades de medida");
        List<UnidadeMedida> unidades = unidadeMedidaRepository.findAll();
        return unidadeMedidaMapper.toDTOs(unidades);
    }
}
