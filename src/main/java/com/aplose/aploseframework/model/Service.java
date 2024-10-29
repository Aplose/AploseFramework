package com.aplose.aploseframework.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.Duration;
import java.time.Instant;

/**
 * Action that a practitionner can do
 * @author oandrade
 */
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Duration duration;
    private Long minuteDuration;
    private Long hourDuration;
    private Long dayDuration;
    @ManyToOne
    private Person professional;    
    private Boolean isActive = Boolean.TRUE;
    private Instant createInstant = Instant.now();


    private Long price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        this.minuteDuration = duration.toMinutes();
        this.hourDuration = duration.toHours();
        this.dayDuration = duration.toDays();
    }

    public Person getProfessional() {
        return professional;
    }

    public void setProfessional(Person professional) {
        this.professional = professional;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Instant getCreateInstant() {
        return createInstant;
    }

    public void setCreateInstant(Instant createInstant) {
        this.createInstant = createInstant;
    }

    public Long getMinuteDuration() {
        return minuteDuration;
    }

    public Long getHourDuration() {
        return hourDuration;
    }

    public Long getDayDuration() {
        return dayDuration;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
