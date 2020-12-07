package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import javax.validation.constraints.NotBlank;
import java.util.UUID;
@ApiModel(value = "用户")
public class Person {
    @ApiModelProperty("userId")
    private final UUID id;
    @NotBlank
    @ApiModelProperty("userName")
    private final String name;

    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
    @ApiOperation(value = "获取id")
    public UUID getId() {
        return id;
    }
    @ApiOperation(value = "获取name")
    public String getName() {
        return name;
    }
}
