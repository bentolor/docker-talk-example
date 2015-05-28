package de.exxcellent.docker.representations;

import io.dropwizard.views.View;

/**
 * Created by rguderlei on 27.05.15.
 */
public class SayingView extends View {
    private final Saying saying;

    public SayingView(Saying saying) {
        super("saying.mustache");

        this.saying = saying;
    }

    public Saying getSaying() {
        return saying;
    }
}
