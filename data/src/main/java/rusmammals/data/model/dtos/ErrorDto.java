package rusmammals.data.model.dtos;

public class ErrorDto {
    private String field;
    private String message;

    public String getField() {
        return this.field;
    }

    public void setField(String str) {
        this.field = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
