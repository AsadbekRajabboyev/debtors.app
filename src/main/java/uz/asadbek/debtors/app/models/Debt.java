/******************************************************************************
 * Copyright (c) 2024.  Asadbek Rajabboyev         created 1/10/24, 4:51 PM   *
 ******************************************************************************/

package uz.asadbek.debtors.app.models;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "debt")
public class Debt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;


	@Column(name = "summa")
	private int summa;
	@Column(name = "return_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXX")
	private Date returnDate;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "debtor_id", referencedColumnName = "id")
	private Debtor debtor;


	public Debt() {
	}

	public Debt(int id, int summa, Date returnDate, LocalDateTime createdAt, Debtor debtor) {
		this.id = id;
		this.summa = summa;
		this.returnDate = returnDate;
		this.createdAt = createdAt;
		this.debtor = debtor;
	}

	public Debt(int summa, Date returnDate, LocalDateTime createdAt, Debtor debtor) {
		this.summa = summa;
		this.returnDate = returnDate;
		this.createdAt = createdAt;
		this.debtor = debtor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Debtor getDebtor() {
		return debtor;
	}

	public void setDebtor(Debtor debtor) {
		this.debtor = debtor;
	}
}

