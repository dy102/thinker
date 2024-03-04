"use client";
import { PostSurveysParticipate } from "@/api/survey-api";
import { Stack } from "@mui/material";
import { useMutation, useQueryClient } from "@tanstack/react-query";

function SurveyWrite() {

  const queryClient = useQueryClient();
  const postMakeSurveysCreateQuery = useMutation(PostSurveysParticipate, {
    onSuccess: () => {
      queryClient.invalidateQueries(["surveys"]);
    },
  });
  const MakeSurveysOnClick = () => {
    const newMakeSurveysBody = {

    }

    postMakeSurveysCreateQuery.mutate(newMakeSurveysBody);
  }
  return (
    <Stack>
      <Stack>설문조사 만들기 페이지</Stack>
      <Stack>한번 만들어봅시다</Stack>
    </Stack>
  );
}

export default SurveyWrite;
