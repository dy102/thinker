import { mainColor } from "@/components/Themes/color";
import { MultipleSurveyType } from "@/components/types/common";
import { RadioGroup, Stack } from "@mui/material";
import React from "react";
import { SurveyBox } from "../SurveyBox.style";
import SurveyRadioGroupDone from "./SurveyRadioGroupDone";
import SurveyRadioGroupNotYet from "./SurveyRadioGroupNotYet";

function MultipleChoice({
  multipleChoiceId,
  question,
  items,
  isDone,
  surveyPost,
}: MultipleSurveyType) {
  const [value, setValue] = React.useState((0).toString());
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setValue((event.target as HTMLInputElement).value);
  };

  return (
    <SurveyBox>
      <Stack
        display={"flex"}
        justifyContent={"flex-start"}
        alignItems={"flex-end"}
        flexDirection={"row"}
        marginBottom={"30px"}
      >
        <Stack fontSize={"35px"} marginRight={"10px"} color={mainColor}>
          Q.
        </Stack>
        <Stack fontSize={"25px"}>{question}</Stack>
      </Stack>
      <RadioGroup value={value} onChange={handleChange}>
        {isDone ? (
          <Stack>
            {items.map((item) => {
              return (
                <SurveyRadioGroupDone
                  key={item.itemId}
                  multipleChoiceId={multipleChoiceId}
                  itemId={item.itemId}
                  item={item.item}
                  isCheck={item.isCheck}
                  surveyPost={surveyPost}
                />
              );
            })}
          </Stack>
        ) : (
          <Stack>
            {items.map((item) => {
              return (
                <SurveyRadioGroupNotYet
                  key={item.itemId}
                  multipleChoiceId={multipleChoiceId}
                  itemId={item.itemId}
                  item={item.item}
                  isCheck={item.isCheck}
                  surveyPost={surveyPost}
                />
              );
            })}
          </Stack>
        )}
      </RadioGroup>
    </SurveyBox>
  );
}

export default MultipleChoice;
