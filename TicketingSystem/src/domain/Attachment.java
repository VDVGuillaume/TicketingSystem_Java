package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name ="Attachments")
public class Attachment implements Serializable{

	@Id
	@Column(name="AttachmentId")
	int attachmentId;
	
}
