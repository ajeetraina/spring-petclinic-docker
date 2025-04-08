package org.springframework.samples.petclinic.repository.jpa;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaOwnerRepositoryImpl implements OwnerRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Owner findById(int id) throws DataAccessException {
		return this.em.find(Owner.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Owner> findByLastName(String lastName) throws DataAccessException {
		Query query = this.em.createQuery("SELECT DISTINCT owner FROM Owner owner WHERE owner.lastName LIKE :lastName");
		query.setParameter("lastName", lastName + "%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Owner> findAll() throws DataAccessException {
		Query query = this.em.createQuery("SELECT owner FROM Owner owner");
		return query.getResultList();
	}

	@Override
	public Owner save(Owner owner) throws DataAccessException {
		if (owner.getId() == null) {
			this.em.persist(owner);
		}
		else {
			owner = this.em.merge(owner);
		}
		return owner;
	}

	@Override
	public void delete(Owner owner) throws DataAccessException {
		this.em.remove(em.contains(owner) ? owner : em.merge(owner));
	}

}
