package ie.por.keepitsimple.dto.requestbody.termcollection;

import jakarta.validation.constraints.NotBlank;

public class AddTermCollectionReqBody {

    @NotBlank(message = "enter collection name")
    private String name;

    @NotBlank(message = "enter short collection description")
    private String description;

    public AddTermCollectionReqBody() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddTermCollectionReqBody(String name, String description) {
        this.name = name;
        this.description = description;
    }
}


