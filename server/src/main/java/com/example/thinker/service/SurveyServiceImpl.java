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
import com.example.thinker.dto.SurveyDetailDto;
import com.example.thinker.dto.SurveyDto;
import com.example.thinker.dto.SurveyDtos;
import com.example.thinker.dto.request.SurveyMakeRequest;
import com.example.thinker.dto.response.PremiumSurveysResponse;
import com.example.thinker.dto.response.SurveyDataResponse;
import com.example.thinker.dto.response.SurveysResponse;
import com.example.thinker.repository.ChoiceFormRepository;
import com.example.thinker.repository.ChoiceRepository;
import com.example.thinker.repository.ImageRepository;
import com.example.thinker.repository.MemberRepository;
import com.example.thinker.repository.MultipleChoiceFormRepository;
import com.example.thinker.repository.SubjectiveFormRepository;
import com.example.thinker.repository.SubjectiveRepository;
import com.example.thinker.repository.SurveyRepository;
import com.example.thinker.util.ScrollPaginationCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private final MemberRepository memberRepository;

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
            Optional<MultipleChoiceForm> multipleChoiceForm = multipleChoiceFormRepository.findById(multipleChoiceId);
            if (multipleChoiceForm.isPresent()) {
                Choice choice = new Choice();
                choice.setChoiceForm(choiceForm.get());
                choice.setParticipant(loginMember);
                choice.setCheck(isCheck);
                choiceRepository.save(choice);
                return multipleChoiceForm.get().getSurvey().getId();
            }
            throw new IllegalArgumentException("존재하지 않는 객관식 항목입니다.");
        }
        throw new IllegalArgumentException("존재하지 않는 선택지입니다.");
    }

    @Override
    public void makeSurveyBySurveyMakeRequest(Member loginMember,
                                              SurveyMakeRequest surveyMakeRequest,
                                              MultipartFile multipartFile) throws IOException {
        Survey survey = new Survey();
        survey.setWriter(loginMember);
        survey.setTitle(surveyMakeRequest.title());
        survey.setIsPremium(surveyMakeRequest.isPremium());

        Image image = new Image();
        image.setData(multipartFile.getBytes());
        image.setFileName(getFileName(survey, image));
        imageRepository.save(image);
        survey.setImage(image);

        List<MultipleChoiceForm> multipleChoiceForms = new ArrayList<>();//객관식 항목 list
        List<SubjectiveForm> subjectiveForms = new ArrayList<>();//주관식 항목 list

        for (MultipleChoiceRequestDto multipleChoiceRequestDto : surveyMakeRequest.surveyRequestDtos().multipleChoiceRequestDtos()) {//객관식 항목
            MultipleChoiceForm multipleChoiceForm = new MultipleChoiceForm();
            multipleChoiceForm.setQuestion(multipleChoiceRequestDto.question());

            surveyRepository.save(survey);
            multipleChoiceForm.setSurvey(survey);
            multipleChoiceFormRepository.save(multipleChoiceForm);

            List<ChoiceForm> choiceForms = new ArrayList<>();
            for (String item : multipleChoiceRequestDto.items()) {
                ChoiceForm choiceForm = new ChoiceForm();
                choiceForm.setQuestion(item);
                choiceForm.setMultipleChoiceForm(multipleChoiceForm);
                choiceFormRepository.save(choiceForm);
                choiceForms.add(choiceForm);
            }
            multipleChoiceForm.setItems(choiceForms);
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
        boolean isOwner = false;
        boolean isManager = isManager(loginMember);
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        if (survey.isPresent()) {

            List<MultipleChoiceDto> multipleChoiceDtos = new ArrayList<>();
            for (MultipleChoiceForm multipleChoiceForm : survey.get().getMultipleChoiceForms()) {
                List<ItemDto> itemDtos = new ArrayList<>();
                ItemDto itemDto;
                List<Choice> choices = new ArrayList<>();
                for (ChoiceForm choiceForm : multipleChoiceForm.getItems()) {
                    choices.add(choiceRepository
                            .findByChoiceForm_IdAndParticipant_Id(choiceForm.getId(), loginMember.getId()));
                }
                if (choices.isEmpty()) {
                    isDone = false;
                }
                for (Choice choice : choices) {
                    itemDto = ItemDto.form(choice.getChoiceForm(), choice.isCheck());
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
            if (loginMember.getId().equals(survey.get().getWriter().getId())) {
                isOwner = true;
            }
            SurveyDetailDto surveyDetailDto = new SurveyDetailDto(surveyId, survey.get().getTitle(), multipleChoiceDtos, subjectiveDtos);
            return new SurveyDataResponse(isDone, isOwner, isManager, surveyDetailDto);
        }
        throw new IllegalArgumentException("존재하지 않는 설문조사입니다.");
    }

    @Override
    public void editSurvey(Member loginMember, Long surveyId, boolean isPremium) {
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        if (survey.isPresent()) {
            if (survey.get().getWriter().equals(loginMember)) {
                if (isPremium && !survey.get().getIsPremium()) {
                    surveyRepository.delete(survey.get());//최신 프리미엄으로 등록하기 위한 과정
                }
                survey.get().setIsPremium(isPremium);
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
                for (MultipleChoiceForm multipleChoiceForm : survey.get().getMultipleChoiceForms()) {
                    multipleChoiceFormRepository.delete(multipleChoiceForm);
                    for (ChoiceForm choiceForm : multipleChoiceForm.getItems()) {
                        choiceFormRepository.delete(choiceForm);
                        List<Choice> choicesByParticipants = choiceRepository.findAllByChoiceForm_Id(choiceForm.getId());
                        for (Choice choice : choicesByParticipants) {
                            choiceRepository.delete(choice);
                        }
                    }
                }
                for (SubjectiveForm subjectiveForm : survey.get().getSubjectiveForms()) {
                    subjectiveFormRepository.delete(subjectiveForm);
                    List<Subjective> subjectivesByParticipants
                            = subjectiveRepository.findAllBySubjectiveForm_Id(subjectiveForm.getId());
                    for (Subjective subjective : subjectivesByParticipants) {
                        subjectiveRepository.delete(subjective);
                    }
                }
                //설문조사에 딸린 모든 도메인 정보를 삭제해야한다.....//참여한 내용도 전부 삭제해야하는데, 함께 삭제해야할까?
                //그러면 3중 for문을 사용해야하는데, 시간이 너무 오래걸리는 건 아닐까?
            }
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        throw new IllegalArgumentException("존재하지 않는 설문조사입니다.");
    }

    @Override
    public PremiumSurveysResponse getPremiumSurveys(Member loginMember, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Survey> surveys = surveyRepository.findByIsPremiumIsTrueOrderByIdDesc(pageRequest);
        List<SurveyDto> surveyDtos = getSurveyDtos(loginMember, surveys.getContent());
        return new PremiumSurveysResponse(surveyDtos.size(), surveyDtos);
    }

    @Override
    public SurveysResponse getSurveys(Member loginMember, String kind, int size, Long lastId) {
        PageRequest pageRequest = PageRequest.of(0, size + 1);
        List<Survey> surveys;
        if (kind.equals("recent")) {
            Page<Survey> page = surveyRepository.findAllByIdLessThanOrderByIdDesc(lastId, pageRequest);
            surveys = page.getContent();
        } else if (kind.equals("popular")) {
            surveys = surveyRepository.find100ByPopular();
            size = 100;//100개를 한꺼번에 보낸다.
        } else {
            throw new IllegalArgumentException("잘못된 정렬 기준입니다.");
        }
        List<SurveyDto> surveyDtos = getSurveyDtos(loginMember, surveys);//isDone 때문에 미리 Dto로 변환 필요
        ScrollPaginationCollection<SurveyDto> cursor = new ScrollPaginationCollection<>(surveyDtos, size);
        return SurveysResponse.form(cursor, surveys.size());
    }

    @Override
    public SurveyDtos getMySurveys(Member loginMember, String kind, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<SurveyDto> surveyDtos;
        if (kind.equals("recent")) {
            List<Survey> surveys = surveyRepository.findAllByWriterOrderByIdDesc(loginMember, pageRequest).getContent();
            surveyDtos = getSurveyDtos(loginMember, surveys);
        } else if (kind.equals("popular")) {
            List<Survey> surveys = surveyRepository.findAllByWriterOrderByParticipantsDesc(loginMember, pageRequest).getContent();
            surveyDtos = getSurveyDtos(loginMember, surveys);
        } else {
            throw new IllegalArgumentException("잘못된 정렬 기준입니다.");
        }
        return new SurveyDtos(surveyDtos);
    }

    @Override
    public SurveyDtos getSurveyParticipatedIn(Member loginMember, String kind, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<SurveyDto> surveyDtos;
        List<Survey> surveys = new ArrayList<>();
        if (kind.equals("recent")) {
            Page<Subjective> subjectives = subjectiveRepository.findAllByParticipantByRecent(loginMember, pageRequest);
            for (Subjective subjective : subjectives) {
                surveys.add(subjective.getSubjectiveForm().getSurvey());
            }
            surveys = surveys.stream().distinct().toList();
            surveyDtos = getSurveyDtos(loginMember, surveys);
        } else if (kind.equals("popular")) {
            Page<Subjective> subjectives = subjectiveRepository.findAllByParticipantByPopular(loginMember, pageRequest);
            for (Subjective subjective : subjectives) {
                surveys.add(subjective.getSubjectiveForm().getSurvey());
            }
            surveys = surveys.stream().distinct().toList();
            surveyDtos = getSurveyDtos(loginMember, surveys);
        } else {
            throw new IllegalArgumentException("잘못된 정렬 기준입니다.");
        }
        return new SurveyDtos(surveyDtos);
    }

    @Override
    public SurveysResponse searchSurveysByTitle(Member loginMember, String kind, String title, Long lastId, int size) {
        PageRequest pageRequest = PageRequest.of(0, size + 1);
        List<Survey> surveys;
        if (kind.equals("recent")) {
            surveys = surveyRepository
                    .findAllByTitleContainingIgnoreCaseOrderByIdDesc(title, lastId, pageRequest).getContent();
        } else if (kind.equals("popular")) {
            surveys = surveyRepository.search100ByTitleAndPopular();
        } else {
            throw new IllegalArgumentException("잘못된 정렬 기준입니다.");
        }
        List<SurveyDto> surveyDtos = getSurveyDtos(loginMember, surveys);//isDone 때문에 미리 Dto로 변환 필요
        ScrollPaginationCollection<SurveyDto> cursor = new ScrollPaginationCollection<>(surveyDtos, size);
        return SurveysResponse.form(cursor, surveys.size());
    }

    @Override
    public SurveysResponse searchSurveysByWriter(Member loginMember, String kind, String name, Long lastId, int size) {
        PageRequest pageRequest = PageRequest.of(0, size + 1);
        List<Survey> surveys;
        if (kind.equals("recent")) {
            surveys = surveyRepository
                    .findAllByWriter_NameContainingIgnoreCaseOrderByIdDesc(name, lastId, pageRequest).getContent();
        } else if (kind.equals("popular")) {
            surveys = surveyRepository.search100ByNameAndPopular();
        } else {
            throw new IllegalArgumentException("잘못된 정렬 기준입니다.");
        }
        List<SurveyDto> surveyDtos = getSurveyDtos(loginMember, surveys);//isDone 때문에 미리 Dto로 변환 필요
        ScrollPaginationCollection<SurveyDto> cursor = new ScrollPaginationCollection<>(surveyDtos, size);
        return SurveysResponse.form(cursor, surveys.size());
    }

    @Override
    public SurveyDtos getSurveysByMember(Member loginMember, Long memberId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<SurveyDto> surveyDtos;
        if (loginMember.getGrade().equals("MANAGER")) {
            Optional<Member> member = memberRepository.findById(memberId);
            if (member.isPresent()) {
                List<Survey> surveys = surveyRepository.findAllByWriterOrderByIdDesc(member.get(), pageRequest).getContent();
                surveyDtos = getSurveyDtos(loginMember, surveys);
            } else {
                throw new IllegalArgumentException("존재하지 않는 회원입니다.");
            }
        } else {
            throw new IllegalArgumentException("접근 권한이 없습니다.");
        }
        return new SurveyDtos(surveyDtos);
    }

    private List<SurveyDto> getSurveyDtos(Member loginMember, List<Survey> surveys) {
        List<SurveyDto> surveyDtos = new ArrayList<>();
        for (Survey survey : surveys) {
            boolean isDone = true;
            if (loginMember == null ||
                    subjectiveRepository.findBySubjectiveForm_IdAndParticipant_Id(
                            survey.getSubjectiveForms().get(0).getId(), loginMember.getId()) == null
                            && choiceRepository.findByChoiceForm_IdAndParticipant_Id(
                            survey.getMultipleChoiceForms().get(0).getItems().get(0).getId(), loginMember.getId()) == null
            ) {
                isDone = false;
            }
            surveyDtos.add(SurveyDto.form(survey, isDone));
        }
        return surveyDtos;
    }

    private boolean isManager(Member member) {
        return member.getGrade().equals("MANAGER");
    }

    private static String getFileName(Survey survey, Image image) {
        return "survey" + "[" + survey.getId() + "]" + "[" + image.getId() + "]";
    }
}
