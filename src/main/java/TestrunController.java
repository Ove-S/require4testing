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
public class TestrunController implements Serializable {
	
	private List<Testrun> testruns = new ArrayList<Testrun>();
	private Testrun tempTestrun;
	private TestrunDAO testrunDAO;
	
	@Inject
	Navigator navigator;
	
	@Inject
	UserController userController;
	
	
	public TestrunController() {
		testrunDAO = new TestrunDAO();
	}

    public List<Testrun> getTestruns() {
		return testrunDAO.findAll();
	}

	public void setTestruns(List<Testrun> testruns) {
		this.testruns = testruns;
	}

	public Testrun getTempTestrun() {
		return tempTestrun;
	}

	public void setTempTestrun(Testrun tempTestrun) {
		this.tempTestrun = tempTestrun;
	}


	public void postValidateTempTestrunTitle(ComponentSystemEvent ev) {
        UIInput temp = (UIInput) ev.getComponent();
        this.tempTestrun.setTitle((String) temp.getValue());
    }
    

	public String editNewTestrun() {
		tempTestrun = new Testrun();
		tempTestrun.setAuthor(userController.getCurrentUser().getFullName());
		return "testrun-details.xhtml?faces-redirect=true";
	}
	
    public String addTestrun() {
    	tempTestrun = testrunDAO.add(tempTestrun);
    	return "testruns.xhtml?faces-redirect=true";
    }
	
	public void onRowSelect(SelectEvent<Testrun> event) throws IOException {
        tempTestrun = event.getObject();
        FacesContext.getCurrentInstance().getExternalContext().redirect("testrun-details.xhtml");
    }
	
	public String selectTester() {
		navigator.setPreviousPage("testrun-details.xhtml?faces-redirect=true");
		return "select-tester.xhtml?faces-redirect=true";
	}
	
	public void onUserSelect(SelectEvent<User> event) throws IOException {
        tempTestrun.setTester(event.getObject());
        testrunDAO.add(tempTestrun);
        FacesContext.getCurrentInstance().getExternalContext().redirect("testrun-details.xhtml");
    }

}