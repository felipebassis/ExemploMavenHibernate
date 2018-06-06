package br.com.db1.start.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Test;

import br.com.db1.start.classes.Cidade;
import br.com.db1.start.classes.Endereco;
import br.com.db1.start.tipos.TipoEndereco;
import br.com.db1.start.tipos.TipoLogradouro;
import br.com.db1.start.tipos.Uf;

public class MainTest {

	@Test
	public void metodoTest() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("db1start");
		EntityManager manager = factory.createEntityManager();

		Cidade cidade = new Cidade();
		cidade.setNome("Maringa");
		cidade.setUf(Uf.PR);
		manager.getTransaction().begin();
		manager.persist(cidade);
		manager.getTransaction().commit();

		factory.close();

	}
	
	@Test
	public void enderecoTest(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("db1start");
		EntityManager manager = factory.createEntityManager();
		
		
		Cidade cidade = new Cidade();
		cidade.setId(1);
		
		Endereco endereco = new Endereco();
		endereco.setLogradouro("1");
		endereco.setNumero("12");
		endereco.setTipoLogradouro(TipoLogradouro.RUA);
		endereco.setTipoEndereco(TipoEndereco.RESIDENCIAL);
		endereco.setCep("87050430");
		endereco.setCidade(cidade);
		manager.getTransaction().begin();
		manager.persist(endereco);
		manager.getTransaction().commit();
		
		factory.close();
	}

	@Test
	public void deleteTest() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("db1start");
		EntityManager manager = factory.createEntityManager();

		Cidade cidade = manager.find(Cidade.class, 3L);
		manager.getTransaction().begin();
		manager.remove(cidade);
		manager.getTransaction().commit();

		factory.close();

	}

	@Test
	public void updateTest() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("db1start");
		EntityManager manager = factory.createEntityManager();

		Cidade cidade = manager.find(Cidade.class, 2L);
		cidade.setNome("Maringa");
		manager.getTransaction().begin();
		manager.persist(cidade);
		manager.getTransaction().commit();

		factory.close();

	}

	@Test
	public void selectHqlTest() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("db1start");
		EntityManager manager = factory.createEntityManager();

		Query query = manager.createQuery("Select * from cidade");
		List<Cidade> cidades = query.getResultList();

		factory.close();

	}

	@Test
	public void selectSqlNativoTest() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("db1start");
		EntityManager manager = factory.createEntityManager();

		Query query = manager.createNativeQuery("Select * from cidade c");
		List<Cidade> cidades = query.getResultList();

		factory.close();

	}

}
