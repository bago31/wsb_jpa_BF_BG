package com.jpacourse.dto;

import com.jpacourse.persistance.enums.TreatmentType;

public class MedicalTreatmentTO {
    private Long id;
    private String description;
    private TreatmentType type;
    private VisitTO visit;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TreatmentType getType() {
        return type;
    }

    public void setType(TreatmentType type) {
        this.type = type;
    }

    public VisitTO getVisit() {
        return visit;
    }

    public void setVisit(VisitTO visit) {
        this.visit = visit;
    }
}
