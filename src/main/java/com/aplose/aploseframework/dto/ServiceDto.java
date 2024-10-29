package com.aplose.aploseframework.dto;

import java.time.Duration;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



public class ServiceDto {
    
    @NotNull(message = "name cannot be null")
    private String name;
    @NotNull(message = "description cannot be null")
    @Size(min = 80, message = "minimum 80 caracters are required")
    private String description;
    @NotNull(message = "duration cannot be null")
    private Duration duration;
    @NotNull(message = "price cannot be null")
    private Long price;     // prix en centimes



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Duration getDuration() {
        return duration;
    }
    public void setDuration(Duration duration) {
        this.duration = duration;
    }
    public Long getPrice() {
        return price;
    }
    public void setPrice(Long price) {
        this.price = price;
    }

}