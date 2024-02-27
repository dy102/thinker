import { Stack, TextField } from "@mui/material";
import { SurveyBox } from "../SurveyBox.style";
import { mainColor } from "@/components/Themes/color";
import { SubjectiveContentNotYetType } from "@/components/types/common";

function SubjectiveNotYet({
  subjectiveFormId,
  question,
}: SubjectiveContentNotYetType) {
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
      <TextField fullWidth></TextField>
    </SurveyBox>
  );
}

export default SubjectiveNotYet;
