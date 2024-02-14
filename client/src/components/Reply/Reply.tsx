import { Avatar, Stack, styled } from "@mui/material";
import { ReplyType } from "../types/common";
import { mainColor, shadowColor } from "../Themes/color";
import { useGetMemberSimpleQuery } from "@/api/userInfo-api";
import { useEffect, useState } from "react";
import { IMemberSimple } from "../types/dto";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import FavoriteIcon from "@mui/icons-material/Favorite";
import IconButton from "../Button/IconButton";

export const ReplyContent = styled(Stack)({
  padding: "20px 50px",
  margin: "15px 0 0 0",
  height: "auto",
  background: `${shadowColor.main}`,
  textAlign: "left",
  borderRadius: "15px",
  maxWidth: "800px",
  lineHeight: "1.7",
});

export default function Reply({
  userId,
  replyContent,
  likeCount,
  createdAt,
  isLiked,
}: ReplyType) {
  const { data: memberSimpleData } = useGetMemberSimpleQuery({
    memberId: userId,
  });
  const [memberSimple, setMemberSimple] = useState<IMemberSimple>();

  useEffect(() => {
    setMemberSimple(memberSimpleData);
  }, [memberSimpleData]);
  return (
    <Stack
      flexDirection={"column"}
      width={"100%"}
      borderRadius={"15px"}
      marginBottom={"35px"}
    >
      <Stack
        flexDirection={"row"}
        justifyContent={"flex-start"}
        alignItems={"center"}
        marginRight={"30px"}
      >
        <Avatar
          alt=""
          src={
            "https://cdn-lostark.game.onstove.com/uploadfiles/user/2021/04/06/637533443568560776.png"
          }
          sx={{ width: 35, height: 35 }}
        />
        <Stack
          display={"flex"}
          justifyContent={"center"}
          alignItems={"center"}
          margin={"5px"}
        >
          JunSeo
        </Stack>

        {isLiked ? (
          <Stack
            flexDirection={"row"}
            display={"flex"}
            justifyContent={"center"}
            alignItems={"center"}
            margin={"0 10px"}
          >
            <IconButton>
              <FavoriteIcon sx={{ color: `${mainColor}` }} />
            </IconButton>
            <Stack marginLeft={"5px"}>{likeCount}</Stack>
          </Stack>
        ) : (
          <Stack
            flexDirection={"row"}
            display={"flex"}
            justifyContent={"center"}
            alignItems={"center"}
            margin={"0 10px"}
          >
            <IconButton>
              <FavoriteBorderIcon sx={{ color: `${mainColor}` }} />
            </IconButton>
            <Stack marginLeft={"5px"}>{likeCount}</Stack>
          </Stack>
        )}
      </Stack>
      <Stack flexDirection={"row"}>
        <ReplyContent>{replyContent}</ReplyContent>
        <Stack
          display={"flex"}
          justifyContent={"flex-end"}
          margin={"0 0 5px 10px"}
        >
          {createdAt}
        </Stack>
      </Stack>
    </Stack>
  );
}
