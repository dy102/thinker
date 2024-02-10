"use client";
import Button from "@/components/Button/Button";
import { mainColor } from "@/components/Themes/color";
import { Menu, MenuItem, Stack } from "@mui/material";
import React, { useState } from "react";
import AlignHorizontalLeftIcon from "@mui/icons-material/AlignHorizontalLeft";
import PremiumThinkingCollect from "@/components/collect/PremiumThinkingCollect";
import NormalThinkingCollect from "@/components/Normal/NormalThinking/NormalThinkingCollect";
import PageLink from "@/components/PageLink/PageLink";

function page() {

  const normalThinkingResponse = {
    contents: {
      dtos: [
        {
          thinkingId: 0,
          thinkingThumbnail: [
            'string'
          ],
          thinkingWriter: "string",
          thinkingTitle: "string",
          isPremium: false,
          likeCount: 0,
          repliesCount: 0,
          viewCount: 0
        }
      ]
    },
    totalElements: 0,
    nextCursor: 0
  };
  // 정렬기준
  const [kind, setKind] = useState("recent");
  const kindList = ["recent", "like", 'view'];
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const sortopen = Boolean(anchorEl);
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
        <PremiumThinkingCollect />
      </Stack>
      <Stack
        marginTop={"50px"}
        flexDirection={"row"}
        justifyContent={"flex-end"}
      >
        <Stack flexDirection={'row'} justifyContent={"space-between"} alignItems={'center'} width={'100%'}>
          <Button>
            <PageLink href={'#'} style={{ fontSize: '18px', alignItems: 'center' }}>
              게시물 작성하기
            </PageLink></Button>
          <Stack flexDirection={"row"} justifyContent={"left"}>
            <Button
              onClick={handleClick}
              sx={{ padding: "8px 10px", minWidth: "24px" }}
            >
              <AlignHorizontalLeftIcon fontSize="medium" />
              정렬기준
            </Button>
            <Menu anchorEl={anchorEl} open={sortopen} onClose={handleClose}>
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
              <MenuItem
                onClick={() => {
                  handleClose();
                  setKind(kindList[2]);
                }}
              >
                조회순
              </MenuItem>
            </Menu>
          </Stack>
        </Stack>
      </Stack>
      <NormalThinkingCollect kind={kind}></NormalThinkingCollect>
    </Stack>
  );
}
export default page;
