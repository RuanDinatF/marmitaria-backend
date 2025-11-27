package com.ifsp.marmitaria.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ifsp.marmitaria.dto.cliente.ClienteCreateDTO;
import com.ifsp.marmitaria.dto.cliente.ClienteDTO;
import com.ifsp.marmitaria.entity.Cliente;
import com.ifsp.marmitaria.mapper.ClienteMapper;
import com.ifsp.marmitaria.repository.ClienteRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

	private final ClienteRepository clienteRepository;
	private final ClienteMapper clienteMapper;

	public ClienteDTO getById(Long id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> {
			logger.warn("Cliente não encontrado com id: {}", id);
			return new EntityNotFoundException("Cliente não encontrado com id: " + id);
		});
		return clienteMapper.toDTO(cliente);
	}

	public List<ClienteDTO> findAll() {
		List<Cliente> clienteList = clienteRepository.findAll();
		return clienteMapper.toDTOs(clienteList);
	}

	public ClienteCreateDTO create(ClienteCreateDTO dto) {
		validateCreditLimit(dto);
		Cliente cliente = clienteMapper.toEntity(dto);
		clienteRepository.save(cliente);
		return dto;
	}

	public ClienteDTO update(Long id, ClienteCreateDTO dto) {
		Cliente clienteExistente = clienteRepository.findById(id).orElseThrow(() -> {
			logger.warn("Cliente não encontrado para update com id: {}", id);
			return new EntityNotFoundException("Cliente não encontrado com id: " + id);
		});

		validateCreditLimit(dto);

		clienteExistente.setNome(dto.getNome());
		clienteExistente.setEndereco(dto.getEndereco());
		clienteExistente.setTelefone(dto.getTelefone());
		clienteExistente.setSaldo(dto.getSaldo());
		clienteExistente.setLimiteCredito(dto.getLimiteCredito());

		Cliente clienteAtualizado = clienteRepository.save(clienteExistente);
		return clienteMapper.toDTO(clienteAtualizado);
	}

	public void delete(Long id) {
		Cliente clienteExistente = clienteRepository.findById(id).orElseThrow(() -> {
			logger.warn("Tentativa de deletar Cliente não existente, id: {}", id);
			return new EntityNotFoundException("Cliente não encontrado para exclusão com id: " + id);
		});

		clienteRepository.delete(clienteExistente);
	}

	private void validateCreditLimit(ClienteCreateDTO dto) {
	    if (dto.getSaldo() == null) {
	        logger.warn("O saldo não foi informado.");
	        return; 
	    }

	    boolean saldoNegativo = dto.getSaldo() < 0; 

	    dto.setLimiteCredito(!saldoNegativo); 

	    if (saldoNegativo) {
	        logger.warn("O saldo é negativo (saldo: %.2f). O cliente não possui Crédito.", dto.getSaldo());
	    } else {
	        logger.info("O saldo é positivo (saldo: %.2f). O cliente possui Crédito.", dto.getSaldo());
	    }
	}


}
