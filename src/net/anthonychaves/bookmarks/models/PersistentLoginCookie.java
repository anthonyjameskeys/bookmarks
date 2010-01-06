package net.anthonychaves.bookmarks.models;

import javax.persistence.*;

@Entity
@Table(name="persistentLoginCookies")
public class PersistentLoginCookie {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO, generator="uuid-hex")
  private String id;
  
  @ManyToOne
  private User user;
  
  public void setUser(User user) {
    this.user = user;
  }
  
  public User getUser() {
    return user;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getId() {
    return id;
  }
}