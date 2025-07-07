import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

@Named
@ApplicationScoped
public class RequirementDAO {
	
	public List<Requirement> findAll() {
		EntityManager entityManager = EntityManagerFactoryCreator.emf.createEntityManager();
		Query abfrage = entityManager.createQuery("SELECT a FROM Requirement a ORDER BY a.id DESC");
		List<Requirement> requirements = abfrage.getResultList();
		return requirements;
	}
	
    public boolean isExisting(Integer id) {
    	EntityManager entityManager = EntityManagerFactoryCreator.emf.createEntityManager();
		Query abfrage = entityManager.createQuery("SELECT a FROM Requirement a WHERE a.id = :id").setParameter("id", id);
		List<Requirement> requirements = abfrage.getResultList();
		entityManager.close();
		return requirements.isEmpty();
    }
	
	public Requirement add(Requirement requirement) {
		EntityManager entityManager = EntityManagerFactoryCreator.emf.createEntityManager();
		EntityTransaction t = entityManager.getTransaction();
		t.begin();
        requirement = entityManager.merge(requirement);
        t.commit();
        return requirement;
	}
	
}