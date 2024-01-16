package uz.asadbek.debtors.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.asadbek.debtors.app.models.Debtor;
import uz.asadbek.debtors.app.models.Users;
import uz.asadbek.debtors.app.repositories.DebtorRepo;
import uz.asadbek.debtors.app.repositories.UsersRepo;
import uz.asadbek.debtors.app.util.exceptions.user.UserNotFoundExeption;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UsersService {
	private final UsersRepo usersRepo;
	private final DebtorRepo debtorRepo;
	@Autowired
	public UsersService(UsersRepo usersRepo, DebtorRepo debtorRepo) {
		this.usersRepo = usersRepo;
		this.debtorRepo = debtorRepo;
	}

	public List<Users> getAllUsers(){
		return usersRepo.findAll();
	}

	public Users getUsersById(int id){
		return usersRepo.findById(id).orElseThrow(UserNotFoundExeption::new);
	}
	@Transactional
	public void save(Users users) {
		 userFullSave(users);
		 usersRepo.save(users);
	}

	private void userFullSave(Users users){
		users.setCreatedAt(LocalDateTime.now());
		users.setUpdatedAt(LocalDateTime.now());
	}
	@Transactional
	public void edit(int id, Users users) {
		users.setUpdatedAt(LocalDateTime.now());
		users.setId(id);
		usersRepo.save(users);
	}

	@Transactional
	public void delete(int id){
		usersRepo.deleteById(id);
	}
}
