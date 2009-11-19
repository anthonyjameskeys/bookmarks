import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

@Entity
public class User implements Serializable {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid-hex")
	String id;
	
	String name;
	String emailAddress;
	
	@OneToMany
	List<Bookmark> bookmarks = new ArrayList<Bookmark>();
	
	@Version 
	int version;
	
	// try @Embeddable
}