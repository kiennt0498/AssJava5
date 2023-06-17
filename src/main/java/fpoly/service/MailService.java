package fpoly.service;

import javax.mail.MessagingException;

import org.springframework.scheduling.annotation.Scheduled;

import fpoly.entity.MailInfo;

public interface MailService {

	void run();

	void queue(String to, String subject, String body);

	void queue(MailInfo mail);

	void send(String to, String subject, String body) throws MessagingException;

	void send(MailInfo mail) throws MessagingException;

}
