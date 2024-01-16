package uz.asadbek.debtors.app.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	@NotEmpty(message = "Ism kiriting")
	@Size(min = 2, max = 30,message = "Ism 2dan 30gacha harfdan iborat bo'lishi zarur")
	private String firstName;
	@Column(name = "last_name")
	@NotEmpty(message = "Familiya kiriting")
	@Size(min = 2, max = 30,message = "Familya 2dan 30gacha harfdan iborat bo'lishi zarur")
	private String lastName;
	@Column(name = "phone")
	@Size(min = 10, max = 10, message = "telefon raqamni to'g'ri formatda kiriting")
	private String phone;
	@Column(name = "date_of_birth")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@NotEmpty(message = "dd-MM-yyyy formatida kiriting")
	private Date dateOfBirth;
	@Column(name = "address")
	@NotEmpty(message = "Addressni to'liq kiriting")
	private String address;
	@Column(name = "user_name")
	private String username;
	@Column(name = "parol")
	private String parol;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	@Column(name = "role")
	private String role;

	@JsonManagedReference
	@OneToMany(mappedBy = "users")
	private List<Debtor> debtorList;

	public Users(int id, String firstName, String lastName, String phone, Date dateOfBirth,
	             String address, String username, String parol,
	             LocalDateTime createdAt, LocalDateTime updatedAt,
	             String role, List<Debtor> debtorList) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.username = username;
		this.parol = parol;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.role = role;
		this.debtorList = debtorList;
	}

	public Users(String firstName, String lastName,
	             String phone, Date dateOfBirth,
	             String address, String username,
	             String parol, LocalDateTime createdAt,
	             LocalDateTime updatedAt, String role,
	             List<Debtor> debtorList) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.username = username;
		this.parol = parol;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.role = role;
		this.debtorList = debtorList;
	}

	public Users() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Debtor> getDebtorList() {
		return debtorList;
	}

	public void setDebtorList(List<Debtor> debtorList) {
		this.debtorList = debtorList;
	}
}
