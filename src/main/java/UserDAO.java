import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

@Named
@ApplicationScoped
public class UserDAO {
	
	// Gibt eine Liste aller User aus der Datenbank zurück.
	public List<User> findAll() {
		EntityManager em = EntityManagerFactoryCreator.emf.createEntityManager();
		Query abfrage = em.createQuery("SELECT u FROM User u");
		List<User> users = abfrage.getResultList();
		em.close();
		return users;
	}
	
	// Gibt eine Liste mit den Usern aus der Datenbank zurück,
	// die die übergebene E-Mail haben (Im Normalfall ein User oder leer).
    public List<User> findUserByEmail(String email) {
    	EntityManager em = EntityManagerFactoryCreator.emf.createEntityManager();
		Query abfrage = em.createQuery("SELECT u FROM User u WHERE u.email = :email").setParameter("email", email);
		List <User> users = abfrage.getResultList();
		em.close();
		return users;
    }
	
    // Persistiert den User in der Datenbank.
	public void add(User user) {
		EntityManager em = EntityManagerFactoryCreator.emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
        em.merge(user);
        t.commit();
        em.close();
	}
	
	public List<User> findAllTester() {
		EntityManager em = EntityManagerFactoryCreator.emf.createEntityManager();
		Query abfrage = em.createQuery("SELECT u FROM User u WHERE u.tester = true");
		List<User> tester = abfrage.getResultList();
		em.close();
		return tester;
	}
	
}