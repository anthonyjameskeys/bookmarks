package net.anthonychaves.bookmarks.models;

import javax.persistence.*;
import org.apache.openjpa.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="bookmarks")
public class Bookmark implements Serializable {
	@Id long id;
	String url;
	int hitCount;
	
	@ManyToOne
	User user;
	
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
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public int getHitCount() {
		return hitCount;
	}
	
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}
}