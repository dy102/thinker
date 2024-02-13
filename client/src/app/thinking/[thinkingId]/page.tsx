"use client";

import { useGetThinkingQuery } from "@/api/thinking-api";
import { PopularCount } from "@/components/Premium/PremiumThinking/PremiumThinking.style";
import { mainColor } from "@/components/Themes/color";
import { IReplies, IThinking } from "@/components/types/dto";
import { Stack, TextField } from "@mui/material";
import { useEffect, useState } from "react";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import VisibilityIcon from "@mui/icons-material/Visibility";
import ChatBubbleOutlineIcon from "@mui/icons-material/ChatBubbleOutline";
import Button from "@/components/Button/Button";
import ThinkingImageTag from "./ThinkingImage";
import { PostReplyApi, useGetRepliesQuery } from "@/api/reply-api";
import {
  QueryClient,
  useMutation,
  useQueryClient,
} from "@tanstack/react-query";

function page({ params }: { params: { thinkingId: number } }) {
  // get main thinking contents
  const { data: thinkingData } = useGetThinkingQuery({
    thinkingId: params.thinkingId,
  });
  const [thinking, setThinking] = useState<IThinking>();

  // get replies
  const { data: repliesData } = useGetRepliesQuery({
    thinkingId: params.thinkingId,
  });
  const [replies, setReplies] = useState<IReplies>();

  // post reply
  const queryClient = useQueryClient();
  const [replyContent, setReplyContent] = useState("");
  const postReplyCreateQuery = useMutation(PostReplyApi, {
    onSuccess: () => {
      queryClient.invalidateQueries(["replies"]);
      console.log("success");
    },
  });
  const ReplyOnClick = () => {
    const newReplyBody = {
      thinkingId: params.thinkingId,
      replyContent: replyContent,
    };

    postReplyCreateQuery.mutate(newReplyBody);
  };

  useEffect(() => {
    setThinking(thinkingData);
    setReplies(repliesData);
  }, [thinkingData, repliesData]);
  const thinkingImage = [
    "https://img.hankyung.com/photo/202210/01.31515036.1.png",
    "https://www.kocca.kr/trend/vol30/img/s11/img_1.jpg",
  ];
  return (
    <Stack margin={"0 auto"} maxWidth={"1024px"}>
      {/* <Stack>{thinking?.ThinkingDetailDto.thinkingTitle}</Stack> */}
      <Stack
        fontSize={"70px"}
        color={`${mainColor}`}
        textAlign={"center"}
        marginTop={"50px"}
      >
        New Netflix Subscription System
      </Stack>
      {/* <Stack>{thinking?.ThinkingDetailDto.thinkingWriter}</Stack> */}
      <Stack textAlign={"left"} marginTop={"20px"}>
        netflix.Inc
      </Stack>
      {/* <Stack>{thinking?.ThinkingDetailDto.thinkingContents}</Stack> */}
      <Stack lineHeight={1.9} margin={"50px 30px"}>
        요금(한국 원화) 광고형 스탠다드*: 월 5,500원 스탠다드: 월
        13,500원(월5,000원 추가 지불 시 추가 회원 자리** 등록 가능) 프리미엄: 월
        17,000원(월 5,000원 추가 지불 시 추가 회원 자리** 등록 가능) 참고: *일부
        타사 결제 파트너 및 패키지를 통해 가입하는 경우에는 광고형 스탠다드
        멤버십이 제공되지 않을 수도 있습니다. 제공업체에 문의하여 이용 가능
        여부를 확인하세요. 참고: **추가 회원은 본인의 계정과 비밀번호를 가지게
        되지만, 멤버십 요금은 넷플릭스 계정을 공유하기 위해 추가 회원을 초대한
        사람이 결제하게 됩니다. 등록할 수 있는 추가 회원 자리는 멤버십에 따라
        다릅니다. 신규 또는 재가입 회원에게는 베이식 멤버십이 더 이상 제공되지
        않습니다. 현재 베이식 멤버십을 이용 중인 경우, 멤버십을 변경하거나
        계정을 해지하기 전까지는 해당 멤버십을 계속 이용할 수 있습니다. 거주
        지역에 따라 멤버십 요금에 추가로 세금이 부과될 수 있습니다. 오늘
        넷플릭스에 가입하고 다양한 결제 옵션 중에서 원하는 옵션을 선택하세요.
        언제든지 쉽게 멤버십을 변경하거나 해지할 수 있습니다.
      </Stack>
      <Stack flexDirection={"row"}>
        {thinkingImage.map((image) => {
          return <ThinkingImageTag ImageSrc={image} />;
        })}
      </Stack>
      <Stack
        flexDirection={"row"}
        fontSize={"12px"}
        color={"rgba(128,128,128, 0.9)"}
        marginTop={"90px"}
      >
        <PopularCount>
          <FavoriteBorderIcon
            sx={{ marginRight: "2px", color: `${mainColor}` }}
            fontSize="small"
          />
          12
        </PopularCount>
        <PopularCount>
          <ChatBubbleOutlineIcon sx={{ marginRight: "3px" }} fontSize="small" />
          {56}
        </PopularCount>

        <PopularCount flexDirection={"row"}>
          <VisibilityIcon sx={{ marginRight: "3px" }} />
          {45}
        </PopularCount>
      </Stack>
      <Stack
        display={"flex"}
        justifyContent={"center"}
        alignItems={"flex-start"}
      >
        <Button sx={{ width: "120px", marginTop: "20px" }}>
          <FavoriteBorderIcon fontSize="small" />
          공감
        </Button>
      </Stack>
      <Stack marginTop={"50px"}>
        <Stack
          display={"flex"}
          justifyContent={"center"}
          alignItems={"center"}
          flexDirection={"row"}
        >
          <Stack
            width={"36px"}
            height={"36px"}
            bgcolor={`${mainColor}`}
            borderRadius={"50%"}
            marginRight={"30px"}
          ></Stack>
          <TextField
            sx={{ width: "80%", marginRight: "30px" }}
            onChange={(e) => {
              setReplyContent(e.target.value);
            }}
          />
          <Button onClick={() => ReplyOnClick()}>게시하기</Button>
        </Stack>
      </Stack>
    </Stack>
  );
}

export default page;
