package com.belrose.microsvctwo.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto implements Serializable {
    private long id;
    @NonNull
    private String lastName;
    private String firstName;
}
