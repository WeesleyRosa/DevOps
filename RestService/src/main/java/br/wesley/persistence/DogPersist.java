package br.wesley.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.wesley.entity.Dog;

public class DogPersist {

	private final EntityManagerFactory entityManagerFactory;

	private final EntityManager entityManager;

	public DogPersist() {

		this.entityManagerFactory = Persistence.createEntityManagerFactory("rest_persist_unit");

		this.entityManager = this.entityManagerFactory.createEntityManager();
	}

	public void post(Dog dog) {

		this.entityManager.getTransaction().begin();
		this.entityManager.persist(dog);
		this.entityManager.getTransaction().commit();
	}

	public void put(Dog dog) {

		this.entityManager.getTransaction().begin();
		this.entityManager.merge(dog);
		this.entityManager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Dog> getAll() {

		return this.entityManager.createQuery("SELECT p FROM DOGS p ORDER BY p.ID").getResultList();
	}

	public Dog GetById(long id) {

		return this.entityManager.find(Dog.class, id);
	}

	public void delete(long id) {

		Dog dog = this.GetById(id);

		this.entityManager.getTransaction().begin();
		this.entityManager.remove(dog);
		this.entityManager.getTransaction().commit();

	}
}
