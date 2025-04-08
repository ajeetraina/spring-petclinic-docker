package org.springframework.samples.petclinic.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaPetRepositoryImpl implements PetRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Pet findById(int id) throws DataAccessException {
		return this.em.find(Pet.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pet> findAll() throws DataAccessException {
		Query query = this.em.createQuery("SELECT pet FROM Pet pet");
		return query.getResultList();
	}

	@Override
	public Pet save(Pet pet) throws DataAccessException {
		if (pet.getId() == null) {
			this.em.persist(pet);
		}
		else {
			pet = this.em.merge(pet);
		}
		return pet;
	}

	@Override
	public void delete(Pet pet) throws DataAccessException {
		this.em.remove(em.contains(pet) ? pet : em.merge(pet));
	}

}
