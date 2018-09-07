package com.example.poll.payload.request;

import javax.validation.constraints.Size;

public class PostRequest {

    private String content;

    @Size(min = 2,max = 50)
    private String title;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
