import { Stack, TextField } from "@mui/material";
import { SurveyBox } from "../SurveyBox.style";
import { mainColor } from "@/components/Themes/color";
import { SubjectiveContentDoneType } from "@/components/types/common";

function SubjectiveDone({
  subjectiveFormId,
  question,
  answer,
}: SubjectiveContentDoneType) {
  return (
    <SurveyBox>
      <Stack
        display={"flex"}
        justifyContent={"flex-start"}
        alignItems={"flex-end"}
        flexDirection={"row"}
        marginBottom={"60px"}
      >
        <Stack fontSize={"35px"} marginRight={"10px"} color={mainColor}>
          Q.
        </Stack>
        <Stack fontSize={"25px"}>{question}</Stack>
      </Stack>
      <Stack>{answer}</Stack>
    </SurveyBox>
  );
}

export default SubjectiveDone;
