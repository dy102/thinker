"use client";
import Button from "@/components/Button/Button";
import PremiumSurvey from "@/components/Premium/PremiumSurvey/PremiumSurvey";
import { mainColor } from "@/components/Themes/color";
import { Menu, MenuItem, Stack } from "@mui/material";
import React, { useState } from "react";
import AlignHorizontalLeftIcon from "@mui/icons-material/AlignHorizontalLeft";

function Page() {
  const premiumSurveysResponse = {
    premiumSurveysCount: 2,
    SurveyDtos: [
      {
        surveyId: 1,
        surveyImage:
          "https://assets.paintingco.com/dev-3/wp-content/uploads/20230802110655/shutterstock_1561397020-scaled.jpg",
        surveyWriter: "Jun Seo",
        surveyTitle: "Is Thinker good?",
        surveyItemCount: 5,
        isDone: true,
        isPremium: true,
      },
      {
        surveyId: 2,
        surveyImage:
          "https://t4.ftcdn.net/jpg/00/93/83/93/240_F_93839373_JT5cpdRmTyuOg6eert4Kexq8aO63iD5r.jpg",
        surveyWriter: "Do Yeon",
        surveyTitle: "Is Thinker good? Is Thinker good? Is Thinker good?",
        surveyItemCount: 12,
        isDone: false,
        isPremium: true,
      },
      {
        surveyId: 3,
        surveyImage:
          "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3l2fb-6xT3Krier-wokMNXGNpM-t6qvSghg&usqp=CAU",
        surveyWriter: "Walt Disney",
        surveyTitle: "Disney Plus Subscribe prise",
        surveyItemCount: 12,
        isDone: false,
        isPremium: true,
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
        SURVEY
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
          Premium Survey
        </Stack>
        <Stack flexDirection={"row"}>
          {premiumSurveysResponse.SurveyDtos.map((survey) => {
            return (
              <PremiumSurvey
                key={survey.surveyId}
                surveyImg={survey.surveyImage}
                surveyWriter={survey.surveyWriter}
                surveyTitle={survey.surveyTitle}
                surveyItemCount={survey.surveyItemCount}
                isDone={survey.isDone}
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
export default Page;
