package net.anthonychaves.bookmarks.models;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

@Entity
@Table(name="users")
public class User implements Serializable {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid-hex")
	public String id;
	
	public String name;
	public String emailAddress;
	
	@OneToMany
	List<Bookmark> bookmarks = new ArrayList<Bookmark>();
	
	@Version 
	public int version;
	
	// try @Embeddable
}