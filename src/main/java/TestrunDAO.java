import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

@Named
@ApplicationScoped
public class TestrunDAO {
	
	public List<Testrun> findAll() {
		EntityManager em = EntityManagerFactoryCreator.emf.createEntityManager();
		Query abfrage = em.createQuery("SELECT t FROM Testrun t ORDER BY t.id DESC");
		List<Testrun> testruns = abfrage.getResultList();
		em.close();
		return testruns;
	}
	
	public Testrun add(Testrun testrun) {
	    EntityManager em = EntityManagerFactoryCreator.emf.createEntityManager();
	    EntityTransaction t = em.getTransaction();
	    t.begin();
	    testrun = em.merge(testrun);
	    t.commit();
	    em.close();
	    return testrun;
	}
	
}