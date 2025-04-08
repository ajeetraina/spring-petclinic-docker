package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.PetTypeRepository;
import org.springframework.samples.petclinic.repository.SpecialtyRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClinicServiceImpl implements ClinicService {

	private OwnerRepository ownerRepository;
	private PetRepository petRepository;
	private PetTypeRepository petTypeRepository;
	private SpecialtyRepository specialtyRepository;
	private VetRepository vetRepository;
	private VisitRepository visitRepository;

	@Autowired
	public ClinicServiceImpl(OwnerRepository ownerRepository, PetRepository petRepository,
			PetTypeRepository petTypeRepository, SpecialtyRepository specialtyRepository,
			VetRepository vetRepository, VisitRepository visitRepository) {
		this.ownerRepository = ownerRepository;
		this.petRepository = petRepository;
		this.petTypeRepository = petTypeRepository;
		this.specialtyRepository = specialtyRepository;
		this.vetRepository = vetRepository;
		this.visitRepository = visitRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Owner> findAllOwners() throws DataAccessException {
		return ownerRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Owner findOwnerById(int id) throws DataAccessException {
		return ownerRepository.findById(id);
	}

	@Override
	@Transactional
	public Owner saveOwner(Owner owner) throws DataAccessException {
		return ownerRepository.save(owner);
	}

	@Override
	@Transactional
	public void deleteOwner(Owner owner) throws DataAccessException {
		ownerRepository.delete(owner);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<PetType> findPetTypes() throws DataAccessException {
		return petTypeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Pet findPetById(int id) throws DataAccessException {
		return petRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pet> findAllPets() throws DataAccessException {
		return petRepository.findAll();
	}

	@Override
	@Transactional
	public Pet savePet(Pet pet) throws DataAccessException {
		return petRepository.save(pet);
	}

	@Override
	@Transactional
	public void deletePet(Pet pet) throws DataAccessException {
		petRepository.delete(pet);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Visit> findVisitsByPetId(int petId) throws DataAccessException {
		return visitRepository.findByPetId(petId);
	}

	@Override
	@Transactional(readOnly = true)
	public Visit findVisitById(int id) throws DataAccessException {
		return visitRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Visit> findAllVisits() throws DataAccessException {
		return visitRepository.findAll();
	}

	@Override
	@Transactional
	public Visit saveVisit(Visit visit) throws DataAccessException {
		return visitRepository.save(visit);
	}

	@Override
	@Transactional
	public void deleteVisit(Visit visit) throws DataAccessException {
		visitRepository.delete(visit);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vet> findAllVets() throws DataAccessException {
		return vetRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Vet findVetById(int id) throws DataAccessException {
		return vetRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Specialty> findSpecialties() throws DataAccessException {
		return specialtyRepository.findAll();
	}

}
