import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import org.mindrot.jbcrypt.BCrypt;

@Named
@SessionScoped
public class UserController implements Serializable {
	
	private List<User> users = new ArrayList<User>();
	private List<User> tester = new ArrayList<User>();
	
	private User tempUser;
	private User currentUser;
	
	private UserDAO userDAO;
	
	public UserController() {
		userDAO = new UserDAO();
		users = userDAO.findAll();
		
		// Falls noch keine User in der Datenbank existieren, werden diese erzeugt und persistiert.
		if(users.isEmpty()) {
			tempUser = new User("Admin", "Admin@example.com", "123", true, true, true, true);
			addUser();
			tempUser = new User("Requirements Engineer", "RequirementsEngineer@example.com", "123", true, false, false, false);
			addUser();
			tempUser = new User("Testmanager", "Testmanager@example.com", "123", false, true, false, false);
			addUser();
			tempUser = new User("Testfallersteller", "Testfallersteller@example.com", "123", false, false, true, false);
			addUser();
			tempUser = new User("Tester", "Tester@example.com", "123", false, false, false, true);
			addUser();
		}
	}
	
	
    public List<User> getUsers() {
		return userDAO.findAll();
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public User getTempUser() {
		return tempUser;
	}

	public void setTempUser(User tempUser) {
		this.tempUser = tempUser;
	}
	
	public List<User> getTester() {
		return userDAO.findAllTester();
	}

	public void setTester(List<User> tester) {
		this.tester = tester;
	}
	
	public Boolean isUserLoggedIn() {
		return currentUser != null;
	}

	public void addUser() {
		// Fall noch kein User mit der Email existiert wird der User hinzugefügt
		if (getUserByEmail(tempUser.getEmail()) == null) {
			
			//Passwort hashen
			String hashed = BCrypt.hashpw(tempUser.getPassword(), BCrypt.gensalt(12));
			tempUser.setPassword(hashed);
			
			userDAO.add(tempUser);
			tempUser = null;
		}
	}
	
	// Gibt den User mit der übergebenen E-mail zurück, sofern vorhanden.
	// Wird auch vor dem hinzufügen eines Users aufgerufen um die E-Mail unique zu halten
    public User getUserByEmail(String email) {
    	List<User> list = userDAO.findUserByEmail(email);
    	if(!list.isEmpty()) {
    		User user = list.get(0);
			return user;
    	}
    	return null;
    }
}