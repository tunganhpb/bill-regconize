package com.pmone.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "boundingBox",
    "text"
})
public class Word {

    @JsonProperty("boundingBox")
    private List<Integer> boundingBox = null;
    @JsonProperty("text")
    private String text;
    @JsonProperty("confidence")
    private String confidence;

    @JsonProperty("boundingBox")
    public List<Integer> getBoundingBox() {
        return boundingBox;
    }

    @JsonProperty("boundingBox")
    public void setBoundingBox(List<Integer> boundingBox) {
        this.boundingBox = boundingBox;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("confidence")
    public String getConfidence() {
        return confidence;
    }

    @JsonProperty("confidence")
    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }
}
