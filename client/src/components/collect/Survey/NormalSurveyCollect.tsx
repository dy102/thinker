import { useGetSurveyNormalQuery } from "@/api/survey-api";
import NormalSurvey from "@/components/Normal/NormalSurvey/NormalSurvey";
import { ISurveyNormal } from "@/components/types/dto";
import { Stack } from "@mui/material";
import { useEffect, useState } from "react";

function NormalSurveyCollect({ kind }: { kind: string }) {
  const normalSurveyResponse = {
    surveyDtos: [
      {
        surveyId: 12,
        surveyImage:
          "https://assets.paintingco.com/dev-3/wp-content/uploads/20230802110655/shutterstock_1561397020-scaled.jpg",
        surveyWriter: "string",
        surveyTitle: "과연 thinking은 괜찮은가?",
        surveyItemCount: 12,
        isDone: false,
        isPremium: true,
      },
      {
        surveyId: 13,
        surveyImage:
          "https://assets.paintingco.com/dev-3/wp-content/uploads/20230802110655/shutterstock_1561397020-scaled.jpg",
        surveyWriter: "string",
        surveyTitle: "정말정말 괜찮을까",
        surveyItemCount: 12,
        isDone: true,
        isPremium: true,
      },
      {
        surveyId: 14,
        surveyImage: null,
        surveyWriter: "string",
        surveyTitle: "정말정말정말정말정말정말정말정말정말정말 괜찮을까",
        surveyItemCount: 12,
        isDone: true,
        isPremium: true,
      },
    ],
    nextCursor: 0,
  };
  const [kindToLastId, setKindToLastId] = useState(kind);
  const [lastId, setLastId] = useState<number | null>(0);
  const { data: normalSurveyData } = useGetSurveyNormalQuery({
    kind: kind,
    lastId: lastId,
  });
  const [normalSurvey, setNormalSurvey] = useState<ISurveyNormal>();

  useEffect(() => {
    setKindToLastId(kind);
    setNormalSurvey(normalSurveyData);
    if (kindToLastId == "recent") {
      setLastId(normalSurvey?.nextCursor ?? 0);
    }
  }, [kind, normalSurveyData, kindToLastId, normalSurvey?.nextCursor]);
  return (
    <Stack
      width={"1084px"}
      height={"100%"}
      display={"flex"}
      flexWrap={"wrap"}
      flexDirection={"row"}
    >
      {normalSurveyResponse.surveyDtos.map((normal) => {
        return (
          <NormalSurvey
            key={normal.surveyId}
            surveyId={normal.surveyId}
            surveyImg={normal.surveyImage}
            surveyWriter={normal.surveyWriter}
            surveyTitle={normal.surveyTitle}
            surveyItemCount={normal.surveyItemCount}
            isDone={normal.isDone}
            isPremium={normal.isPremium}
          />
        );
      })}
    </Stack>
  );
}
export default NormalSurveyCollect;
