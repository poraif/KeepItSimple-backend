package ie.por.keepitsimple.dto.requestbody.term;

import jakarta.validation.constraints.NotBlank;

public class AddTermReqBody {

    @NotBlank(message = "Add a name for the term")
    public String name;

    @NotBlank(message = "choose a category that best fits this term")
    public String category;

    public AddTermReqBody() {
    }

    public AddTermReqBody(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
