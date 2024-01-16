/******************************************************************************
 * Copyright (c) 2024.  Asadbek Rajabboyev         created 1/10/24, 4:52 PM   *
 ******************************************************************************/

package uz.asadbek.debtors.app.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.asadbek.debtors.app.DTO.debtor.DebtorDTO;
import uz.asadbek.debtors.app.models.Debtor;
import uz.asadbek.debtors.app.services.DebtorService;
import uz.asadbek.debtors.app.util.errorModels.DebtorErrorResponse;
import uz.asadbek.debtors.app.util.errorModels.UserResponseError;
import uz.asadbek.debtors.app.util.exceptions.debtor.DebtorNotAddExeption;
import uz.asadbek.debtors.app.util.exceptions.debtor.DebtorNotFoundUserIdExeption;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/debtor")
public class DebtorRestController {
	private final DebtorService debtorService;
	private final ModelMapper modelMapper;
	@Autowired
	public DebtorRestController(DebtorService debtorService, ModelMapper modelMapper) {
		this.debtorService = debtorService;
		this.modelMapper = modelMapper;
	}

	@GetMapping
	public List<DebtorDTO> getAllDebtors() {
		List<Debtor> debtors = debtorService.getAllUsers();
		return debtors.stream().map(this::convertToDebtorDTO).collect(Collectors.toList());
	}
	@GetMapping("/{id}")
	public ResponseEntity<DebtorDTO> getDebtorByUserId(@PathVariable("id") int userId) {
		Debtor debtor = debtorService.getDebtorByUserId(userId);
		if (debtor == null) {
			throw new DebtorNotFoundUserIdExeption();
		}
		DebtorDTO debtorDTO = convertToDebtorDTO(debtor);
		return new ResponseEntity<>(debtorDTO, HttpStatus.OK);
	}






	private DebtorDTO convertToDebtorDTO(Debtor debtor) {
		return modelMapper.map(debtor, DebtorDTO.class);
	}

	@ExceptionHandler
	private ResponseEntity<DebtorErrorResponse> handlerExeptionCreated(DebtorNotFoundUserIdExeption e) {
		DebtorErrorResponse debtorErrorResponse = new DebtorErrorResponse("Bunday IDga ega user yo'q",System.currentTimeMillis());
		return new ResponseEntity<>(debtorErrorResponse, HttpStatus.BAD_REQUEST);// 400 Status
	}



}
