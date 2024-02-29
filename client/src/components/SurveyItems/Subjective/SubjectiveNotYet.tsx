import { Stack, TextField } from "@mui/material";
import { SurveyBox } from "../SurveyBox.style";
import { mainColor } from "@/components/Themes/color";
import { SubjectiveContentNotYetType } from "@/components/types/common";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { PostSubjectiveApi } from "@/api/survey-api";
import { useState } from "react";

function SubjectiveNotYet({
  subjectiveFormId,
  question,
  surveyPost,
}: SubjectiveContentNotYetType) {
  const [subjAnswer, setSubjAnswer] = useState("");
  const queryClient = useQueryClient();
  const postSubjectiveCreateQuery = useMutation(PostSubjectiveApi, {
    onSuccess: () => {
      queryClient.invalidateQueries(["surveys"]);
      console.log("success");
    },
  });
  const SubjectivePost = () => {
    const newSubjectiveBody = {
      subjectiveId: subjectiveFormId,
      answer: subjAnswer,
    };

    postSubjectiveCreateQuery.mutate(newSubjectiveBody);
  };
  if (surveyPost) {
    SubjectivePost;
    console.log(surveyPost);
  }
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
      <TextField
        onChange={(e) => {
          setSubjAnswer(e.target.value);
        }}
        fullWidth
      ></TextField>
    </SurveyBox>
  );
}

export default SubjectiveNotYet;
