package com.example.thinker.service;

import com.example.thinker.domain.Choice;
import com.example.thinker.domain.ChoiceForm;
import com.example.thinker.domain.Image;
import com.example.thinker.domain.Member;
import com.example.thinker.domain.MultipleChoiceForm;
import com.example.thinker.domain.Subjective;
import com.example.thinker.domain.SubjectiveForm;
import com.example.thinker.domain.Survey;
import com.example.thinker.repository.ChoiceFormRepository;
import com.example.thinker.repository.MultipleChoiceFormRepository;
import com.example.thinker.repository.SubjectiveFormRepository;
import com.example.thinker.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {
    private final SurveyRepository surveyRepository;
    private final SubjectiveFormRepository subjectiveFormRepository;
    private final MultipleChoiceFormRepository multipleChoiceFormRepository;
    private final ChoiceFormRepository choiceFormRepository;

    @Override
    public Long postSubjective(Member loginMember, Long subjectiveFormId, String answer) {
        Optional<SubjectiveForm> subjectiveForm = subjectiveFormRepository.findById(subjectiveFormId);
        if (subjectiveForm.isPresent()) {
            Subjective subjective = new Subjective();
            subjective.setSubjectiveForm(subjectiveForm.get());
            subjective.setParticipant(loginMember);
            subjective.setQuestion(subjectiveForm.get().getQuestion());
            subjective.setAnswer(answer);
            return subjectiveForm.get().getSurvey().getId();
        }
        throw new IllegalArgumentException("존재하지 않는 주관식 항목입니다.");
    }

    @Override
    public Long postMultiple(Member loginMember, Long multipleChoiceId, Long itemId, boolean isCheck) {
        Optional<ChoiceForm> choiceForm = choiceFormRepository.findById(itemId);
        if (choiceForm.isPresent()) {
            Choice choice = new Choice();
            choice.setChoiceForm(choiceForm.get());
            choice.setParticipant(loginMember);
            choice.setQuestion(choiceForm.get().getQuestion());
            choice.setCheck(isCheck);

            Optional<MultipleChoiceForm> multipleChoiceForm = multipleChoiceFormRepository.findById(multipleChoiceId);
            if (multipleChoiceForm.isPresent()) {
                return multipleChoiceForm.get().getSurvey().getId();
            }
            throw new IllegalArgumentException("존재하지 않는 객관식 항목입니다.");
        }
        throw new IllegalArgumentException("존재하지 않는 선택지입니다.");
    }

//    @Override
//    public void makeSurveyBySurveyMakeRequest(Member loginMember, SurveyMakeRequest surveyMakeRequest) throws IOException {
//        Survey survey = new Survey();
//        survey.setWriter(loginMember);
//        survey.setTitle(surveyMakeRequest.title());
//
//        Image image = new Image();
//        image.setData(surveyMakeRequest.thumbnail().getBytes());
//        image.setFileName(getFileName(survey, image));
//        survey.setImage(image);
//
//        List<MultipleChoiceForm> multipleChoiceForms = new ArrayList<>();
//        List<ChoiceForm> choiceForms = new ArrayList<>();
//        MultipleChoiceForm multipleChoiceForm = new MultipleChoiceForm();
//
//        ChoiceForm choiceForm = new ChoiceForm();
//        choiceForm.setMultipleChoiceForm(multipleChoiceForm);
//        choiceForm.setQuestion();
//
//        survey.setMultipleChoiceForms();
//        survey.setSubjectiveForms();
//    }

    private static String getFileName(Survey survey, Image image) {
        return "survey" + "[" + survey.getId() + "]" + "[" + image.getId() + "]";
    }
}
