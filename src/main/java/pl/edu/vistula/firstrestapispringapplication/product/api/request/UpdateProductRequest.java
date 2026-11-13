package pl.edu.vistula.firstrestapispringapplication.product.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public class UpdateProductRequest extends ProductRequest {

    private final Long id;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UpdateProductRequest(String name, Long id) {
        super(name);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
