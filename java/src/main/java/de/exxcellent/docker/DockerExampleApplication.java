package de.exxcellent.docker;

import de.exxcellent.docker.configuration.DockerExampleConfiguration;
import de.exxcellent.docker.health.TemplateHealthCheck;
import de.exxcellent.docker.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

/**
 * Created by rguderlei on 26.05.15.
 */
public class DockerExampleApplication extends Application<DockerExampleConfiguration> {
    public static void main(String[] args) throws Exception {
        new DockerExampleApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<DockerExampleConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<DockerExampleConfiguration>()); // add mustache support
    }

    @Override
    public void run(DockerExampleConfiguration configuration,
                    Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        environment.jersey().register(resource);
    }

}