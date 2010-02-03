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
  private String tags;
  
  public BookmarkDetail(String title, String url, String tags) {
    this.title = title;
    this.url = url;
    this.tags = tags;
  }

  public String getTitle() { return this.title; }
  public String getUrl() { return this.url; }
  public String getTags() { return this.tags; }
}