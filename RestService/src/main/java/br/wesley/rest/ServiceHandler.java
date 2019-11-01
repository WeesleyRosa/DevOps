package br.wesley.rest;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.wesley.entity.Cat;
import br.wesley.entity.Dog;
import br.wesley.http.CatPOJO;
import br.wesley.http.DogPOJO;
import br.wesley.persistence.CatPersist;
import br.wesley.persistence.DogPersist;
import br.wesley.persistence.GenericPersist;

@Path("/service")
public class ServiceHandler {

	private final CatPersist persistCat = new CatPersist();
	private final DogPersist persistDog = new DogPersist();
	private final GenericPersist genericHandler = new GenericPersist();

	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/add")
	public String add(Object pojo) {

		Object entity = new Object();

		try {

			if (pojo instanceof DogPOJO) {

				((Dog) entity).setAge(((DogPOJO) pojo).getAge());
				((Dog) entity).setName(((DogPOJO) pojo).getName());
				((Dog) entity).setSex(((DogPOJO) pojo).getSex());

				persistDog.post((Dog) entity);
			}

			else if (pojo instanceof CatPOJO) {

				((Cat) entity).setAge(((CatPOJO) pojo).getAge());
				((Cat) entity).setName(((CatPOJO) pojo).getName());
				((Cat) entity).setSex(((CatPOJO) pojo).getSex());

				persistCat.post((Cat) entity);
			}

			else
				return "Objeto não identificado";

			return "Registro cadastrado com sucesso!";

		} catch (Exception e) {

			return "Erro ao cadastrar um registro " + e.getMessage();
		}

	}

	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/put")
	public String put(Object pojo) {

		Object entity = new Object();

		try {

			if (pojo instanceof DogPOJO) {

				((Dog) entity).setAge(((DogPOJO) pojo).getAge());
				((Dog) entity).setName(((DogPOJO) pojo).getName());
				((Dog) entity).setSex(((DogPOJO) pojo).getSex());

				persistDog.post((Dog) entity);
			}

			else if (pojo instanceof CatPOJO) {

				((Cat) entity).setAge(((CatPOJO) pojo).getAge());
				((Cat) entity).setName(((CatPOJO) pojo).getName());
				((Cat) entity).setSex(((CatPOJO) pojo).getSex());

				persistCat.post((Cat) entity);
			}

			else
				return "Objeto não identificado";

			return "Registro alterado com sucesso!";

		} catch (Exception e) {

			return "Erro ao alterar o registro " + e.getMessage();

		}

	}

	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/listAll/{entitysName}")
	public List<Object> listAll(@PathParam("entitysName") String entitysName) {

		List<Object> pojos = new ArrayList<Object>();

		List<Object> listEntity = genericHandler.getAll(entitysName);

		if (listEntity instanceof Cat) {
			for (Object singleObject : listEntity) {

				pojos.add(new CatPOJO(((Cat) singleObject).getId(), ((Cat) singleObject).getAge(),
						((Cat) singleObject).getName(), ((Cat) singleObject).getSex()));
			}
			return pojos;
		}

		else if (listEntity instanceof Dog) {
			for (Object singleObject : listEntity) {
				pojos.add(new DogPOJO(((Dog) singleObject).getId(), ((Dog) singleObject).getAge(),
						((Dog) singleObject).getName(), ((Dog) singleObject).getSex()));
			}
			return pojos;
		}
		return null;
	}

	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getCat/{id}")
	public CatPOJO GetCat(@PathParam("id") Integer id) {

		Cat entity = persistCat.GetById(id);

		if (entity != null)
			return new CatPOJO(entity.getId(), entity.getAge(), entity.getName(), entity.getSex());

		return null;
	}

	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/delete/{id}")
	public String delete(@PathParam("id") Integer id) {

		try {

			persistCat.delete(id);

			return "Registro excluido com sucesso!";

		} catch (Exception e) {

			return "Erro ao excluir o registro! " + e.getMessage();
		}

	}

}
