import { PremiumSurveyType } from "@/components/types/common";
import { Stack } from "@mui/material";

function PremiumSurvey({
  surveyImg,
  surveyWriter,
  surveyTitle,
  surveyItemCount,
  isDone,
}: PremiumSurveyType) {
  return (
    <Stack
      width={"300px"}
      height={"300px"}
      margin={"20px"}
      borderRadius={"15px"}
      border={"1px solid #000000"}
    >
      <img
        style={{
          width: "100%",
          height: "100%",
          overflow: "none",
          backgroundRepeat: "no-repeat",
          backgroundSize: "cover",
          backgroundPosition: "center",
        }}
        src={surveyImg}
      ></img>
    </Stack>
  );
}

export default PremiumSurvey;
