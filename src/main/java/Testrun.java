import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class Testrun implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String title;
	private String author;
	
	@ManyToOne
	private User tester;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "testrun", cascade = CascadeType.ALL)
	private List<Testcase> testcases;
	
	public Testrun() {
		
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

	public User getTester() {
		return tester;
	}

	public void setTester(User tester) {
		this.tester = tester;
	}

	public List<Testcase> getTestcases() {
		return testcases;
	}

	public void setTestcases(List<Testcase> testcases) {
		this.testcases = testcases;
	}
	
}