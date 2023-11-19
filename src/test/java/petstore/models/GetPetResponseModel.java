package petstore.models;

import lombok.Data;

@Data
public class GetPetResponseModel {
    int code;
    String type, message;
}
