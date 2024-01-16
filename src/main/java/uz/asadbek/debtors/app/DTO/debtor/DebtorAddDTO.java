package uz.asadbek.debtors.app.DTO.debtor;

import jakarta.validation.constraints.*;
import uz.asadbek.debtors.app.DTO.debt.DebtDTO;
import uz.asadbek.debtors.app.models.Debt;
import java.util.List;

/******************************************************************************
 * Copyright (c) 2024.  Asadbek Rajabboyev         created 1/10/24, 9:36 PM   *
 ******************************************************************************/

public class DebtorAddDTO {
	@NotEmpty(message = "Ismni to'liq kiriting")
	@Size(min = 2, max = 30, message = "Ism 2dan 30gacha bo'lgan harfdan iborat bo'lishi kerak")
	private String firstName;
	@NotEmpty(message = "Familyani to'liq kiriting")
	@Size(min = 2, max = 30, message = "Familya 2dan 30gacha bo'lgan harfdan iborat bo'lishi kerak")
	private String lastName;
	@NotEmpty(message = "Raqamni to'liq kiriting")
	private String phone;
	private String address;
	@NotNull(message = "User ID ni to'liq kiriting")
	private int userId;  // Yangi qo'shilgan maydon
	private DebtDTO debts;

	public DebtorAddDTO() {
	}

	public DebtorAddDTO(String firstName, String lastName, String phone, String address, int userId, DebtDTO debts) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
		this.userId = userId;
		this.debts = debts;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public DebtDTO getDebts() {
		return debts;
	}

	public void setDebts(DebtDTO debts) {
		this.debts = debts;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
