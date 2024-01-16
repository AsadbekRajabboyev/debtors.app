/******************************************************************************
 * Copyright (c) 2024.  Asadbek Rajabboyev         created 1/15/24, 1:09 PM   *
 ******************************************************************************/

package uz.asadbek.debtors.app.DTO.user;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;
import java.util.Date;

public class UsersDTO {
	@NotEmpty(message = "Ism kiriting")
	@Size(min = 2, max = 30,message = "Ism 2dan 30gacha harfdan iborat bo'lishi zarur")
	private String firstName;
	@NotEmpty(message = "Familiya kiriting")
	@Size(min = 2, max = 30,message = "Familya 2dan 30gacha harfdan iborat bo'lishi zarur")
	private String lastName;
	@Size(min = 10, max = 10, message = "telefon raqamni to'g'ri formatda kiriting")
	private String phone;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@NotEmpty(message = "dd-MM-yyyy formatida kiriting")
	private Date dateOfBirth;
	@NotEmpty(message = "Addressni to'liq kiriting")
	private String address;
	@Column(name = "user_name")
	private String username;
	@Column(name = "parol")
	private String parol;

	public UsersDTO(String firstName, String lastName, String phone, Date dateOfBirth, String address, String username, String parol) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.username = username;
		this.parol = parol;
	}

	public UsersDTO() {
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getParol() {
		return parol;
	}

	public void setParol(String parol) {
		this.parol = parol;
	}
}