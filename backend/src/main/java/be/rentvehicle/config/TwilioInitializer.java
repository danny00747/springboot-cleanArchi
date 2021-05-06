package be.rentvehicle.config;

import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// @Profile("prod")
@Configuration
public class TwilioInitializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(TwilioInitializer.class);

    @Autowired
    public TwilioInitializer(TwilioConfiguration twilioConfiguration) {
        Twilio.init(
                twilioConfiguration.getAccountSid(),
                twilioConfiguration.getAuthToken()
        );
        LOGGER.info("Twilio initialized ... with account sid {} ", twilioConfiguration.getAccountSid());
        LOGGER.info("Twilio initialized ... with auth {} ", twilioConfiguration.getAuthToken());
        LOGGER.info("Twilio initialized ... with number {} ", twilioConfiguration.getTrialNumber());
    }
}
