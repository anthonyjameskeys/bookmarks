/**
  Copyright 2010 Anthony Chaves
  
  This file is part of Bookmarks.

  Bookmarks is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  Bookmarks is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with Bookmarks.  If not, see <http://www.gnu.org/licenses/>.
*/

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
	@OrderBy("id DESC")
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
	
	public List<BookmarkDetail> getBookmarksDetail() {
	  List<BookmarkDetail> bd = new ArrayList<BookmarkDetail>();
	  
	  for (Bookmark b : getBookmarks()) {
	    bd.add(new BookmarkDetail(b.getId(), b.getTitle(), b.getUrl(), b.getTags()));
	  }
	  
	  return bd;
	}
}