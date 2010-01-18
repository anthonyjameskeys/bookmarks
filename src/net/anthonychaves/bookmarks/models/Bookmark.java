package net.anthonychaves.bookmarks.models;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="bookmarks")
public class Bookmark implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String url;
	private int hitCount;
	private String title;
	
	@ManyToOne
	private User user;
	
	private String tags;
	
	@PreRemove
	public void preRemove() {
	  setUser(null);
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getHitCount() {
		return hitCount;
	}
	
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}
	
	public void setTitle(String title) {
	  this.title = title;
	}
	
	public String getTitle() {
	  return title;
	}
	
	public void setTags(String tags) {
	  this.tags = tags;
	}
	
	public String getTags() {
	  return tags;
	}
}