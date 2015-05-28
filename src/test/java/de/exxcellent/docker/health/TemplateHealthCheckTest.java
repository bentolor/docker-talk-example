package de.exxcellent.docker.health;

import com.codahale.metrics.health.HealthCheck;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by rguderlei on 27.05.15.
 */
public class TemplateHealthCheckTest {

    @org.junit.Test
    public void testCheck() throws Exception {
        TemplateHealthCheck healthCheck = new TemplateHealthCheck("Hello, %s! (from %s)");
        assertThat(healthCheck.check(), is(equalTo(HealthCheck.Result.healthy())));
    }
}