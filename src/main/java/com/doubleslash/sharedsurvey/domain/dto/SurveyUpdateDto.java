package com.doubleslash.sharedsurvey.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyUpdateDto {

    private Long surveyId;

    private boolean state;

}