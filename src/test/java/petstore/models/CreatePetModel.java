package petstore.models;

import lombok.Data;

@Data
public class CreatePetModel {
    int id;
    String name, status;
}