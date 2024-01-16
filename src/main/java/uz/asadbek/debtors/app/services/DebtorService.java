package uz.asadbek.debtors.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.asadbek.debtors.app.models.Debtor;
import uz.asadbek.debtors.app.repositories.DebtorRepo;
import uz.asadbek.debtors.app.util.exceptions.debtor.DebtorNotFoundExeption;
import uz.asadbek.debtors.app.util.exceptions.debtor.DebtorNotFoundUserIdExeption;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DebtorService {
	private final DebtorRepo debtorRepo;
	@Autowired
	public DebtorService(DebtorRepo debtorRepo) {
		this.debtorRepo = debtorRepo;
	}


	public List<Debtor> getAllUsers() {
		return debtorRepo.findAll();
	}

	@Transactional
	public void save(Debtor newDebtor) {
		debtorRepo.save(newDebtor);
	}

	public Debtor getDebtorByUserId(int userId) {
		return debtorRepo.findByUsersId(userId).orElseThrow(DebtorNotFoundUserIdExeption::new);
	}

	public Debtor getDebtorById(int debtorId) {
		return debtorRepo.findById(debtorId).orElseThrow(DebtorNotFoundExeption::new);
	}

}
