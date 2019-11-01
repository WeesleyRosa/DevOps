package br.wesley.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.wesley.entity.Cat;

public class CatPersist {

	private final EntityManagerFactory entityManagerFactory;

	private final EntityManager entityManager;

	public CatPersist() {

		this.entityManagerFactory = Persistence.createEntityManagerFactory("rest_persist_unit");

		this.entityManager = this.entityManagerFactory.createEntityManager();
	}

	public void post(Cat cat) {

		this.entityManager.getTransaction().begin();
		this.entityManager.persist(cat);
		this.entityManager.getTransaction().commit();
	}

	public void put(Cat cat) {

		this.entityManager.getTransaction().begin();
		this.entityManager.merge(cat);
		this.entityManager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Cat> getAll() {

		return this.entityManager.createQuery("SELECT p FROM CATS p ORDER BY p.ID").getResultList();
	}

	public Cat GetById(long id) {

		return this.entityManager.find(Cat.class, id);
	}

	public void delete(long id) {

		Cat cat = this.GetById(id);

		this.entityManager.getTransaction().begin();
		this.entityManager.remove(cat);
		this.entityManager.getTransaction().commit();

	}
}
