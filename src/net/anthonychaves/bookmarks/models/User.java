package net.anthonychaves.bookmarks.models;

import javax.persistence.*;

import org.apache.openjpa.persistence.jdbc.*;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

@Entity
@Table(name="users", uniqueConstraints={@UniqueConstraint(columnNames="openIdIdentifier"), @UniqueConstraint(columnNames="apiKey"), 
                                        @UniqueConstraint(columnNames="emailAddress")})
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

  private String openIdIdentifier;
	private String apiKey;

	private String firstName;
	private String lastName;
	private String emailAddress;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	List<Bookmark> bookmarks = new ArrayList<Bookmark>();
	
	@Version 
	private int version;
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
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
	
	public void setApiKey(String apiKey) {
	  this.apiKey = apiKey;
	}
	
	public String getApiKey() {
	  return apiKey;
	}
	
	public void setOpenIdIdentifier(String openIdIdentifier) {
	  this.openIdIdentifier = openIdIdentifier;
	}
	
	public String getOpenIdIdentifier() {
	  return openIdIdentifier;
	}
}