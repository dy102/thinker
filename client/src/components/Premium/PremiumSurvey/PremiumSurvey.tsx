import { PremiumSurveyType } from "@/components/types/common";
import { Stack, Tooltip } from "@mui/material";
import { SurveyBox, SurveyThumbnail } from "./PremiumSurvey.style";
import { mainColor } from "@/components/Themes/color";
import Button from "@/components/Button/Button";
import PageLink from "@/components/PageLink/PageLink";
import VerifiedIcon from '@mui/icons-material/Verified';

function PremiumSurvey({
  surveyImg,
  surveyWriter,
  surveyTitle,
  surveyItemCount,
  isDone,
}: PremiumSurveyType) {
  return (
    <SurveyBox>
      <SurveyThumbnail height={'55%'} bgcolor={'wheat'} imagesrc={surveyImg}></SurveyThumbnail>
      <Stack padding={'20px 20px'}>
        <Stack flexDirection={'row'} alignItems={'flex-end'} justifyContent={'space-between'}>
          <Stack flexDirection={'row'} alignItems={'flex-end'}>
            <Stack fontSize={'10px'} marginRight={'10px'}>by.</Stack>
            <Stack fontSize={'12px'} bgcolor={`${mainColor}`} color={"white"} borderRadius={'7px'} padding={'2px 6px'}>{surveyWriter}</Stack>

          </Stack>
          <Stack fontSize={'12px'} color={'rgba(128,128,128, 0.9)'}>
            <Stack>{surveyItemCount} 개의 문항이 있어요</Stack>
          </Stack>

        </Stack>
        <Stack marginTop={'15px'} fontSize={'20px'} overflow={'hidden'} textOverflow={'ellipsis'} whiteSpace={'nowrap'}>{surveyTitle}</Stack>

        <Stack width={'200px'} margin={'10px auto auto auto'}>
          <Button sx={{ position: 'relative' }}>
            <PageLink href={"#"}>
              참여하기
            </PageLink>
          </Button>
          {
            isDone ? (
              <Tooltip title="이미 참여했어요" placement="bottom">
                <VerifiedIcon sx={{ color: `${mainColor}`, position: 'absolute' }} fontSize="large" />
              </Tooltip>
            ) : (<></>)
          }

        </Stack>
      </Stack>
    </SurveyBox>
  );
}

export default PremiumSurvey;
