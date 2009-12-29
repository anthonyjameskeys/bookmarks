package net.anthonychaves.bookmarks.models;

import javax.persistence.*;

import org.apache.openjpa.persistence.jdbc.*;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

@Entity
@Table(name="users", uniqueConstraints={@UniqueConstraint(columnNames="name")})
public class User implements Serializable {
	@Id
	public int id;

	public String name;
	public String emailAddress;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	List<Bookmark> bookmarks = new ArrayList<Bookmark>();
	
//	@Version 
//	public int version;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setBookmarks(List<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}
	
	public List<Bookmark> getBookmarks() {
		return bookmarks;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	// try @Embeddable
}