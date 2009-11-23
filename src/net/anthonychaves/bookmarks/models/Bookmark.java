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
}