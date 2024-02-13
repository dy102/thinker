import { Stack } from "@mui/material";
import { ReplyType } from "../types/common";
import { mainColor } from "../Themes/color";

export default function Reply({
  userId,
  replyContent,
  likeCount,
  createdAt,
  isLiked,
}: ReplyType) {
  return (
    <Stack flexDirection={"row"}>
      <Stack
        width={"36px"}
        height={"36px"}
        bgcolor={`${mainColor}`}
        borderRadius={"50%"}
        marginRight={"30px"}
      ></Stack>
      <Stack>{replyContent}</Stack>
    </Stack>
  );
}
