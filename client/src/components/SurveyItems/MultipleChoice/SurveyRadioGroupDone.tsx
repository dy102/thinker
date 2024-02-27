import { FormControlLabel, Radio } from "@mui/material";
import { MultipleSurveyItemComponentType } from "../../types/common";

function SurveyRadioGroupDone({
  multipleChoiceId,
  itemId,
  item,
  isCheck,
}: MultipleSurveyItemComponentType) {
  return (
    <FormControlLabel
      checked={isCheck}
      value={itemId.toString()}
      control={<Radio />}
      label={item}
    />
  );
}

export default SurveyRadioGroupDone;
