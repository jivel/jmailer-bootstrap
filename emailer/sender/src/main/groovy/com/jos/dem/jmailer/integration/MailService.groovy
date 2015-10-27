package com.jos.dem.jmailer.integration

interface MailService {
	void sendMailWithTemplate(String email, String sender, Map model, String subject, String template)
}
