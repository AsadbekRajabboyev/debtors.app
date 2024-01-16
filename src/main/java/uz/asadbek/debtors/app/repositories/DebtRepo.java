package uz.asadbek.debtors.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.asadbek.debtors.app.models.Debt;

@Repository
public interface DebtRepo extends JpaRepository<Debt,Integer> {
}
