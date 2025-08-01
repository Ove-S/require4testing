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
public class TestcaseController implements Serializable {
	
	private TestcaseDAO testcaseDAO;
	private List<Testcase> testcases = new ArrayList<Testcase>();
	private Testcase tempTestcase;
	private List<Testcase> selectableTestcases = new ArrayList<Testcase>();

	@Inject
	Navigator navigator;
	
	@Inject
	RequirementController requirementController;
	
	@Inject
	UserController userController;
	
	@Inject
	TestrunController testrunController;

	
	public TestcaseController() {
		testcaseDAO = new TestcaseDAO();
	}

	public List<Testcase> getTestcases() {
		return testcaseDAO.findAll();
	}

	public void setTestcases(List<Testcase> testcases) {
		this.testcases = testcases;
	}

	public Testcase getTempTestcase() {
		return tempTestcase;
	}

	public void setTempTestcase(Testcase tempTestcase) {
		this.tempTestcase = tempTestcase;
	}
	
    public List<Testcase> getSelectableTestcases() {
		return testcaseDAO.findSelectable();
	}

	public void setSelectableTestcases(List<Testcase> selectableTestcases) {
		this.selectableTestcases = selectableTestcases;
	}
	

	public void postValidateTempTestcaseTitle(ComponentSystemEvent ev) {
        UIInput temp = (UIInput) ev.getComponent();
        this.tempTestcase.setTitle((String) temp.getValue());
    }
    
    public void postValidateTempTestcaseResult(ComponentSystemEvent ev) {
        UIInput temp = (UIInput) ev.getComponent();
        this.tempTestcase.setResult((String) temp.getValue());
    }
    
	
	public void onRowSelect(SelectEvent<Testcase> event) throws IOException {
		String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		navigator.setPreviousPage(viewId);
        tempTestcase = event.getObject();
        FacesContext.getCurrentInstance().getExternalContext().redirect("testcase-details.xhtml");
    }
	
	public String editNewTestcase() {
		navigator.setPreviousPage("requirement-details.xhtml?faces-redirect=true");
		tempTestcase = new Testcase();
		tempTestcase.setAuthor(userController.getCurrentUser().getFullName());
		tempTestcase.setRequirement(requirementController.getTempRequirement());
		return "testcase-details.xhtml?faces-redirect=true";
	}
	
    public String addTestcase() {
    	if(tempTestcase.getId() == null) {
    		requirementController.getTempRequirement().getTestcases().add(tempTestcase);
    		// Anforderunge updaten
    		requirementController.addRequirement();
    	} else {
    		tempTestcase = testcaseDAO.updateTestcase(tempTestcase);
    	}
    	return navigator.getPreviousPage();
    }
    
	public String selectTestcase() {
		navigator.setPreviousPage("testrun-details.xhtml?faces-redirect=true");
		return "select-testcase.xhtml?faces-redirect=true";
	}
	
	public void onTestcaseSelect(SelectEvent<Testcase> event) throws IOException {
		tempTestcase = event.getObject();
		tempTestcase.setTestrun(testrunController.getTempTestrun());
		testrunController.getTempTestrun().getTestcases().add(tempTestcase);
		
		testrunController.setTempTestrun(testcaseDAO.addToTestrun(testrunController.getTempTestrun()));
        FacesContext.getCurrentInstance().getExternalContext().redirect("testrun-details.xhtml");
    }
	
}