package com.example.thinker.service;

import com.example.thinker.domain.Choice;
import com.example.thinker.domain.ChoiceForm;
import com.example.thinker.domain.Image;
import com.example.thinker.domain.Member;
import com.example.thinker.domain.MultipleChoiceForm;
import com.example.thinker.domain.Subjective;
import com.example.thinker.domain.SubjectiveForm;
import com.example.thinker.domain.Survey;
import com.example.thinker.dto.ItemDto;
import com.example.thinker.dto.MultipleChoiceDto;
import com.example.thinker.dto.MultipleChoiceRequestDto;
import com.example.thinker.dto.SubjectiveDto;
import com.example.thinker.dto.SubjectiveRequestDto;
import com.example.thinker.dto.SurveyDto;
import com.example.thinker.dto.request.SurveyMakeRequest;
import com.example.thinker.dto.response.SurveyDataResponse;
import com.example.thinker.repository.ChoiceFormRepository;
import com.example.thinker.repository.ChoiceRepository;
import com.example.thinker.repository.ImageRepository;
import com.example.thinker.repository.MultipleChoiceFormRepository;
import com.example.thinker.repository.SubjectiveFormRepository;
import com.example.thinker.repository.SubjectiveRepository;
import com.example.thinker.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {
    private final SurveyRepository surveyRepository;
    private final SubjectiveFormRepository subjectiveFormRepository;
    private final MultipleChoiceFormRepository multipleChoiceFormRepository;
    private final ChoiceFormRepository choiceFormRepository;
    private final ChoiceRepository choiceRepository;
    private final SubjectiveRepository subjectiveRepository;
    private final ImageRepository imageRepository;

    @Override
    public Long postSubjective(Member loginMember, Long subjectiveFormId, String answer) {
        //해당 설문조사에 답변내용이 존재하는지 검증하는 로직이 필요함.
        Optional<SubjectiveForm> subjectiveForm = subjectiveFormRepository.findById(subjectiveFormId);
        if (subjectiveForm.isPresent()) {
            Subjective subjective = new Subjective();
            subjective.setSubjectiveForm(subjectiveForm.get());
            subjective.setParticipant(loginMember);
            subjective.setQuestion(subjectiveForm.get().getQuestion());
            subjective.setAnswer(answer);
            subjectiveRepository.save(subjective);
            return subjectiveForm.get().getSurvey().getId();
        }
        throw new IllegalArgumentException("존재하지 않는 주관식 항목입니다.");
    }

    @Override
    public Long postMultiple(Member loginMember, Long multipleChoiceId, Long itemId, boolean isCheck) {
        //해당 설문조사에 답변내용이 존재하는지 검증하는 로직이 필요함.
        Optional<ChoiceForm> choiceForm = choiceFormRepository.findById(itemId);
        if (choiceForm.isPresent()) {
            Choice choice = new Choice();
            choice.setChoiceForm(choiceForm.get());
            choice.setParticipant(loginMember);
            choice.setQuestion(choiceForm.get().getQuestion());
            choice.setCheck(isCheck);
            choiceRepository.save(choice);
            Optional<MultipleChoiceForm> multipleChoiceForm = multipleChoiceFormRepository.findById(multipleChoiceId);
            if (multipleChoiceForm.isPresent()) {
                return multipleChoiceForm.get().getSurvey().getId();
            }
            throw new IllegalArgumentException("존재하지 않는 객관식 항목입니다.");
        }
        throw new IllegalArgumentException("존재하지 않는 선택지입니다.");
    }

    @Override
    public void makeSurveyBySurveyMakeRequest(Member loginMember, SurveyMakeRequest surveyMakeRequest) throws IOException {
        Survey survey = new Survey();
        survey.setWriter(loginMember);
        survey.setTitle(surveyMakeRequest.title());
        survey.setPremium(surveyMakeRequest.isPremium());

        Image image = new Image();
        image.setData(surveyMakeRequest.thumbnail().getBytes());
        image.setFileName(getFileName(survey, image));
        imageRepository.save(image);
        survey.setImage(image);

        List<MultipleChoiceForm> multipleChoiceForms = new ArrayList<>();//객관식 항목 list
        List<SubjectiveForm> subjectiveForms = new ArrayList<>();//주관식 항목 list

        for (MultipleChoiceRequestDto multipleChoiceRequestDto : surveyMakeRequest.surveyRequestDtos().multipleChoiceRequestDtos()) {//객관식 항목
            MultipleChoiceForm multipleChoiceForm = new MultipleChoiceForm();
            multipleChoiceForm.setQuestion(multipleChoiceRequestDto.question());

            List<ChoiceForm> choiceForms = new ArrayList<>();
            for (String item : multipleChoiceRequestDto.items()) {
                ChoiceForm choiceForm = new ChoiceForm();
                choiceForm.setQuestion(item);
                choiceForm.setMultipleChoiceForm(multipleChoiceForm);
                choiceFormRepository.save(choiceForm);
                choiceForms.add(choiceForm);
            }
            multipleChoiceForm.setItems(choiceForms);
            multipleChoiceForm.setSurvey(survey);
            multipleChoiceFormRepository.save(multipleChoiceForm);
            multipleChoiceForms.add(multipleChoiceForm);
        }

        for (SubjectiveRequestDto subjectiveRequestDto : surveyMakeRequest.surveyRequestDtos().subjectiveRequestDtos()) {//주관식 항목
            SubjectiveForm subjectiveForm = new SubjectiveForm();
            subjectiveForm.setSurvey(survey);
            subjectiveForm.setQuestion(subjectiveRequestDto.questions());
            subjectiveFormRepository.save(subjectiveForm);
            subjectiveForms.add(subjectiveForm);
        }
        survey.setMultipleChoiceForms(multipleChoiceForms);
        survey.setSubjectiveForms(subjectiveForms);
        surveyRepository.save(survey);
    }

    @Override
    public SurveyDataResponse getSurvey(Member loginMember, Long surveyId) {
        boolean isDone = true;
        boolean isManager = isManager(loginMember);
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        if (survey.isPresent()) {

            List<MultipleChoiceDto> multipleChoiceDtos = new ArrayList<>();
            for (MultipleChoiceForm multipleChoiceForm : survey.get().getMultipleChoiceForms()) {
                List<ItemDto> itemDtos = new ArrayList<>();
                ItemDto itemDto;
                for (ChoiceForm choiceForm : multipleChoiceForm.getItems()) {
                    Choice choice = choiceRepository
                            .findByChoiceForm_IdAndParticipant_Id(choiceForm.getId(), loginMember.getId());
                    if (choice == null) {
                        isDone = false;
                        itemDto = ItemDto.form(choiceForm, false);
                    } else {
                        itemDto = ItemDto.form(choiceForm, choice.isCheck());
                    }
                    itemDtos.add(itemDto);
                }
                MultipleChoiceDto multipleChoiceDto = MultipleChoiceDto.form(multipleChoiceForm, itemDtos);
                multipleChoiceDtos.add(multipleChoiceDto);
            }//객관식 완성

            List<SubjectiveDto> subjectiveDtos = new ArrayList<>();
            SubjectiveDto subjectiveDto;
            for (SubjectiveForm subjectiveForm : survey.get().getSubjectiveForms()) {
                Subjective subjective = subjectiveRepository
                        .findBySubjectiveForm_IdAndParticipant_Id(subjectiveForm.getId(), loginMember.getId());
                if (subjective == null) {
                    isDone = false;
                    subjectiveDto = SubjectiveDto.form(subjectiveForm, null);
                } else {
                    subjectiveDto = SubjectiveDto.form(subjectiveForm, subjective.getAnswer());
                }
                subjectiveDtos.add(subjectiveDto);
            }//주관식 완성

            SurveyDto surveyDto = new SurveyDto(surveyId, multipleChoiceDtos, subjectiveDtos);
            return new SurveyDataResponse(isDone, isManager, surveyDto);
        }
        throw new IllegalArgumentException("존재하지 않는 설문조사입니다.");
    }

    @Override
    public void editSurvey(Member loginMember, Long surveyId, boolean isPremium) {
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        if (survey.isPresent()) {
            if (survey.get().getWriter().equals(loginMember)) {
                survey.get().setPremium(isPremium);
                surveyRepository.save(survey.get());
            }
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        throw new IllegalArgumentException("존재하지 않는 설문조사입니다.");
    }

    @Override
    public void deleteSurvey(Member loginMember, Long surveyId) {
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        if (survey.isPresent()) {
            if (survey.get().getWriter().equals(loginMember)) {
                surveyRepository.delete(survey.get());
                //설문조사에 딸린 모든 도메인 정보를 삭제해야한다....
            }
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        throw new IllegalArgumentException("존재하지 않는 설문조사입니다.");
    }

    private boolean isManager(Member member) {
        return member.getGrade().equals("MANAGER");
    }

    private static String getFileName(Survey survey, Image image) {
        return "survey" + "[" + survey.getId() + "]" + "[" + image.getId() + "]";
    }
}
