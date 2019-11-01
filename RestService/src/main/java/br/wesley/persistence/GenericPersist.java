package br.wesley.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericPersist {

	private final EntityManagerFactory entityManagerFactory;

	private final EntityManager entityManager;

	public GenericPersist() {

		this.entityManagerFactory = Persistence.createEntityManagerFactory("rest_persist_unit");

		this.entityManager = this.entityManagerFactory.createEntityManager();
	}

	public void post(Object object) {

		this.entityManager.getTransaction().begin();
		this.entityManager.persist(object);
		this.entityManager.getTransaction().commit();
	}

	public void put(Object object) {

		this.entityManager.getTransaction().begin();
		this.entityManager.merge(object);
		this.entityManager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Object> getAll(String entitysName) {

		String query = "SELECT p FROM " + entitysName.toUpperCase() + "S p ORDER BY p.ID";

		return this.entityManager.createQuery(query).getResultList();
	}

	public Object GetById(long id, String entitysName) {

		// String query = "SELECT p FROM " + entitysName.toUpperCase() + "S" + "
		// p ORDER BY p.ID";
		String query = "SELECT * FROM" + entitysName.toUpperCase() + "S WHERE ID = 1";
		
		return this.entityManager.createNativeQuery(query);

		// return this.entityManager.find(entityClass, id);
	}

	public void delete(long id, Class<?> entityClass) {

		Object object = this.GetById(id, entityClass);

		this.entityManager.getTransaction().begin();
		this.entityManager.remove(object);
		this.entityManager.getTransaction().commit();

	}
}
