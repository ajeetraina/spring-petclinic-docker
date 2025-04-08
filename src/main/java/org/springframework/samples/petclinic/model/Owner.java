package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "owners")
public class Owner extends Person {

	@Column(name = "address")
	@NotEmpty
	private String address;

	@Column(name = "city")
	@NotEmpty
	private String city;

	@Column(name = "telephone")
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telephone;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	private Set<Pet> pets;

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	protected Set<Pet> getPetsInternal() {
		if (this.pets == null) {
			this.pets = new HashSet<>();
		}
		return this.pets;
	}

	protected void setPetsInternal(Set<Pet> pets) {
		this.pets = pets;
	}

	public List<Pet> getPets() {
		List<Pet> sortedPets = new ArrayList<>(getPetsInternal());
		Collections.sort(sortedPets, new PetComparator());
		return Collections.unmodifiableList(sortedPets);
	}

	public void addPet(Pet pet) {
		if (pet.isNew()) {
			getPetsInternal().add(pet);
		}
		pet.setOwner(this);
	}

	public Pet getPet(String name) {
		return getPet(name, false);
	}

	public Pet getPet(String name, boolean ignoreNew) {
		name = name.toLowerCase();
		for (Pet pet : getPetsInternal()) {
			if (!ignoreNew || !pet.isNew()) {
				String compName = pet.getName();
				compName = compName.toLowerCase();
				if (compName.equals(name)) {
					return pet;
				}
			}
		}
		return null;
	}

}
