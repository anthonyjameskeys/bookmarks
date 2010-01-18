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

@Entity
@Table(name="persistentLoginTokens")
public class PersistentLoginToken {
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