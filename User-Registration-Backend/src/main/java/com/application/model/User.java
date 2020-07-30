package com.application.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="USERS")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@NotEmpty(message = "Name is required")
	@Size(max = 60, message = "please enter a name with size less than or equal 60")
	private String name;

	@Email(message = "Email format is invalide")
	@NotBlank(message = "Email is required")
	@Column(unique = true)
	private String email;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Gender is required")
	private Gender gender;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inscriptionDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;


	public String getEmail() {
		return email;
	}

	public Gender getGender() {
		return gender;
	}

	public int getId() {
		return id;
	}

	public Date getInscriptionDate() {
		return inscriptionDate;
	}

	public String getName() {
		return name;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	@PrePersist
	protected void onCreate() {
		inscriptionDate  = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updateDate = new Date();
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setInscriptionDate(Date inscriptionDate) {
		this.inscriptionDate = inscriptionDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
