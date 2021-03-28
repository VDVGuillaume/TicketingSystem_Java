package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="Comments")
public class Comment implements Serializable {
	
	@Id
	@Column(name="CommentId")
	int commentId;
	

}
