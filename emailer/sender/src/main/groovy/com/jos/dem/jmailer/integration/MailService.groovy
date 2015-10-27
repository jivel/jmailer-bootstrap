package com.jos.dem.jmailer.integration

import java.util.Map

interface MailService {
	void sendMailWithEngine(String email, String sender, Map model, String subject, String template)
}
