package com.petstore.api.modal;

import com.google.gson.annotations.Expose;
import lombok.*;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

    @Expose
    private Long id;

    @Expose
    private Category category;

    @Expose
    private String name;

    @Expose
    private List<String> photoUrls;

    @Expose
    private List<Tags> tags;

    @Expose
    private String status;

}
