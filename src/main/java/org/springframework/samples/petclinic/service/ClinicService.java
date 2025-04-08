package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;

public interface ClinicService {

	List<Owner> findAllOwners() throws DataAccessException;

	Owner findOwnerById(int id) throws DataAccessException;

	Owner saveOwner(Owner owner) throws DataAccessException;
	
	void deleteOwner(Owner owner) throws DataAccessException;
	
	Collection<PetType> findPetTypes() throws DataAccessException;

	Pet findPetById(int id) throws DataAccessException;

	List<Pet> findAllPets() throws DataAccessException;

	Pet savePet(Pet pet) throws DataAccessException;
	
	void deletePet(Pet pet) throws DataAccessException;
	
	Collection<Visit> findVisitsByPetId(int petId) throws DataAccessException;

	Visit findVisitById(int id) throws DataAccessException;

	List<Visit> findAllVisits() throws DataAccessException;

	Visit saveVisit(Visit visit) throws DataAccessException;
	
	void deleteVisit(Visit visit) throws DataAccessException;
	
	List<Vet> findAllVets() throws DataAccessException;

	Vet findVetById(int id) throws DataAccessException;

	Collection<Specialty> findSpecialties() throws DataAccessException;

}
