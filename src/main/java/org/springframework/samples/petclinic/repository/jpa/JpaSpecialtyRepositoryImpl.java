package org.springframework.samples.petclinic.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.repository.SpecialtyRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaSpecialtyRepositoryImpl implements SpecialtyRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Specialty findById(int id) throws DataAccessException {
		return this.em.find(Specialty.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Specialty> findAll() throws DataAccessException {
		return this.em.createQuery("SELECT s FROM Specialty s").getResultList();
	}

}
