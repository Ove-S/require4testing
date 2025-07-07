import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

@Named
@ApplicationScoped
public class TestcaseDAO {
	
	
	public List<Testcase> findAll() {
		EntityManager em = EntityManagerFactoryCreator.emf.createEntityManager();
		Query abfrage = em.createQuery("SELECT t FROM Testcase t");
		List<Testcase> testcases = abfrage.getResultList();
		em.close();
		return testcases;
	}
	
	public Testcase updateTestcase(Testcase testcase) {
		EntityManager em = EntityManagerFactoryCreator.emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		testcase = em.merge(testcase);
		t.commit();
		em.close();
		return testcase;
	}
	
}