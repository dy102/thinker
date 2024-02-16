import { SurveyType } from "@/components/types/common";
import { Stack, Tooltip } from "@mui/material";
import {
  NonNormalSurveyThumbnail,
  NormalSurveyBox,
  NormalSurveyThumbnail,
  SurveyTitle,
} from "./NormalSurvey.style";
import { mainColor } from "@/components/Themes/color";
import VerifiedIcon from "@mui/icons-material/Verified";

function NormalSurvey({
  surveyId,
  surveyTitle,
  surveyWriter,
  surveyImg,
  surveyItemCount,
  isDone,
  isPremium,
}: SurveyType) {
  return (
    <NormalSurveyBox>
      <Stack
        width={"70%"}
        paddingTop={"50px"}
        paddingLeft={"20px"}
        flexDirection={"column"}
      >
        <SurveyTitle href={`/thinking/${surveyId}`}>{surveyTitle}</SurveyTitle>
        <Stack
          display={"flex"}
          flexDirection={"row"}
          justifyContent={"flex-start"}
          alignItems={"center"}
        >
          <Stack marginBottom={"40px"}>by. {surveyWriter}</Stack>
          <Stack
            marginLeft={"100px"}
            display={"flex"}
            justifyContent={"flex-end"}
            alignItems={"center"}
          >
            {isDone ? (
              <Tooltip title="이미 참여했어요" placement="bottom">
                <VerifiedIcon
                  sx={{ color: `${mainColor}`, position: "absolute" }}
                  fontSize="large"
                />
              </Tooltip>
            ) : (
              <></>
            )}
          </Stack>
        </Stack>
        <Stack
          marginTop={"10px"}
        >{`총 ${surveyItemCount}개의 문항이 있어요.`}</Stack>
      </Stack>
      <Stack width={"30%"}>
        {surveyImg === null ? (
          <NonNormalSurveyThumbnail></NonNormalSurveyThumbnail>
        ) : (
          <NormalSurveyThumbnail imagesrc={surveyImg}></NormalSurveyThumbnail>
        )}
      </Stack>
    </NormalSurveyBox>
  );
}

export default NormalSurvey;
