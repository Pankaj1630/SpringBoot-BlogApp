package com.example.BlogApplication.Payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private Integer catId;
    @NotEmpty
    @Size(min = 4)
    private String catTitle;
    @Size(min = 4,max = 20)
    private String catDesc;
}
