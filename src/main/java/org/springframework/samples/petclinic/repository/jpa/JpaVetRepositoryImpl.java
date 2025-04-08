package org.springframework.samples.petclinic.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaVetRepositoryImpl implements VetRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Vet findById(int id) throws DataAccessException {
		return this.em.find(Vet.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vet> findAll() throws DataAccessException {
		return this.em.createQuery("SELECT vet FROM Vet vet").getResultList();
	}

}
