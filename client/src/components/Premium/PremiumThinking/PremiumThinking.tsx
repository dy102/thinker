import { PremiumThinkingType } from "@/components/types/common";
import { Stack } from "@mui/material";
import { mainColor } from "@/components/Themes/color";
import Button from "@/components/Button/Button";
import PageLink from "@/components/PageLink/PageLink";
import {
  PopularCount,
  ThinkingThumbnail,
  ThinkingyBox,
} from "./PremiumSurvey.style";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import VisibilityIcon from "@mui/icons-material/Visibility";
import ChatBubbleOutlineIcon from "@mui/icons-material/ChatBubbleOutline";

function PremiumThinking({
  thinkingThumbnail,
  thinkingWriter,
  thinkingTitle,
  likecount,
  repliesCount,
  viewCount,
}: PremiumThinkingType) {
  return (
    <ThinkingyBox>
      <ThinkingThumbnail
        height={"55%"}
        bgcolor={"wheat"}
        imagesrc={thinkingThumbnail}
      ></ThinkingThumbnail>
      <Stack padding={"20px 20px"}>
        <Stack
          flexDirection={"row"}
          alignItems={"flex-end"}
          justifyContent={"space-between"}
        >
          <Stack flexDirection={"column"}>
            <Stack flexDirection={"row"} alignItems={"flex-end"}>
              <Stack fontSize={"10px"} marginRight={"10px"} marginLeft={"2px"}>
                by.
              </Stack>
              <Stack
                fontSize={"12px"}
                bgcolor={`${mainColor}`}
                color={"white"}
                borderRadius={"7px"}
                padding={"2px 6px"}
              >
                {thinkingWriter}
              </Stack>
            </Stack>
            <Stack
              flexDirection={"row"}
              fontSize={"12px"}
              color={"rgba(128,128,128, 0.9)"}
              marginTop={"10px"}
            >
              <PopularCount>
                <FavoriteBorderIcon
                  sx={{ marginRight: "2px" }}
                  fontSize="small"
                />
                {likecount}
              </PopularCount>
              <PopularCount>
                <ChatBubbleOutlineIcon
                  sx={{ marginRight: "3px" }}
                  fontSize="small"
                />
                {repliesCount}
              </PopularCount>

              <PopularCount flexDirection={"row"}>
                <VisibilityIcon sx={{ marginRight: "3px" }} />
                {viewCount}
              </PopularCount>
            </Stack>
          </Stack>
        </Stack>
        <Stack
          marginTop={"10px"}
          fontSize={"20px"}
          overflow={"hidden"}
          textOverflow={"ellipsis"}
          whiteSpace={"nowrap"}
        >
          {thinkingTitle}
        </Stack>

        <Stack width={"200px"} margin={"10px auto auto auto"}>
          <Button sx={{ position: "relative" }}>
            <PageLink href={"#"}>보러가기</PageLink>
          </Button>
        </Stack>
      </Stack>
    </ThinkingyBox>
  );
}

export default PremiumThinking;
