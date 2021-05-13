package com.leiton.ABMExample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.leiton.ABMExample.business.BusinessException;
import com.leiton.ABMExample.business.IClienteBusiness;
import com.leiton.ABMExample.model.Cliente;
import com.leiton.ABMExample.model.exception.NotFoundException;

@RestController
@RequestMapping("/clientes")
public class ClienteRESTController {
	@Autowired
	private IClienteBusiness clienteBusiness;
	
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Cliente>> lista() {

		try {
			return new ResponseEntity<List<Cliente>>(clienteBusiness.getAll(),HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Cliente>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = {  "/{id}" }, method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Cliente> uno(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<Cliente>(clienteBusiness.getOne(id),HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}
		
	}

	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> add(@RequestBody Cliente cliente) {
		
		try {
			clienteBusiness.add(cliente);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", "/clientes/" + cliente.getDni());
			return new ResponseEntity<Object>(responseHeaders,HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
