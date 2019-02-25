package notes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotesModel {

    private final long id;
    private final String content;

    public NotesModel(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @JsonProperty("id")
    public Long getId() {
        return this.id;
    }
}