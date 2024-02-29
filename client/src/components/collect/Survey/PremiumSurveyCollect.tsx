import Toast from "@/components/Toast/Toast";
import { IconButton, Stack } from "@mui/material";
import { KeyboardArrowLeft, KeyboardArrowRight } from "@mui/icons-material";
import { useEffect, useState } from "react";
import { ISurveyPremium } from "@/components/types/dto";
import { useGetSurveyPremiumQuery } from "@/api/survey-api";
import PremiumSurvey from "@/components/Premium/PremiumSurvey/PremiumSurvey";

export default function PremiumSurveyCollect() {
  const premiumSurveysResponse = {
    premiumSurveysCount: 2,
    SurveyDtos: [
      {
        surveyId: 1,
        surveyImage:
          "https://assets.paintingco.com/dev-3/wp-content/uploads/20230802110655/shutterstock_1561397020-scaled.jpg",
        surveyWriter: "Jun Seo",
        surveyTitle: "Is Thinker good?",
        surveyItemCount: 5,
        isDone: true,
        isPremium: true,
      },
      {
        surveyId: 2,
        surveyImage:
          "https://t4.ftcdn.net/jpg/00/93/83/93/240_F_93839373_JT5cpdRmTyuOg6eert4Kexq8aO63iD5r.jpg",
        surveyWriter: "Do Yeon",
        surveyTitle: "Is Thinker good? Is Thinker good? Is Thinker good?",
        surveyItemCount: 12,
        isDone: false,
        isPremium: true,
      },
      {
        surveyId: 3,
        surveyImage:
          "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3l2fb-6xT3Krier-wokMNXGNpM-t6qvSghg&usqp=CAU",
        surveyWriter: "Walt Disney",
        surveyTitle: "Disney Plus Subscribe prise",
        surveyItemCount: 12,
        isDone: false,
        isPremium: true,
      },
    ],
  };

  const [page, setPage] = useState(0);
  const [backendSendPage, setBackendSendPage] = useState(1);
  const [toastOpen, setToastOpen] = useState(false);

  const [survyePremium, setSurveyPremium] = useState<ISurveyPremium>();
  const { data: surveyPremiumData } = useGetSurveyPremiumQuery({
    page: backendSendPage,
  });
  const newDataButtonClick = () => {
    if (page < (survyePremium?.premiumSurveysCount ?? 0)) {
      setPage((prevPage) => prevPage + 1);
      setBackendSendPage((prevPage) => prevPage + 1);
    } else {
      setToastOpen(true);
    }
  };
  useEffect(() => {
    setSurveyPremium(surveyPremiumData);
  }, [surveyPremiumData]);
  return (
    <Stack flexDirection={"row"}>
      <Toast
        open={toastOpen}
        onClose={() => setToastOpen(false)}
        toastMessage="게시글이 존재하지 않습니다."
      />
      <IconButton
        sx={{ width: "32px", height: "32px", margin: "auto" }}
        onClick={() => {
          page > 0 ? setPage((prevPage) => prevPage - 1) : setToastOpen(true);
        }}
      >
        <KeyboardArrowLeft />
      </IconButton>
      {premiumSurveysResponse.SurveyDtos.map((survey) => {
        return (
          <PremiumSurvey
            key={survey.surveyId}
            surveyId={survey.surveyId}
            surveyImg={survey.surveyImage}
            surveyWriter={survey.surveyWriter}
            surveyTitle={survey.surveyTitle}
            surveyItemCount={survey.surveyItemCount}
            isDone={survey.isDone}
            isPremium={survey.isPremium}
          />
        );
      })}
      <IconButton
        sx={{ width: "32px", height: "32px", margin: "auto" }}
        onClick={newDataButtonClick}
      >
        <KeyboardArrowRight />
      </IconButton>
    </Stack>
  );
}
