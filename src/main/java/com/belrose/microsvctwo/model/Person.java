package com.belrose.microsvctwo.model;


import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "person")
public class Person implements Serializable {
    @Id
    private long id;
    @NotBlank(message = "Every person must have a lastname")
    private String lastName;
    private String firstName;
}
