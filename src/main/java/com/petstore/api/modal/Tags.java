package com.petstore.api.modal;

import com.google.gson.annotations.Expose;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tags {

    @Expose
    private Integer id;

    @Expose
    private String name;


}
