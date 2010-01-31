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

public class BookmarkDetail {
  
  private int id;
  private String title;
  private String url;
  private int hitCount;
  
  public BookmarkDetail(int id, String title, String url, int hitCount) {
    System.out.println("================================================== CREATING BOOKMARK DETAIL");
    this.id = id;
    this.title = title;
    this.url = url;
    this.hitCount = hitCount;
    System.out.println("================================================== DONE");
  }
  
  public int getId() { return this.id; }
  public String getTitle() { return this.title; }
  public String getUrl() { return this.url; }
  public int getHitCount() { return this.hitCount; }
}