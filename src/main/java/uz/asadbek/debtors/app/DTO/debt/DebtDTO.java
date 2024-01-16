package uz.asadbek.debtors.app.DTO.debt;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;

public class DebtDTO {

	@NotNull(message = "summa kiriting")
	@Min(value = 1000, message = "1000 so'mdan kam kiritib bo'lmaydi")
	@Max(value = 1000000, message = "1mln dan ko'p kiritib bo'lmaydi")
	private int summa;


	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXX")
	private Date returnDate;

	@NotNull(message = "debtorId kiriting")
	private int debtorId;

	public DebtDTO() {
	}

	public DebtDTO(int summa, Date returnDate, int debtorId) {
		this.summa = summa;
		this.returnDate = returnDate;
		this.debtorId = debtorId;
	}

	public int getDebtorId() {
		return debtorId;
	}

	public void setDebtorId(int debtorId) {
		this.debtorId = debtorId;
	}

	public int getSumma() {
		return summa;
	}

	public void setSumma(int summa) {
		this.summa = summa;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
}
