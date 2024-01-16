/******************************************************************************
 * Copyright (c) 2024.  Asadbek Rajabboyev         created 1/11/24, 7:09 PM   *
 ******************************************************************************/

package uz.asadbek.debtors.app.DTO.debtor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class DebtorDTO {
	@NotEmpty(message = "Ismni to'liq kiriting")
	@Size(min = 2, max = 30, message = "Ism 2dan 30gacha bo'lgan harfdan iborat bo'lishi kerak")
	private String firstName;
	@NotEmpty(message = "Familyani to'liq kiriting")
	@Size(min = 2, max = 30, message = "Familya 2dan 30gacha bo'lgan harfdan iborat bo'lishi kerak")
	private String lastName;
	@NotEmpty(message = "Raqamni to'liq kiriting")
	@Pattern(regexp = "^\\\\+998[0-9]{9}$", message = "+998 formatda kiriting")
	private String phone;
	private String address;


	public DebtorDTO() {
	}

	public DebtorDTO(String firstName, String lastName, String phone, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
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


