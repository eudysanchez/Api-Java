package notes;

public class Notes {

    private long id;
    private String body;

    public Notes(long id, String body) {
        this.id = id;
        this.body = body;
    }

    public String getContent() {
        return body;
    }

    //@Override
    public Long getId() {
        return this.id;
    }

   // @Override
    public void setId(Long id) {
        this.id = id;
    }
}