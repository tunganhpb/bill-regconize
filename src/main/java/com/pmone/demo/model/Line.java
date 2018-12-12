package com.pmone.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "boundingBox",
    "text",
    "words"
})
public class Line {

    @JsonProperty("boundingBox")
    private List<Integer> boundingBox = null;
    @JsonProperty("text")
    private String text;
    @JsonProperty("words")
    private List<Word> words = null;

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

    @JsonProperty("words")
    public List<Word> getWords() {
        return words;
    }

    @JsonProperty("words")
    public void setWords(List<Word> words) {
        this.words = words;
    }

}
