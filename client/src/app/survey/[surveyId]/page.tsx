"use client";

import { useGetSurveyQuery } from "@/api/survey-api";
import { mainColor } from "@/components/Themes/color";
import { ISurvey } from "@/components/types/dto";
import { Modal, Stack } from "@mui/material";
import React, { useEffect, useState } from "react";
import Button from "@/components/Button/Button";
import PageLink from "@/components/PageLink/PageLink";
import MultipleChoice from "@/components/SurveyItems/MultipleChoice/MultipleChoice";
import SubjectiveDone from "@/components/SurveyItems/Subjective/SubjectiveDone";
import SubjectiveNotYet from "@/components/SurveyItems/Subjective/SubjectiveNotYet";
import { ModalContent } from "@/components/Modal/Modal.style";

function Page({ params }: { params: { surveyId: number } }) {
  const getSurveys = {
    isDone: false,
    isOwner: false,
    isManager: false,
    surveyDetailDto: {
      surveyId: 1,
      surveyTitle: "THINKER 사용 만족도 조사",
      multipleChoiceDtos: [
        {
          multipleChoiceId: 100,
          question: "thinking은 괜찮은가?",
          items: [
            {
              itemId: 1001,
              item: "very good",
              isCheck: false,
            },
            {
              itemId: 1002,
              item: "good",
              isCheck: true,
            },
            {
              itemId: 1003,
              item: "bad",
              isCheck: false,
            },
          ],
        },
        {
          multipleChoiceId: 101,
          question: "survey는 괜찮은가?",
          items: [
            {
              itemId: 1011,
              item: "very good",
              isCheck: true,
            },
            {
              itemId: 1012,
              item: "good",
              isCheck: false,
            },
            {
              itemId: 1013,
              item: "bad",
              isCheck: false,
            },
            {
              itemId: 1014,
              item: "very bad",
              isCheck: false,
            },
          ],
        },
      ],
      subjectiveDtos: [
        {
          subjectiveFormId: 0,
          question: "THINKER는 왜 괜찮은가?",
          answer: "왜냐하면 좋기 때문이다.",
        },
        {
          subjectiveFormId: 0,
          question: "THINKER에게 하고싶은 말 또는 개선사항이 있다면 적어주세요",
          answer: "ui를 조금 더 발전시켜보자",
        },
      ],
    },
  };

  const [ConfirmOpen, setConfirmOpen] = useState<boolean>(false);

  //get main survey contents
  const { data: surveyData } = useGetSurveyQuery({
    surveyId: params.surveyId,
  });
  const [survey, setSurvey] = useState<ISurvey>();
  useEffect(() => {
    setSurvey(surveyData);
  }, [surveyData]);
  return (
    <Stack margin={"0 auto"} maxWidth={"1024px"}>
      <Stack
        fontSize={"70px"}
        color={`${mainColor}`}
        textAlign={"center"}
        margin={"100px 0 100px 0"}
      >
        {getSurveys.surveyDetailDto.surveyTitle}
      </Stack>
      {getSurveys.surveyDetailDto.multipleChoiceDtos.map((multi) => {
        return (
          <MultipleChoice
            multipleChoiceId={multi.multipleChoiceId}
            question={multi.question}
            items={multi.items}
            isDone={getSurveys.isDone}
          />
        );
      })}
      {getSurveys.isDone ? (
        <Stack>
          {getSurveys.surveyDetailDto.subjectiveDtos.map((subj) => {
            return (
              <SubjectiveDone
                subjectiveFormId={subj.subjectiveFormId}
                question={subj.question}
                answer={subj.answer}
              />
            );
          })}
        </Stack>
      ) : (
        <Stack>
          {getSurveys.surveyDetailDto.subjectiveDtos.map((subj) => {
            return (
              <SubjectiveNotYet
                subjectiveFormId={subj.subjectiveFormId}
                question={subj.question}
              />
            );
          })}
        </Stack>
      )}

      {getSurveys.isDone ? (
        <></>
      ) : (
        <Stack display="flex" alignItems={"flex-end"}>
          <Button onClick={() => setConfirmOpen(true)} sx={{ padding: "10px 20px" }}>
            {"제출하기 >>>"}
          </Button>
        </Stack>
      )}

      {/* 재확인 모달 */}
      <Modal open={ConfirmOpen} onClose={() => setConfirmOpen(false)}>
        <ModalContent>
          <Stack>
            설문조사를 제출하시겠습니까?
          </Stack>
          <Stack>
            제출 후 수정할 수 없습니다.
          </Stack>

        </ModalContent>

      </Modal>
    </Stack>
  );
}
export default Page;
