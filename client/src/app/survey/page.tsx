"use client";
import PremiumSurvey from "@/components/Premium/PremiumSurvey/PremiumSurvey";
import { mainColor } from "@/components/Themes/color";
import { Stack } from "@mui/material";

function page() {

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
        surveyImage: "https://t4.ftcdn.net/jpg/00/93/83/93/240_F_93839373_JT5cpdRmTyuOg6eert4Kexq8aO63iD5r.jpg",
        surveyWriter: "Do Yeon",
        surveyTitle: "Is Thinker good? Is Thinker good? Is Thinker good?",
        surveyItemCount: 12,
        isDone: false,
        isPremium: true,
      },
    ],
  };

  return (
    <Stack margin={'0 auto'}>
      <Stack fontSize={'100px'} color={`${mainColor}`} margin={'auto'} fontWeight={'bolder'}>SURVEY</Stack>
      <Stack flexDirection={'row'}>
        {premiumSurveysResponse.SurveyDtos.map((survey) => {
          return (
            <PremiumSurvey
              key={survey.surveyId}
              surveyImg={survey.surveyImage}
              surveyWriter={survey.surveyWriter}
              surveyTitle={survey.surveyTitle}
              surveyItemCount={survey.surveyItemCount}
              isDone={survey.isDone}
            />
          );
        })}
      </Stack>

    </Stack>
  )
}
export default page;
