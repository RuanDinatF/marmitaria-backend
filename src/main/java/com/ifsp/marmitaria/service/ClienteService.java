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

	public ClienteDTO create(ClienteCreateDTO dto) {
		validateCreditLimit(dto);
		Cliente cliente = clienteMapper.toEntity(dto);
		cliente.setAtivo(true); // Garante que novos clientes são criados como ativos
		Cliente clienteSalvo = clienteRepository.save(cliente);
		return clienteMapper.toDTO(clienteSalvo);
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

		// Soft delete: marca como inativo ao invés de deletar
		clienteExistente.setAtivo(false);
		clienteRepository.save(clienteExistente);
		logger.info("Cliente com id {} foi marcado como inativo (soft delete)", id);
	}

	private void validateCreditLimit(ClienteCreateDTO dto) {
	    if (dto.getSaldo() == null) {
	        logger.warn("O saldo não foi informado.");
	        return; 
	    }

	    // Apenas loga avisos, mas não força a mudança do limite de crédito
	    // O usuário pode decidir liberar crédito mesmo com saldo zero (ex: cliente confiável)
	    if (dto.getLimiteCredito() != null && dto.getLimiteCredito()) {
	        if (dto.getSaldo() > 0) {
	            logger.info("Cliente com saldo positivo (saldo: {}) e crédito liberado.", String.format("%.2f", dto.getSaldo()));
	        } else {
	            logger.warn("ATENÇÃO: Cliente com saldo zero ou negativo (saldo: {}) mas crédito foi LIBERADO manualmente.", String.format("%.2f", dto.getSaldo()));
	        }
	    } else {
	        logger.info("Cliente com crédito bloqueado (saldo: {}).", String.format("%.2f", dto.getSaldo()));
	    }
	}


}
