package org.springframework.samples.petclinic.repository.jpa;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaVisitRepositoryImpl implements VisitRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Visit findById(int id) throws DataAccessException {
		return this.em.find(Visit.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Visit> findAll() throws DataAccessException {
		return this.em.createQuery("SELECT v FROM Visit v").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Visit> findByPetId(int petId) throws DataAccessException {
		Query query = this.em.createQuery("SELECT v FROM Visit v WHERE v.pet.id = :petId");
		query.setParameter("petId", petId);
		return query.getResultList();
	}

	@Override
	public Visit save(Visit visit) throws DataAccessException {
		if (visit.getId() == null) {
			this.em.persist(visit);
		}
		else {
			visit = this.em.merge(visit);
		}
		return visit;
	}

	@Override
	public void delete(Visit visit) throws DataAccessException {
		this.em.remove(em.contains(visit) ? visit : em.merge(visit));
	}

}
