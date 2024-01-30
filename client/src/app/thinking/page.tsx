"use client";
import Button from "@/components/Button/Button";
import PremiumSurvey from "@/components/Premium/PremiumSurvey/PremiumSurvey";
import { mainColor } from "@/components/Themes/color";
import { Menu, MenuItem, Stack } from "@mui/material";
import React, { useState } from "react";
import AlignHorizontalLeftIcon from "@mui/icons-material/AlignHorizontalLeft";
import PremiumThinking from "@/components/Premium/PremiumThinking/PremiumThinking";

function page() {
  const premiumThinkingResponse = {
    premiumThinkingCount: 2,
    premiumThinkingDtos: [
      {
        thinkingId: 4,
        thinkingThumbnail:
          "https://assets.paintingco.com/dev-3/wp-content/uploads/20230802110655/shutterstock_1561397020-scaled.jpg",
        thinkingWriter: "Jun Seo",
        thinkingTitle: "Is Thinker good?",
        isPremium: true,
        likeCount: 5,
        repliesCount: 10,
        viewCount: 123,
      },
      {
        thinkingId: 5,
        thinkingThumbnail:
          "https://t4.ftcdn.net/jpg/00/93/83/93/240_F_93839373_JT5cpdRmTyuOg6eert4Kexq8aO63iD5r.jpg",
        thinkingWriter: "Jun Seo",
        thinkingTitle: "Is Thinker good?",
        isPremium: true,
        likeCount: 5,
        repliesCount: 10,
        viewCount: 123,
      },
      {
        thinkingId: 6,
        thinkingThumbnail:
          "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3l2fb-6xT3Krier-wokMNXGNpM-t6qvSghg&usqp=CAU",
        thinkingWriter: "Jun Seo",
        thinkingTitle: "Is Thinker good?",
        isPremium: true,
        likeCount: 5,
        repliesCount: 10,
        viewCount: 123,
      },
    ],
  };

  //정렬기준
  const [kind, setKind] = useState("recent");
  const kindList = ["recent", "popular"];
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const friendopen = Boolean(anchorEl);
  const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <Stack margin={"0 auto"}>
      <Stack
        fontSize={"100px"}
        color={`${mainColor}`}
        margin={"auto"}
        fontWeight={"bolder"}
      >
        THINKING
      </Stack>
      <Stack
        marginTop={"30px"}
        flexDirection={"column"}
        borderTop={`1px solid ${mainColor}`}
      >
        <Stack
          display={"flex"}
          alignItems={"center"}
          width={"100%"}
          fontSize={"25px"}
          color={`${mainColor}`}
          borderBottom={`1px solid ${mainColor}`}
        >
          Premium Thinking
        </Stack>
        <Stack flexDirection={"row"}>
          {premiumThinkingResponse.premiumThinkingDtos.map((thinking) => {
            return (
              <PremiumThinking
                key={thinking.thinkingId}
                thinkingThumbnail={thinking.thinkingThumbnail}
                thinkingWriter={thinking.thinkingWriter}
                thinkingTitle={thinking.thinkingTitle}
                likecount={thinking.likeCount}
                repliesCount={thinking.repliesCount}
                viewCount={thinking.viewCount}
              />
            );
          })}
        </Stack>
      </Stack>
      <Stack
        marginTop={"50px"}
        flexDirection={"row"}
        justifyContent={"flex-end"}
      >
        <Stack justifyContent={"center"}>
          <Stack flexDirection={"row"} justifyContent={"left"}>
            <Button
              onClick={handleClick}
              sx={{ padding: "8px 10px", minWidth: "24px" }}
            >
              <AlignHorizontalLeftIcon fontSize="medium" />
              정렬기준
            </Button>
            <Menu anchorEl={anchorEl} open={friendopen} onClose={handleClose}>
              <MenuItem
                onClick={() => {
                  handleClose();
                  setKind(kindList[0]);
                }}
              >
                최신순
              </MenuItem>
              <MenuItem
                onClick={() => {
                  handleClose();
                  setKind(kindList[1]);
                }}
              >
                인기순
              </MenuItem>
            </Menu>
          </Stack>
        </Stack>
      </Stack>
    </Stack>
  );
}
export default page;
