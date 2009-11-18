import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Bookmark implements Serializable {
	@Id long id;
	String url;
	int hitCount;
}