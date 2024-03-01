import { FormControlLabel, Radio } from "@mui/material";
import { MultipleSurveyItemComponentType } from "../../types/common";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { PostMultipleChoiceApi } from "@/api/survey-api";
import { useState } from "react";

function SurveyRadioGroupNotYet({
  multipleChoiceId,
  itemId,
  item,
  isCheck,
  surveyPost,
}: MultipleSurveyItemComponentType) {
  const [choice, setChoice] = useState(false);
  const ChoiceOnClick = () => {
    setChoice(true);
  };

  const queryClient = useQueryClient();
  const postMultipleChoiceCreateQuery = useMutation(PostMultipleChoiceApi, {
    onSuccess: () => {
      queryClient.invalidateQueries(["surveys"]);
      console.log("success");
    },
  });

  const MultipleChoicePost = () => {
    const newMultipleChoiceBody = {
      multipleChoiceId: multipleChoiceId,
      itemId: itemId,
      isCheck: choice,
    };

    postMultipleChoiceCreateQuery.mutate(newMultipleChoiceBody);
  };
  if (surveyPost) {
    MultipleChoicePost;
    console.log(surveyPost);
  }
  return (
    <FormControlLabel
      value={itemId.toString()}
      control={<Radio />}
      label={item}
      onClick={ChoiceOnClick}
    />
  );
}

export default SurveyRadioGroupNotYet;
