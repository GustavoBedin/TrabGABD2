package aplicacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dominio.Usuario;
import dominio.UsuarioLimite;
import dominio.Categoria;
import dominio.Despesa;

public class Programa {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bd2_persistence_unit");
		EntityManager em = emf.createEntityManager();
		
		
		
		LocalDate dateNascimentoUser = LocalDate.parse("2020-01-08");
		LocalDate dateNascimentoUser2 = LocalDate.parse("2020-01-08");
		Usuario user = new Usuario("teste", "teste@gmail.com", dateNascimentoUser);
		Usuario user2 = new Usuario("teste2", "teste2@gmail.com", dateNascimentoUser2);
		
		UsuarioLimite limiteUser = new UsuarioLimite("Limite 1", new BigDecimal("500"), true);
		UsuarioLimite limiteUser2 = new UsuarioLimite("Limite 2", new BigDecimal("5000"), true);
		
		user.setLimite(limiteUser);
		user2.setLimite(limiteUser2);
		limiteUser.setUsuario(user);
		limiteUser2.setUsuario(user2);
		
		em.getTransaction().begin();
		em.persist(limiteUser);
		em.persist(limiteUser2);
		em.persist(user);
		em.persist(user2);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		Despesa userDespesa1 = new Despesa("Compras mercado", new BigDecimal("10.5"));
		Despesa user2Despesa1 = new Despesa("Abastecimento carro", new BigDecimal("100"));
		Despesa userDespesa2 = new Despesa("Parcela celular", new BigDecimal("500"));
		
		user.addDespesa(userDespesa1);
		user.addDespesa(userDespesa2);
		user2.addDespesa(user2Despesa1);
		
		em.persist(userDespesa1);
		em.persist(userDespesa2);
		em.persist(user2Despesa1);
		em.persist(user);
		em.persist(user2);
		em.getTransaction().commit();
		
		
		Categoria cat1 = new Categoria("Teste1");
		Categoria cat2 = new Categoria("Teste2");
		Categoria cat3 = new Categoria("Teste3");
		
		Despesa despesa1 = em.find(Despesa.class, 1L);
		despesa1.addCategoria(cat1);
		despesa1.addCategoria(cat2);
		despesa1.addCategoria(cat3);
		
		Despesa despesa2 = em.find(Despesa.class, 2L);
		despesa2.addCategoria(cat1);
		despesa2.addCategoria(cat2);
		
		em.getTransaction().begin();
		em.persist(cat1);
		em.persist(cat2);
		em.persist(cat3);
		em.persist(despesa1);
		em.persist(despesa2);
		em.getTransaction().commit();

		Query q;
		
	    q = em.createQuery("SELECT u.NOME, l.QUANTIDADE " + "FROM Usuario u inner join u.CODIGOUSUARIOLIMITE l");
	    List<Object[]> list = q.getResultList();
	    for(Object[] arr : list){
			System.out.println(Arrays.toString(arr));
		}	
	    
		System.out.println("-=-=-=-=-=-=-=-=-=-==-");
		
		q = em.createQuery("SELECT u.NOME, d.DESCRICAO " + "FROM Usuario u inner join u.despesasUsuario d");
	    List<Object[]> list2 = q.getResultList();
	    for(Object[] arr2 : list2){
			System.out.println(Arrays.toString(arr2));
		}	
	    
	    System.out.println("-=-=-=-=-=-=-=-=-=-==-");
	    
	    q = em.createQuery("SELECT d.DESCRICAO, c.DESCRICAO " + "FROM Despesa d inner join d.categorias c");
	    List<Object[]> list3 = q.getResultList();
	    for(Object[] arr3 : list3){
			System.out.println(Arrays.toString(arr3));
		}
		
		/*
		em.getTransaction().begin();
		user.setLimite(null);
		em.persist(user);
		
		limite.setUsuario(user2);
		user2.setLimite(limite);
		em.persist(limite);
		
		
		em.persist(user2);
		em.getTransaction().commit();*/
		
		em.close();
		emf.close();
		System.exit(0);
		
		/*
		// Inserindo (precisa ter transaction)
		em.getTransaction().begin();
		em.persist(new Usuario("Teste1", "Teste1@gmail.com"));
		em.persist(new Usuario("Teste2", "Teste2@gmail.com"));
		em.getTransaction().commit();
		System.out.println("Inserido com sucesso!");

		// Atualizando (precisa ter transaction)
		Usuario pessoa = em.find(Usuario.class, 1);
		if (pessoa != null) {
			System.out.println(pessoa);
			em.getTransaction().begin();
			pessoa.setNOME("Teste atualizar");
			em.merge(pessoa);
			em.getTransaction().commit();
			System.out.println("Atualizado com sucesso!");
			System.out.println(pessoa);
		}
		
		
		System.out.println("Imprimir todas pessoas:");
		// Recuperando "n" objetos...
		TypedQuery<Usuario> q = em.createQuery("SELECT u " + "FROM Usuario u", Usuario.class);
		for (Usuario each : q.getResultList()) {
			System.out.println(each);
		}
		
		em.close();
		emf.close();
		System.exit(0);*/
	}

}
