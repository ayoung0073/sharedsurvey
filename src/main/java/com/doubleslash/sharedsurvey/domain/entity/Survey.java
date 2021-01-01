package com.doubleslash.sharedsurvey.domain.entity;

import com.doubleslash.sharedsurvey.domain.Timestamped;
import com.doubleslash.sharedsurvey.domain.dto.survey.SurveyRequestDto;
import com.doubleslash.sharedsurvey.domain.dto.survey.SurveyUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Entity
public class Survey extends Timestamped {
    // id, writer, name, description, startDate, endDate,
    // regDate, state, responseCount, point, picture

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long writer; // Member - id

    private String name;

    private String category;

    private String description;

    private Date startDate;

    private Date endDate;

    private boolean state;

    private int point;

    private String filename = "";

    private int responseCount;

    private boolean existFile = false;

    //@OneToMany(mappedBy="survey", targetEntity = Question.class) // 이거와 @JoinColumn 같이 쓰면 안됨
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_key")
    private List<Question> questions;


    public Survey(SurveyRequestDto requestDto){
        this.writer = requestDto.getWriterId();
        this.category = requestDto.getCategory();
        this.name = requestDto.getName();
        this.description = requestDto.getDescription();
        this.startDate = requestDto.getStartDate();
        this.endDate = requestDto.getEndDate();
        this.state = true;
        this.point = requestDto.getPoint();
        this.filename = requestDto.getFilename();
        this.existFile = requestDto.isExistFile();
    }

    public Survey() {

    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void updateSurvey(SurveyUpdateDto updateDto) {
        this.name = updateDto.getName();
        this.description = updateDto.getDescription();
        this.point = updateDto.getPoint();
        this.state = updateDto.isState();
        this.startDate = updateDto.getStartDate();
        this.endDate = updateDto.getEndDate();
    }

    public void updateCount(){
        this.responseCount ++;
    }
}
