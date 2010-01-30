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

package net.anthonychaves.bookmarks.service;

import net.anthonychaves.bookmarks.models.*;

import org.springframework.stereotype.*;

import java.util.*;

import org.jredis.*;
import org.jredis.ri.alphazero.*;
import static org.jredis.ri.alphazero.support.DefaultCodec.*;

@Service
public class RedisService {

  private JRedis jredis;

  public RedisService() {
    this.jredis = new JRedisClient();
  }

  public void addTags(Bookmark bookmark) {
    StringTokenizer st = new StringTokenizer(bookmark.getTags(), " ");
    
    try {
      while (st.hasMoreTokens()) {
        String token = st.nextToken();
        System.out.println("before token: " + token + " bookmark: " + bookmark.getId());
        jredis.lpush(token, bookmark.getId());
        System.out.println("just put token: " + token + " bookmark: " + bookmark.getId());
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}