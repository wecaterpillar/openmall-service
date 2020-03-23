package org.openmall.mall.portal.config;

import com.paypal.base.rest.APIContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayApiConfig {

    //// paypal
    @Value("${paypal.mode:sandbox}")
    private String paypalMode;
    @Value("${paypal.client.app}")
    private String paypalClientId;
    @Value("${paypal.client.secret}")
    private String paypalClientSecret;

    @Bean
    public APIContext apiContext() {
        APIContext apiContext = new APIContext(paypalClientId, paypalClientId, paypalMode);
        return apiContext;
    }
}
