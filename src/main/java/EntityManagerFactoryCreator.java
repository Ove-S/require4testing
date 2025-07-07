import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Named
@ApplicationScoped
public class EntityManagerFactoryCreator {
	
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("require4testingPersistenceUnit");
    
	public EntityManagerFactoryCreator() {
		
	}
}