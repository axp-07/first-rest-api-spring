package pl.edu.vistula.firstrestapispringapplication.product.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ProductRequest {

    private final String name;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ProductRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
