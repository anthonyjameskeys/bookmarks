import javax.persistence.*;

@Entity
public class User {
	@Id int id;
	String name;
	String emailAddress;
	
	@Version int version;
	
	// try @Embeddable
}