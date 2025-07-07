import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.SelectEvent;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class RequirementController implements Serializable {
	
	private List<Requirement> requirements = new ArrayList<Requirement>();
	private Requirement tempRequirement;
	private RequirementDAO requirementDAO;
	
	@Inject
	Navigator navigator;
	
	@Inject
	UserController userController;
	
	
	public RequirementController() {
		requirementDAO = new RequirementDAO();
	}

	
	public List<Requirement> getRequirements() {
		return requirementDAO.findAll();
	}

	public void setRequirements(List<Requirement> requirements) {
		this.requirements = requirements;
	}
	
	public Requirement getTempRequirement() {
		return tempRequirement;
	}

	public void setTempRequirement(Requirement tempRequirement) {
		this.tempRequirement = tempRequirement;
	}
	
	
    public void postValidateRequirementTitle(ComponentSystemEvent ev) {
        UIInput temp = (UIInput) ev.getComponent();
        this.tempRequirement.setTitle((String) temp.getValue());
    }
    

	public String editNewRequirement() {
		tempRequirement = new Requirement();
		tempRequirement.setAuthor(userController.getCurrentUser().getFullName());
		return "requirement-details.xhtml?faces-redirect=true";
	}
	
    public String addRequirement() {
    	tempRequirement = requirementDAO.add(tempRequirement);
    	return "requirements.xhtml?faces-redirect=true";
    }
    
	
	public void onRowSelectRequirement(SelectEvent<Requirement> event) throws IOException {
        tempRequirement = event.getObject();
        FacesContext.getCurrentInstance().getExternalContext().redirect("requirement-details.xhtml");
    }
	
}