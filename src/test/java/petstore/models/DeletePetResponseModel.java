package petstore.models;

import lombok.Data;

@Data
public class DeletePetResponseModel {
    int code;
    String message, type;
}
