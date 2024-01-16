package uz.asadbek.debtors.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.asadbek.debtors.app.models.Debt;
import uz.asadbek.debtors.app.repositories.DebtRepo;
import uz.asadbek.debtors.app.repositories.DebtorRepo;
import uz.asadbek.debtors.app.util.exceptions.debtor.DebtorNotFoundExeption;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DebtService {

	private final DebtRepo debtRepo;

	@Autowired
	public DebtService(DebtRepo debtRepo) {
		this.debtRepo = debtRepo;
	}

	public List<Debt> getAllDebts(){
	 return	debtRepo.findAll();
	}

	public Debt getDebt(int debtorId){
		return debtRepo.findById(debtorId).orElseThrow(DebtorNotFoundExeption::new);
	}

	@Transactional
	public void saveDebt(Debt debt){
		debt.setCreatedAt(LocalDateTime.now());
		debtRepo.save(debt);
	}

	@Transactional
	public void deleteDebt(int id){
		debtRepo.deleteById(id);
	}
	@Transactional
	public void editDebt(int id, Debt debt){
		debt.setId(id);
		debtRepo.save(debt);
	}
}
