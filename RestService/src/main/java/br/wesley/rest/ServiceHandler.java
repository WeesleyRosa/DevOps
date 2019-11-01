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
import br.wesley.http.CatPOJO;
import br.wesley.persistence.CatPersist;


@Path("/service")
public class ServiceHandler {

	private final CatPersist persistCat = new CatPersist();


	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/add")
	public String add(CatPOJO catPojo) {

		Cat entity = new Cat();

		try {

			entity.setAge(catPojo.getAge());
			entity.setName(catPojo.getName());
			entity.setSex(catPojo.getSex());

			persistCat.post(entity);

			return "Registro cadastrado com sucesso!";

		} catch (Exception e) {

			return "Erro ao cadastrar um registro " + e.getMessage();
		}

	}


	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/put")
	public String put(CatPOJO catPojo) {

		Cat entity = new Cat();

		try {

			entity.setAge(catPojo.getAge());
			entity.setName(catPojo.getName());
			entity.setSex(catPojo.getSex());

			persistCat.put(entity);

			return "Registro alterado com sucesso!";

		} catch (Exception e) {

			return "Erro ao alterar o registro " + e.getMessage();

		}

	}


	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/listAll")
	public List<CatPOJO> listAll() {

		List<CatPOJO> cats = new ArrayList<CatPOJO>();

		List<Cat> listaEntityCat = persistCat.getAll();

		for (Cat entity : listaEntityCat) {

			cats.add(new CatPOJO(entity.getId(), entity.getAge(), entity.getName(), entity.getSex()));
		}

		return cats;
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
