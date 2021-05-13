package com.leiton.ABMExample.business.impl;

import com.leiton.ABMExample.model.Cliente;
import com.leiton.ABMExample.model.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.leiton.ABMExample.business.BusinessException;
import com.leiton.ABMExample.business.IClienteBusiness;

@Service
public class ClienteBusiness implements IClienteBusiness {
	
	private List<Cliente> r = new ArrayList<Cliente>();

	public ClienteBusiness() {
		r.add(new Cliente(30123123, "Juan", "Lopez", new Date(), "Calle 123"));
		r.add(new Cliente(32456456, "Jose", "Rodriguez", new Date(), "Avenida 456"));
		r.add(new Cliente(35789789, "Maria", "Lopez", new Date(), "Calle 789"));
	}

	@Override
	public List<Cliente> getAll() throws BusinessException {
		return r;
	}

	@Override
	public List<Cliente> searchByLastName(String part) throws BusinessException {
		List<Cliente> resultado = new ArrayList<Cliente>();
		for (Cliente c : r) {
			if (c.getApellido().toLowerCase().indexOf(part.toLowerCase()) != -1) {
				resultado.add(c);
			}
		}
		return resultado;
	}

	@Override
	public Cliente getOne(int dni) throws BusinessException, NotFoundException {
		Cliente cl = null;
		for (Cliente c : r) {
			if (c.getDni() == dni) {
				cl = c;
				break;
			}
		}
		if (cl == null)
			throw new NotFoundException();
		return cl;
	}

	@Override
	public Cliente add(Cliente cliente) throws BusinessException {
		r.add(cliente);
		return cliente;
	}

	@Override
	public Cliente update(Cliente cliente) throws BusinessException, NotFoundException {
		boolean found = false;
		for (int t = 0; t < r.size(); t++) {
			Cliente c = r.get(t);
			if (c.getDni() == cliente.getDni()) {
				r.set(t, cliente);
				found = true;
				break;
			}
		}
		if (!found)
			throw new NotFoundException();
		return cliente;
	}

	@Override
	public void delete(Cliente cliente) throws BusinessException, NotFoundException {
		boolean found = false;
		for (int t = 0; t < r.size(); t++) {
			Cliente c = r.get(t);
			if (c.getDni() == cliente.getDni()) {
				r.remove(t);
				found = true;
				break;
			}
		}
		if (!found)
			throw new NotFoundException();
	}

}
