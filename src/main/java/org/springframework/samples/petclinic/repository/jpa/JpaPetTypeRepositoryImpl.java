package org.springframework.samples.petclinic.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.PetTypeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaPetTypeRepositoryImpl implements PetTypeRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public PetType findById(int id) throws DataAccessException {
		return this.em.find(PetType.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PetType> findAll() throws DataAccessException {
		return this.em.createQuery("SELECT ptype FROM PetType ptype").getResultList();
	}

}
