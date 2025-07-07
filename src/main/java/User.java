import java.io.Serializable;

import jakarta.persistence.*;

@Entity
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer ID;
	private String fullName;
	private String email;
	private String password;
	private boolean requirementsEngineer;
	private boolean testmanager;
	private boolean testcaseAuthor;
	private boolean tester;
	
	
	public User() {
		
	}
	
	public User(String fullName, String email, String password, boolean requirementsEngineer,
				boolean testmanager, boolean testcaseAuthor, boolean tester) {
		
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.requirementsEngineer = requirementsEngineer;
		this.testmanager = testmanager;
		this.testcaseAuthor = testcaseAuthor;
		this.tester = tester;
	}
	

	public Integer getId() {
		return ID;
	}

	public void setId(Integer ID) {
		this.ID = ID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


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

	public boolean isRequirementsEngineer() {
		return requirementsEngineer;
	}

	public void setRequirementsEngineer(boolean requirementsEngineer) {
		this.requirementsEngineer = requirementsEngineer;
	}

	public boolean isTestmanager() {
		return testmanager;
	}

	public void setTestmanager(boolean testmanager) {
		this.testmanager = testmanager;
	}

	public boolean isTestcaseAuthor() {
		return testcaseAuthor;
	}

	public void setTestcaseAuthor(boolean testcaseAuthor) {
		this.testcaseAuthor = testcaseAuthor;
	}

	public boolean isTester() {
		return tester;
	}

	public void setTester(boolean tester) {
		this.tester = tester;
	}
	
}