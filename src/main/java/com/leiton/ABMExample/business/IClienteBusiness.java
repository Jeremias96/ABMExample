package com.leiton.ABMExample.business;

import java.util.List;

import com.leiton.ABMExample.model.Cliente;
import com.leiton.ABMExample.model.exception.NotFoundException;

public interface IClienteBusiness {
	public List<Cliente> getAll() throws BusinessException;
	public List<Cliente> searchByLastName(String part) throws BusinessException;
	public Cliente getOne(int dni) throws BusinessException, NotFoundException;
	public Cliente add(Cliente cliente) throws BusinessException;
	public Cliente update(Cliente cliente) throws BusinessException, NotFoundException;
	public void delete(Cliente cliente) throws BusinessException, NotFoundException;
}
