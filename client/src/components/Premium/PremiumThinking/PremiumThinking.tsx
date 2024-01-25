import { Stack } from "@mui/material";
import { PremiumSurveyType, PremiumThinkingType } from "../../types/common";
import { mainColor } from "../../Themes/color";

function PremiumThinking({
  thinkingImg,
  thinkingWriter,
  thinkingTitle,
  likeCount,
  viewCount,
}: PremiumThinkingType) {
  return (
    <Stack>
      <Stack
        width={"300px"}
        height={"300px"}
        bgcolor={`${mainColor}`}
        margin={"20px"}
        borderRadius={"15px"}
      ></Stack>
    </Stack>
  );
}

export default PremiumThinking;
