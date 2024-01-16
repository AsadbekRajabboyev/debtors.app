package uz.asadbek.debtors.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.asadbek.debtors.app.models.Debtor;

import java.util.Optional;

@Repository
public interface DebtorRepo extends JpaRepository<Debtor,Integer> {
	Optional<Debtor> findByUsersId(int userId);
}
