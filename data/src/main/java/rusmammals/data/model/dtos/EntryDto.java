package rusmammals.data.model.dtos;

public class EntryDto {
    private String animalsNumber;
    private String author;
    private String bindingAccuracy;
    private String comment;
    private String dataSourceId;
    private String dateOfObservation;
    private String determinationMethodId;
    private String latitude;
    private String longitude;
    private String placeDescription;
    private String speciesId;
    private String uuid;

    public String getSpeciesId() {
        return this.speciesId;
    }

    public void setSpeciesId(String str) {
        this.speciesId = str;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public String getBindingAccuracy() {
        return this.bindingAccuracy;
    }

    public void setBindingAccuracy(String str) {
        this.bindingAccuracy = str;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public String getPlaceDescription() {
        return this.placeDescription;
    }

    public void setPlaceDescription(String str) {
        this.placeDescription = str;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String str) {
        this.author = str;
    }

    public String getDateOfObservation() {
        return this.dateOfObservation;
    }

    public void setDateOfObservation(String str) {
        this.dateOfObservation = str;
    }

    public String getDataSourceId() {
        return this.dataSourceId;
    }

    public void setDataSourceId(String str) {
        this.dataSourceId = str;
    }

    public String getDeterminationMethodId() {
        return this.determinationMethodId;
    }

    public void setDeterminationMethodId(String str) {
        this.determinationMethodId = str;
    }

    public String getAnimalsNumber() {
        return this.animalsNumber;
    }

    public void setAnimalsNumber(String str) {
        this.animalsNumber = str;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }
}
