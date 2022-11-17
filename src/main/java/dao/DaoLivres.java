package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entity.Livre;
import util.HibernateUtil;

public class DaoLivres {
	private Livre livre;
	private int idLivre;
	private static List<Livre> listLivre = getAll();

	public DaoLivres() {
		getAll();
	}

	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	public int getEtudiantId() {
		return idLivre;
	}

	public void setEtudiantId(int idLivre) {
		this.idLivre = idLivre;
	}

	public List<Livre> getListLivre() {
		return listLivre;
	}

	public void setListLivre(List<Livre> listLivre) {
		this.listLivre = listLivre;
	}

	public void persist(Livre livre) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			session.persist(livre);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Echec de l'insertion du livre : " + e.getMessage());
			transaction.rollback();
		}
		session.close();
	}

	public static void update(Livre livre) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(livre);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Echec de l'insertion du livre : " + e.getMessage());
			transaction.rollback();
		}
		session.close();
	}

	public static void delete(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Livre et = session.get(Livre.class, id);
			session.delete(et);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Echec de la suppression du livre : " + e.getMessage());
			transaction.rollback();
		}
		session.close();
	}
	
	public static List<Livre> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Query query = session.createSQLQuery("SELECT * FROM livre");
		List<Livre> listLivre = query.list();
		System.out.println(listLivre);
		session.close();
		System.out.println("liste : " + listLivre);
		return listLivre;
	}
	
	public Livre getLivreByTitre(String titre) {
		Livre livre1 = new Livre();
		for(Livre livre : listLivre )
			if(livre.getTitre() == titre)
				livre1 = livre;
		return livre1;
	}

}
