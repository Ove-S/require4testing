import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
public class Testcase implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String title;
	private String author;
	private String result;
	
    @ManyToOne
	private Requirement requirement;
    
    @ManyToOne
	private Testrun testrun;
	
    
	public Testcase() {
		
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Requirement getRequirement() {
		return requirement;
	}

	public void setRequirement(Requirement requirement) {
		this.requirement = requirement;
	}

	public Testrun getTestrun() {
		return testrun;
	}

	public void setTestrun(Testrun testrun) {
		this.testrun = testrun;
	}
	
}