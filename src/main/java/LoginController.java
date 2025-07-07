import jakarta.faces.application.FacesMessage;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

import org.mindrot.jbcrypt.BCrypt;

@Named
@ViewScoped
public class LoginController implements Serializable {

    @Inject
    private UserController userController;
    
    private String email;
    private String password;
    private String failureMessage = "";

    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }
    
    
    public void postValidateEmail(ComponentSystemEvent ev) {
        UIInput temp = (UIInput) ev.getComponent();
        this.email = (String) temp.getValue();
    }

    

    // Prüft ob der User eingeloggt ist
    public void checkLogin() {
        if(!userController.isUserLoggedIn()) {
            FacesContext fc = FacesContext.getCurrentInstance();
            NavigationHandler nh = fc.getApplication().getNavigationHandler();
            nh.handleNavigation(fc, null, "login.xhtml?faces-redirect=true");
        }
    }
    

    public String logout() {
        userController.setCurrentUser(null);
        return "login.xhtml?faces-redirect=true";
    }
    

    public void validateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String password = (String) value;
        userController.setCurrentUser(null);
        
        User user = userController.getUserByEmail(email);
        
        // User wurde gefunden und Passwort ist korrekt
        if(user != null && BCrypt.checkpw(password, user.getPassword())) {
        	userController.setCurrentUser(user);
        	return;
        }
    	throw new ValidatorException(new FacesMessage("E-Mail oder Passwort falsch!"));
    }
    

    public String login() {
        if (userController.isUserLoggedIn()) {
        	this.failureMessage = "";
        	return "requirements.xhtml?faces-redirect=true";
        }
        return "login.xhtml?faces-redirect=true";
    }
    
}