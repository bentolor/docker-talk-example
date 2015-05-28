package de.exxcellent.docker.resources;

/**
 * Created by rguderlei on 26.05.15.
 */

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import de.exxcellent.docker.representations.Saying;
import de.exxcellent.docker.representations.SayingView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        try {
            final String hostname = InetAddress.getLocalHost().getHostName();
            final String value = String.format(template, name.or(defaultName), hostname);
            return new Saying(counter.incrementAndGet(), value, hostname);
        } catch (UnknownHostException ex) {
            throw new WebApplicationException(ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Timed
    @Produces(MediaType.TEXT_HTML)
    public SayingView sayHelloAsHtml(@QueryParam("name") Optional<String> name) {
        try {
            final String hostname = InetAddress.getLocalHost().getHostName();
            final Saying saying = new Saying(counter.incrementAndGet(), name.or(defaultName), hostname);
            return new SayingView(saying);
        } catch (UnknownHostException ex) {
            throw new WebApplicationException(ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}