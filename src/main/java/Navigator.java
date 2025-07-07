import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@SessionScoped
@Named
public class Navigator implements Serializable {

    private String previousPage;

    public void setPreviousPage(String previousPage) {
    	this.previousPage = previousPage;
    }

    public String getPreviousPage() {
        return previousPage;
    }
    
    public String back() {
    	return previousPage;
    }
    
    public String showRequirements() {
    	return "requirements.xhtml?faces-redirect=true";
    }
    
    public String showTestcases() {
    	return "testcases.xhtml?faces-redirect=true";
    }
    
    public String showTestruns() {
    	return "testruns.xhtml?faces-redirect=true";
    }
    
}