package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.SimpleVisitTO;

import java.util.List;

public interface PatientService
{
    PatientTO findById(final Long id);
    void deletePatientById(final Long id);
    List<SimpleVisitTO> findVisitsByPatientId(Long patientId);
}
