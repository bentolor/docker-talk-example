package de.exxcellent.docker.representations;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class Saying {
    private long id;

    @Length(max = 3)
    private String content;
    private String hostname;

    public Saying() {
        // Jackson deserialization
    }

    public Saying(long id, String content, String hostname) {
        this.id = id;
        this.content = content;
        this.hostname = hostname;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }

    public String getHostname() {
        return hostname;
    }
}