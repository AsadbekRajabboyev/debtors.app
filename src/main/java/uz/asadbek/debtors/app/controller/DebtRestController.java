/******************************************************************************
 * Copyright (c) 2024.  Asadbek Rajabboyev         created 1/10/24, 4:53 PM   *
 ******************************************************************************/

package uz.asadbek.debtors.app.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.asadbek.debtors.app.DTO.debt.DebtDTO;
import uz.asadbek.debtors.app.models.Debt;
import uz.asadbek.debtors.app.models.Debtor;
import uz.asadbek.debtors.app.services.DebtService;
import uz.asadbek.debtors.app.services.DebtorService;
import uz.asadbek.debtors.app.util.errorModels.DebtorErrorResponse;
import uz.asadbek.debtors.app.util.errorModels.UserResponseError;
import uz.asadbek.debtors.app.util.exceptions.debtor.DebtorNotFoundExeption;
import uz.asadbek.debtors.app.util.exceptions.user.UserNotFoundExeption;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/debts")
public class DebtRestController {
	private final ModelMapper modelMapper;
	private final DebtService debtService;
	private final DebtorService debtorService;

	@Autowired
	public DebtRestController(ModelMapper modelMapper, DebtService debtService, DebtorService debtorService) {
		this.modelMapper = modelMapper;
		this.debtService = debtService;
		this.debtorService = debtorService;
	}




	@GetMapping
	public List<DebtDTO>getAllUsers(){
		return debtService.getAllDebts().stream().map(this::convertToDebtDTO).collect(Collectors.toList());
	}

	private DebtDTO convertToDebtDTO(Debt debt) {
		return modelMapper.map(debt, DebtDTO.class);
	}

	private Debt convertToDebt(DebtDTO debtDTO){
		return modelMapper.map(debtDTO,Debt.class);
	}
	@GetMapping("/{debtor_id}")
	public ResponseEntity<List<DebtDTO>> getDebtsByDebtorId(@PathVariable("debtor_id") int debtorId) {
		Debtor debtor = debtorService.getDebtorById(debtorId);
		if (debtor == null) {
			throw new DebtorNotFoundExeption();
		}
		List<Debt> debts = debtor.getDebts();
		List<DebtDTO> debtDTOList = debts.stream()
				.map(debt -> modelMapper.map(debt, DebtDTO.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(debtDTOList, HttpStatus.OK);
	}


	@ExceptionHandler
	private ResponseEntity<DebtorErrorResponse> handlerExeption(DebtorNotFoundExeption e) {
		DebtorErrorResponse debtorErrorResponse= new DebtorErrorResponse("Bunday IDga ega debtor yo'q",System.currentTimeMillis());
		return new ResponseEntity<>(debtorErrorResponse, HttpStatus.NOT_FOUND); // 404 status code
	}

}