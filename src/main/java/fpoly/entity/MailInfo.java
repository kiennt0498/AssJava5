package fpoly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {
	private String from,to,subject,body;
	private String[] cc,bcc,attachments;
	public MailInfo(String to, String subject, String body) {
		this.from = "Fpt Polytechnic <kienntpd06766@fpt.edu.vn>";
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}
