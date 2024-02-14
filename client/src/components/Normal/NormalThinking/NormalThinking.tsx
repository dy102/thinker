import { ThinkingType } from "@/components/types/common";
import { Stack } from "@mui/material";
import {
  NormalThinkingBox,
  NormalThinkingNonThumbnail,
  NormalThinkingThumbnail,
  ThinkingTitle,
} from "./NormalThinking.style";
import { PopularCount } from "@/components/Premium/PremiumThinking/PremiumThinking.style";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import VisibilityIcon from "@mui/icons-material/Visibility";
import ChatBubbleOutlineIcon from "@mui/icons-material/ChatBubbleOutline";

function NormalThinking({
  thinkingThumbnail,
  thinkingId,
  thinkingWriter,
  thinkingTitle,
  likecount,
  repliesCount,
  viewCount,
}: ThinkingType) {
  return (
    <NormalThinkingBox>
      <Stack
        width={"70%"}
        paddingTop={"50px"}
        paddingLeft={"20px"}
        flexDirection={"column"}
      >
        <ThinkingTitle href={`/thinking/${thinkingId}`}>
          {thinkingTitle}
        </ThinkingTitle>
        <Stack>by. {thinkingWriter}</Stack>
        <Stack
          flexDirection={"row"}
          fontSize={"12px"}
          color={"rgba(128,128,128, 0.9)"}
          marginTop={"10px"}
        >
          <PopularCount>
            <FavoriteBorderIcon sx={{ marginRight: "2px" }} fontSize="small" />
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
      <Stack width={"30%"}>
        {thinkingThumbnail === null ? (
          <NormalThinkingNonThumbnail></NormalThinkingNonThumbnail>
        ) : (
          <NormalThinkingThumbnail
            imagesrc={thinkingThumbnail}
          ></NormalThinkingThumbnail>
        )}
      </Stack>
    </NormalThinkingBox>
  );
}
export default NormalThinking;
