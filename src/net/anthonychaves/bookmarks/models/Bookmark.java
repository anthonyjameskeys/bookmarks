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