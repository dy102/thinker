package com.example.thinker.service;

import com.example.thinker.domain.Choice;
import com.example.thinker.domain.ChoiceForm;
import com.example.thinker.domain.Image;
import com.example.thinker.domain.Member;
import com.example.thinker.domain.MultipleChoiceForm;
import com.example.thinker.domain.Point;
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
import com.example.thinker.repository.PointRepository;
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

import static com.example.thinker.constants.ErrorConst.LAST_PAGE;
import static com.example.thinker.constants.ErrorConst.NOT_PARTICIPATE;
import static com.example.thinker.constants.ErrorConst.NO_CHOICE_FORM;
import static com.example.thinker.constants.ErrorConst.NO_MEMBER;
import static com.example.thinker.constants.ErrorConst.NO_MULTIPLE_CHOICE_FORM;
import static com.example.thinker.constants.ErrorConst.NO_ORDER;
import static com.example.thinker.constants.ErrorConst.NO_PERMISSION;
import static com.example.thinker.constants.ErrorConst.NO_SUBJECTIVE_FORM;
import static com.example.thinker.constants.ErrorConst.NO_SURVEY;
import static com.example.thinker.constants.PointConst.CREATE_PREMIUM_SURVEY;
import static com.example.thinker.constants.PointConst.MULTIPLY_TO_SURVEY_SIZE;
import static com.example.thinker.constants.PointConst.NO_POINT;
import static com.example.thinker.constants.PointConst.POINT_MAX_BOUND_IN_SURVEY;
import static com.example.thinker.constants.PointConst.PREMIUM_SURVEY_BONUS;
import static com.example.thinker.constants.PointConst.PREMIUM_SURVEY_COST;
import static com.example.thinker.constants.ServiceConst.MAX_POST_COUNT;
import static com.example.thinker.constants.ServiceConst.MAX_PREMIUM_PAGE;
import static com.example.thinker.constants.ServiceConst.ORDER_BY_POPULAR;
import static com.example.thinker.constants.ServiceConst.ORDER_BY_RECENT;
import static com.example.thinker.domain.Grade.MANAGER;

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
    private final PointRepository pointRepository;

    private final MemberService memberService;

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
        throw new IllegalArgumentException(NO_SUBJECTIVE_FORM);
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
            throw new IllegalArgumentException(NO_MULTIPLE_CHOICE_FORM);
        }
        throw new IllegalArgumentException(NO_CHOICE_FORM);
    }

    @Override
    public void makeSurveyBySurveyMakeRequest(Member loginMember,
                                              SurveyMakeRequest surveyMakeRequest,
                                              MultipartFile multipartFile) throws IOException {
        Survey survey = new Survey();
        survey.setWriter(loginMember);
        survey.setTitle(surveyMakeRequest.title());

        if (surveyMakeRequest.isPremium()) {
            if (loginMember.getPoint() >= PREMIUM_SURVEY_COST) {
                Point point = new Point();
                point.setMember(loginMember);
                point.setExplanation("프리미엄 설문조사 생성");
                point.setAmount(-PREMIUM_SURVEY_COST);
                pointRepository.save(point);

                loginMember.setPoint(loginMember.getPoint() - PREMIUM_SURVEY_COST);
                memberRepository.save(loginMember);

                survey.setIsPremium(surveyMakeRequest.isPremium());
            } else {
                throw new IllegalArgumentException("포인트가 부족합니다.");
            }
        }

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

        Image image = imageRepository.findByData(multipartFile.getBytes());
        if (image == null && !multipartFile.isEmpty()) {
            image = new Image();
            image.setData(multipartFile.getBytes());
            image.setFileName(getFileName(survey, image));
            imageRepository.save(image);
        }
        survey.setImage(image);
        surveyRepository.save(survey);


    }

    @Override
    public void participateSurvey(Member loginMember, Long surveyId) {
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        if (survey.isPresent()) {
            for (MultipleChoiceForm multipleChoiceForm : survey.get().getMultipleChoiceForms()) {
                for (ChoiceForm choiceForm : multipleChoiceForm.getItems()) {
                    if (choiceRepository
                            .findByChoiceForm_IdAndParticipant_Id(choiceForm.getId(), loginMember.getId())
                            == null) {
                        throw new IllegalArgumentException(NOT_PARTICIPATE);
                    }
                }
            }
            for (SubjectiveForm subjectiveForm : survey.get().getSubjectiveForms()) {
                if (subjectiveRepository
                        .findBySubjectiveForm_IdAndParticipant_Id(subjectiveForm.getId(), loginMember.getId())
                        == null) {
                    throw new IllegalArgumentException(NOT_PARTICIPATE);
                }
            }
            //설문조사 참여 확인절차 완료

            //포인트 추가
            int sizePoint = (survey.get().getSubjectiveForms().size()
                    + survey.get().getMultipleChoiceForms().size()) * MULTIPLY_TO_SURVEY_SIZE;
            if (sizePoint > POINT_MAX_BOUND_IN_SURVEY) {
                sizePoint = POINT_MAX_BOUND_IN_SURVEY;
            }
            if (survey.get().getIsPremium()) {
                Long amount = sizePoint + PREMIUM_SURVEY_BONUS;
                Point point = new Point();
                point.setMember(loginMember);
                point.setExplanation("설문조사 참여(프리미엄)");
                point.setAmount(amount);
                pointRepository.save(point);

                loginMember.setPoint(loginMember.getPoint() + amount);
                loginMember.setAccumulatedPoint(loginMember.getAccumulatedPoint() + amount);
                memberRepository.save(loginMember);
            } else {
                Long amount = (long) sizePoint;
                Point point = new Point();
                point.setMember(loginMember);
                point.setExplanation("댓글 작성");
                point.setAmount(amount);
                pointRepository.save(point);

                loginMember.setPoint(loginMember.getPoint() + amount);
                loginMember.setAccumulatedPoint(loginMember.getAccumulatedPoint() + amount);
                memberRepository.save(loginMember);
            }
            memberService.setGradeByAccumulatedPoint(loginMember);

            survey.get().setParticipants(survey.get().getParticipants() + 1);
            surveyRepository.save(survey.get());
            return;
        }
        throw new IllegalArgumentException(NO_SURVEY);
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
        throw new IllegalArgumentException(NO_SURVEY);
    }

    @Override
    public void editSurvey(Member loginMember, Long surveyId, boolean isPremium) {
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        if (survey.isPresent()) {
            if (survey.get().getWriter().equals(loginMember)) {
                if (isPremium) {
                    if (loginMember.getPoint() >= PREMIUM_SURVEY_COST) {
                        Point point = new Point();
                        point.setMember(loginMember);
                        point.setExplanation(CREATE_PREMIUM_SURVEY);
                        point.setAmount(-PREMIUM_SURVEY_COST);
                        pointRepository.save(point);

                        loginMember.setPoint(loginMember.getPoint() - PREMIUM_SURVEY_COST);
                        memberRepository.save(loginMember);

                        survey.get().setIsPremium(isPremium);
                    } else {
                        throw new IllegalArgumentException(NO_POINT);
                    }
                }
                if (isPremium && !survey.get().getIsPremium()) {
                    surveyRepository.delete(survey.get());//최신 프리미엄으로 등록하기 위한 과정
                }
                survey.get().setIsPremium(isPremium);
                surveyRepository.save(survey.get());
            }
            throw new IllegalArgumentException(NO_PERMISSION);
        }
        throw new IllegalArgumentException(NO_SURVEY);
    }

    @Override
    public void deleteSurvey(Member loginMember, Long surveyId) {
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        if (survey.isPresent()) {
            if (survey.get().getWriter().getId().equals(loginMember.getId())) {

                for (SubjectiveForm subjectiveForm : survey.get().getSubjectiveForms()) {

                    List<Subjective> subjectivesByParticipants
                            = subjectiveRepository.findAllBySubjectiveForm_Id(subjectiveForm.getId());
                    subjectiveRepository.deleteAll(subjectivesByParticipants);
                    subjectiveFormRepository.delete(subjectiveForm);
                }

                for (MultipleChoiceForm multipleChoiceForm : survey.get().getMultipleChoiceForms()) {

                    for (ChoiceForm choiceForm : multipleChoiceForm.getItems()) {

                        List<Choice> choicesByParticipants = choiceRepository.findAllByChoiceForm_Id(choiceForm.getId());
                        choiceRepository.deleteAll(choicesByParticipants);
                        choiceFormRepository.delete(choiceForm);
                    }
                    multipleChoiceFormRepository.delete(multipleChoiceForm);
                }

                surveyRepository.delete(survey.get());
                return;
                //설문조사에 딸린 모든 도메인 정보를 삭제해야한다.....//참여한 내용도 전부 삭제해야하는데, 함께 삭제해야할까?
                //그러면 3중 for문을 사용해야하는데, 시간이 너무 오래걸리는 건 아닐까?
            }
            throw new IllegalArgumentException(NO_PERMISSION);
        }
        throw new IllegalArgumentException(NO_SURVEY);
    }

    @Override
    public PremiumSurveysResponse getPremiumSurveys(Member loginMember, int page, int size) {
        if (page > MAX_PREMIUM_PAGE) {
            throw new IllegalArgumentException(LAST_PAGE);
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Survey> surveys = surveyRepository.findByIsPremiumIsTrueOrderByIdDesc(pageRequest);
        List<SurveyDto> surveyDtos = getSurveyDtos(loginMember, surveys.getContent());
        return new PremiumSurveysResponse(surveyDtos.size(), surveyDtos);
    }

    @Override
    public SurveysResponse getSurveys(Member loginMember, String kind, int size, Long lastId) {
        PageRequest pageRequest = PageRequest.of(0, size + 1);
        List<Survey> surveys;
        if (kind.equals(ORDER_BY_RECENT)) {
            if (lastId == null) {
                Page<Survey> page = surveyRepository.findAllByOrderByIdDesc(pageRequest);
                surveys = page.getContent();
            } else {
                Page<Survey> page = surveyRepository.findAllByIdLessThanOrderByIdDesc(lastId, pageRequest);
                surveys = page.getContent();
            }
        } else if (kind.equals(ORDER_BY_POPULAR)) {
            surveys = surveyRepository.find100ByPopular();
            size = MAX_POST_COUNT;//100개를 한꺼번에 보낸다.
        } else {
            throw new IllegalArgumentException(NO_ORDER);
        }
        List<SurveyDto> surveyDtos = getSurveyDtos(loginMember, surveys);//isDone 때문에 미리 Dto로 변환 필요
        ScrollPaginationCollection<SurveyDto> cursor = new ScrollPaginationCollection<>(surveyDtos, size);
        return SurveysResponse.form(cursor, surveys.size());
    }

    @Override
    public SurveyDtos getMySurveys(Member loginMember, String kind, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<SurveyDto> surveyDtos;
        if (kind.equals(ORDER_BY_RECENT)) {
            List<Survey> surveys = surveyRepository.findAllByWriterOrderByIdDesc(loginMember, pageRequest).getContent();
            surveyDtos = getSurveyDtos(loginMember, surveys);
        } else if (kind.equals(ORDER_BY_POPULAR)) {
            List<Survey> surveys = surveyRepository.findAllByWriterOrderByParticipantsDesc(loginMember, pageRequest).getContent();
            surveyDtos = getSurveyDtos(loginMember, surveys);
        } else {
            throw new IllegalArgumentException(NO_ORDER);
        }
        return new SurveyDtos(surveyDtos);
    }

    @Override
    public SurveyDtos getSurveyParticipatedIn(Member loginMember, String kind, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<SurveyDto> surveyDtos;
        List<Survey> surveys = new ArrayList<>();
        if (kind.equals(ORDER_BY_RECENT)) {
            Page<Subjective> subjectives = subjectiveRepository.findAllByParticipantByRecent(loginMember, pageRequest);
            for (Subjective subjective : subjectives) {
                surveys.add(subjective.getSubjectiveForm().getSurvey());
            }
            surveys = surveys.stream().distinct().toList();
            surveyDtos = getSurveyDtos(loginMember, surveys);
        } else if (kind.equals(ORDER_BY_POPULAR)) {
            Page<Subjective> subjectives = subjectiveRepository.findAllByParticipantByPopular(loginMember, pageRequest);
            for (Subjective subjective : subjectives) {
                surveys.add(subjective.getSubjectiveForm().getSurvey());
            }
            surveys = surveys.stream().distinct().toList();
            surveyDtos = getSurveyDtos(loginMember, surveys);
        } else {
            throw new IllegalArgumentException(NO_ORDER);
        }
        return new SurveyDtos(surveyDtos);
    }

    @Override
    public SurveysResponse searchSurveysByTitle(Member loginMember, String kind, String title, Long lastId, int size) {
        PageRequest pageRequest = PageRequest.of(0, size + 1);
        List<Survey> surveys;
        if (kind.equals(ORDER_BY_RECENT)) {
            if (lastId == null) {
                surveys = surveyRepository
                        .findAllByTitleContainingIgnoreCaseOrderByIdDesc(title, pageRequest).getContent();
            } else {
                surveys = surveyRepository
                        .findAllByTitleContainingIgnoreCaseAndIdLessThanOrderByIdDesc(title, lastId, pageRequest).getContent();
            }
        } else if (kind.equals(ORDER_BY_POPULAR)) {
            surveys = surveyRepository.search100ByTitleAndPopular();
        } else {
            throw new IllegalArgumentException(NO_ORDER);
        }
        List<SurveyDto> surveyDtos = getSurveyDtos(loginMember, surveys);//isDone 때문에 미리 Dto로 변환 필요
        ScrollPaginationCollection<SurveyDto> cursor = new ScrollPaginationCollection<>(surveyDtos, size);
        return SurveysResponse.form(cursor, surveys.size());
    }

    @Override
    public SurveysResponse searchSurveysByWriter(Member loginMember, String kind, String name, Long lastId, int size) {
        PageRequest pageRequest = PageRequest.of(0, size + 1);
        List<Survey> surveys;
        if (kind.equals(ORDER_BY_RECENT)) {
            if (lastId == null) {
                surveys = surveyRepository
                        .findAllByWriter_NameContainingIgnoreCaseAndIdLessThanOrderByIdDesc(name, lastId, pageRequest).getContent();
            } else {
                surveys = surveyRepository
                        .findAllByWriter_NameContainingIgnoreCaseOrderByIdDesc(name, pageRequest).getContent();
            }

        } else if (kind.equals(ORDER_BY_POPULAR)) {
            surveys = surveyRepository.search100ByNameAndPopular();
        } else {
            throw new IllegalArgumentException(NO_ORDER);
        }
        List<SurveyDto> surveyDtos = getSurveyDtos(loginMember, surveys);//isDone 때문에 미리 Dto로 변환 필요
        ScrollPaginationCollection<SurveyDto> cursor = new ScrollPaginationCollection<>(surveyDtos, size);
        return SurveysResponse.form(cursor, surveys.size());
    }

    @Override
    public SurveyDtos getSurveysByMember(Member loginMember, Long memberId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<SurveyDto> surveyDtos;
        if (loginMember.getGrade().equals(MANAGER.getName())) {
            Optional<Member> member = memberRepository.findById(memberId);
            if (member.isPresent()) {
                List<Survey> surveys = surveyRepository.findAllByWriterOrderByIdDesc(member.get(), pageRequest).getContent();
                surveyDtos = getSurveyDtos(loginMember, surveys);
            } else {
                throw new IllegalArgumentException(NO_MEMBER);
            }
        } else {
            throw new IllegalArgumentException(NO_PERMISSION);
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
        return member.getGrade().equals(MANAGER.getName());
    }

    private static String getFileName(Survey survey, Image image) {
        return "survey" + "[" + survey.getId() + "]" + "[" + image.getId() + "]";
    }
}
