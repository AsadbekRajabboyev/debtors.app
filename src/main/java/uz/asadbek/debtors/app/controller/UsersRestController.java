/******************************************************************************
 * Copyright (c) 2024.  Asadbek Rajabboyev         created 1/10/24, 4:50 PM   *
 ******************************************************************************/

package uz.asadbek.debtors.app.controller;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import uz.asadbek.debtors.app.DTO.debt.DebtDTO;
import uz.asadbek.debtors.app.DTO.debtor.DebtorAddDTO;
import uz.asadbek.debtors.app.DTO.user.UsersDTO;
import uz.asadbek.debtors.app.models.Debt;
import uz.asadbek.debtors.app.models.Debtor;
import uz.asadbek.debtors.app.models.Users;
import uz.asadbek.debtors.app.services.DebtService;
import uz.asadbek.debtors.app.services.DebtorService;
import uz.asadbek.debtors.app.services.UsersService;
import uz.asadbek.debtors.app.util.errorModels.DebtorErrorResponse;
import uz.asadbek.debtors.app.util.errorModels.UserResponseError;
import uz.asadbek.debtors.app.util.exceptions.debtor.DebtorNotAddExeption;
import uz.asadbek.debtors.app.util.exceptions.user.UserNotCreatedExeption;
import uz.asadbek.debtors.app.util.exceptions.user.UserNotFoundExeption;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UsersRestController {
private final UsersService usersService;
private final ModelMapper modelMapper;
private final DebtorService debtorService;
private final DebtService debtService;

	@Autowired
	public UsersRestController(UsersService usersService, ModelMapper modelMapper, DebtorService debtorService, DebtService debtService) {
		this.usersService = usersService;
		this.modelMapper = modelMapper;
		this.debtorService = debtorService;
		this.debtService = debtService;
	}

	@GetMapping
	public List<UsersDTO>getAllUsers(){
		return usersService.getAllUsers().stream().map(this::convertToUsersDTO).collect(Collectors.toList());
	}
	@GetMapping("/{id}")
	public UsersDTO getUserById(@PathVariable("id") int id){
		return convertToUsersDTO(usersService.getUsersById(id));
	}

//////////////////////////////user save////////////////////////////////////////////////////////////////////////////////
	@PostMapping()
	public ResponseEntity<String> createUser(@RequestBody @Valid UsersDTO usersDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()){
			StringBuilder stringBuilder= new StringBuilder();
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			for (FieldError fieldError: fieldErrors) {
				stringBuilder.append(fieldError.getField())
						.append("-")
						.append(fieldError.getDefaultMessage())
						.append(";");
			}
			throw new UserNotCreatedExeption(stringBuilder.toString());
		}
		usersService.save(convertToUsers(usersDTO));
		return new ResponseEntity<>("Muaffaqiyatli qo'shildi", HttpStatus.CREATED);
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	@PatchMapping("/{id}")
	public String updatePerson(UsersDTO usersDTO, @PathVariable("id") int id) {
		usersService.edit(id,convertToUsers(usersDTO));
		return "redirect:/people";
	}


	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable("id") int id){
		usersService.delete(id);
		return "redirect:/people";
	}
/////////////////////////////////save debtor////////////////////////////////////////////////////////////////////////////
	@PostMapping("/addDebtor")
	public ResponseEntity<String> addDebtor(@RequestBody @Valid DebtorAddDTO debtorAddDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			StringBuilder stringBuilder = new StringBuilder();
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				stringBuilder.append(fieldError.getField())
						.append("-")
						.append(fieldError.getDefaultMessage())
						.append(";");
			}
			throw new DebtorNotAddExeption(stringBuilder.toString());
		}

		Users user = usersService.getUsersById(debtorAddDTO.getUserId());

		if (user == null) {
			throw new UserNotFoundExeption(); // 404 Status Code
		}
		Debtor debtor = new Debtor();
		debtor.setUsers(user);
		debtor.setAddress(debtorAddDTO.getAddress());
		debtor.setFirstName(debtorAddDTO.getFirstName());
		debtor.setLastName(debtorAddDTO.getLastName());
		debtor.setPhone(debtorAddDTO.getPhone());

		debtorService.save(debtor);

		Debt debt = new Debt();
		debt.setDebtor(debtor);
		debt.setSumma(debtorAddDTO.getDebts().getSumma());
		debt.setReturnDate(debtorAddDTO.getDebts().getReturnDate());
		debtService.saveDebt(debt);
		return new ResponseEntity<>("Debtor added successfully", HttpStatus.OK); // 201 status code
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private Users convertToUsers(UsersDTO usersDTO) {
		return modelMapper.map(usersDTO, Users.class);
	}

	private UsersDTO convertToUsersDTO(Users users) {
		return modelMapper.map(users, UsersDTO.class);
	}
	@ExceptionHandler
	private ResponseEntity<UserResponseError> handlerExeption(UserNotFoundExeption e) {
		UserResponseError userResponseError = new UserResponseError("Bunday IDga ega user yo'q",System.currentTimeMillis());
		return new ResponseEntity<>(userResponseError, HttpStatus.NOT_FOUND); // 404 status code
	}
	@ExceptionHandler
	private ResponseEntity<UserResponseError> handlerExeptionCreated(UserNotCreatedExeption e) {
		UserResponseError userResponseError = new UserResponseError();
		userResponseError.setMessage(e.getMessage());
		userResponseError.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(userResponseError, HttpStatus.BAD_REQUEST);//400 status code
	}

	@ExceptionHandler
	private ResponseEntity<DebtorErrorResponse> handlerExeptionCreated(DebtorNotAddExeption e) {
		DebtorErrorResponse debtorErrorResponse = new DebtorErrorResponse();
		debtorErrorResponse.setMessage(e.getMessage());
		debtorErrorResponse.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(debtorErrorResponse, HttpStatus.BAD_REQUEST);// 400 Status
	}

}
