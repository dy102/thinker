import { IconButton, Stack } from "@mui/material";
import Toast from "../../Toast/Toast";
import { useEffect, useState } from "react";
import { IThinkingPremium } from "../../types/dto";
import { useGetThinkingPremiumQuery } from "@/api/thinking-api";
import { KeyboardArrowLeft, KeyboardArrowRight } from "@mui/icons-material";
import PremiumThinking from "../../Premium/PremiumThinking/PremiumThinking";

export default function PremiumThinkingCollect() {
  const premiumThinkingResponse = {
    premiumThinkingCount: 2,
    dtos: [
      {
        thinkingId: 4,
        thinkingThumbnail:
          "https://assets.paintingco.com/dev-3/wp-content/uploads/20230802110655/shutterstock_1561397020-scaled.jpg",
        thinkingWriter: "Jun Seo",
        thinkingTitle: "Is Thinker good?",
        isPremium: true,
        likeCount: 9,
        repliesCount: 11,
        viewCount: 2530,
      },
      {
        thinkingId: 5,
        thinkingThumbnail:
          "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3l2fb-6xT3Krier-wokMNXGNpM-t6qvSghg&usqp=CAU",
        thinkingWriter: "Walt Disney",
        thinkingTitle: "Disney Land Open Run",
        isPremium: true,
        likeCount: 3,
        repliesCount: 10,
        viewCount: 123,
      },
      {
        thinkingId: 6,
        thinkingThumbnail: "https://www.kocca.kr/trend/vol30/img/s11/img_1.jpg",
        thinkingWriter: "netflix.Inc",
        thinkingTitle: "New Netflix Subscription System",
        isPremium: true,
        likeCount: 20,
        repliesCount: 5,
        viewCount: 123,
      },
    ],
  };
  const [page, setPage] = useState(0);
  const [backendSendPage, setBackendSendPage] = useState(1);
  const [toastOpen, setToastOpen] = useState(false);

  const [thinkingPremium, setThinkingPremium] = useState<IThinkingPremium>();
  const { data: thinkingPremiumData } = useGetThinkingPremiumQuery({
    page: backendSendPage,
  });
  const newDataButtonClick = () => {
    if (page < (thinkingPremium?.premiumThinkingCount ?? 0)) {
      setPage((prevPage) => prevPage + 1);
      setBackendSendPage((prevPage) => prevPage + 1);
    } else {
      setToastOpen(true);
    }
  };
  useEffect(() => {
    setThinkingPremium(thinkingPremiumData);
  }, [thinkingPremiumData]);
  return (
    <Stack flexDirection={"row"}>
      <Toast
        open={toastOpen}
        onClose={() => setToastOpen(false)}
        toastMessage="게시글이 존재하지 않습니다."
      />
      <IconButton
        sx={{ width: "32px", height: "32px", margin: "auto" }}
        onClick={() => {
          page > 0 ? setPage((prevPage) => prevPage - 1) : setToastOpen(true);
        }}
      >
        <KeyboardArrowLeft />
      </IconButton>
      {premiumThinkingResponse?.dtos?.map((thinking) => {
        return (
          <PremiumThinking
            key={thinking.thinkingId}
            thinkingId={thinking.thinkingId}
            thinkingThumbnail={thinking.thinkingThumbnail}
            thinkingWriter={thinking.thinkingWriter}
            thinkingTitle={thinking.thinkingTitle}
            likecount={thinking.likeCount}
            repliesCount={thinking.repliesCount}
            viewCount={thinking.viewCount}
          />
        );
      })}
      <IconButton
        sx={{ width: "32px", height: "32px", margin: "auto" }}
        onClick={newDataButtonClick}
      >
        <KeyboardArrowRight />
      </IconButton>
    </Stack>
  );
}
