"use client";

import { GoToSurvey, GoToThinking, LeftInnerTrapezoid, LeftTrapezoid, RightInnerTrapezoid, RightTrapezoid } from "@/components/Layout/Mainpage.style";
import {
  AnimatedTitle,
  Content,
  IntroComment,
  Thinker,
  Track,
} from "@/components/Layout/page.style";
import PageLink from "@/components/PageLink/PageLink";
import PremiumSurvey from "@/components/Premium/PremiumSurvey/PremiumSurvey";
import PremiumThinking from "@/components/Premium/PremiumThinking/PremiumThinking";
import { mainColor } from "@/components/Themes/color";
import { Stack, Typography } from "@mui/material";
import { useState } from "react";

export default function Home() {
  const [result, setResult] = useState(0);
  const premiumSurveysResponse = {
    premiumSurveysCount: 2,
    SurveyDtos: [
      {
        surveyId: 1,
        surveyImage:
          "https://assets.paintingco.com/dev-3/wp-content/uploads/20230802110655/shutterstock_1561397020-scaled.jpg",
        surveyWriter: "jsjsjsjs",
        surveyTitle: "Is Thinker good?",
        surveyItemCount: 5,
        isDone: false,
        isPremium: true,
      },
      {
        surveyId: 2,
        surveyImage: "string",
        surveyWriter: "jsjsjsjs",
        surveyTitle: "Is Thinker good?",
        surveyItemCount: 5,
        isDone: false,
        isPremium: true,
      },
    ],
  };
  const premiumThinkingResponse = {
    premiumThinkingCount: 2,
    premiumThinkingDtos: [
      {
        thinkingId: 11,
        thinkingThumbnail: "string",
        thinkingWriter: "string",
        thinkingTitle: "string",
        isPremium: true,
        likeCount: 12,
        repliesCount: 24,
        viewCount: 36,
      },
      {
        thinkingId: 12,
        thinkingThumbnail: "string",
        thinkingWriter: "string",
        thinkingTitle: "string",
        isPremium: true,
        likeCount: 12,
        repliesCount: 24,
        viewCount: 36,
      },
    ],
  };

  return (
    <Stack display={"flex"} flexDirection={"column"}>
      <LeftTrapezoid imagesrc="https://i0.wp.com/cdn.tiqets.com/wordpress/blog/wp-content/uploads/2023/01/05125828/Creation_of_Adam_Michelangelo_1475%E2%80%931564_circa_1511-1024x465.jpg?resize=1024%2C465&ssl=1" height={'95vh'} bgcolor={'wheat'} position={'relative'}>
        <Stack zIndex={1} width={'100%'} height={"200px"} position={'absolute'} margin={'auto'}>
          <Thinker fontSize={"130px"}>THINKER</Thinker>
        </Stack>
        <LeftInnerTrapezoid width={'100%'} height={'25vh'} bgcolor={'rgba(128,128,128, 0.5)'} margin={'auto'}>
          <GoToSurvey>
            <Stack width="300px" textAlign="center">
              <PageLink href={"/survey"}>
                <Stack color={"white"} fontWeight={550} fontSize={"25px"}>
                  {"Get More SURVEY >>>"}
                </Stack>
              </PageLink>
            </Stack>
          </GoToSurvey>
        </LeftInnerTrapezoid>
        <RightTrapezoid imagesrc="https://c1.wallpaperflare.com/preview/502/735/779/art-school-of-athens-rapha%C3%ABl-italian-painter-fresco.jpg" width={'50%'} height={'95vh'} bgcolor={'teal'} position={'absolute'} right={0}>
          <RightInnerTrapezoid width={'100%'} height={'25vh'} bgcolor={'rgba(128,128,128, 0.5)'} margin={'auto'}>
            <GoToThinking>
              <Stack width="300px" textAlign="center">
                <PageLink href={"/thinking"}>
                  <Stack color={"white"} fontWeight={550} fontSize={"25px"}>
                    {"<<< Get More THINKING"}
                  </Stack>
                </PageLink>
              </Stack>
            </GoToThinking>
          </RightInnerTrapezoid>

        </RightTrapezoid>
      </LeftTrapezoid>

      <AnimatedTitle>
        <Track>
          <Content>
            <Stack>
              나만의 설문조사를 만들어보세요 평소의 생각들, 시사, 경제 등 다양한
              주제들을 THINKING에 적어주세요 설문조사에 참여해 포인트를 쌓아
              프리미엄 설문조사, THINKING으로 만들어보세요 다양한 정보들과
              설문조사들이 넘처나는 THINKER 입니다!!
            </Stack>
          </Content>
        </Track>
      </AnimatedTitle>
      <Stack marginTop="200px" height="400px" flexDirection="row">
        <IntroComment>
          <Stack>설문조사에 참여해 포인트를 쌓아보세요!!</Stack>
        </IntroComment>
        <Stack width={"45%"} height={"100%"} bgcolor={`${mainColor}`}></Stack>
      </Stack>
      <Stack marginTop="80px" height="400px" flexDirection="row">
        <Stack width={"45%"} height={"100%"} bgcolor={`${mainColor}`}></Stack>
        <IntroComment>
          <Stack>
            포인트를 이용해 프리미엄 설문조사/게시물을 만들어보세요!!
          </Stack>
          <Stack>프리미엄 설문조사/게시물은 상단에 노출됩니다</Stack>
        </IntroComment>
      </Stack>
      <Typography
        color={`${mainColor}`}
        fontSize="100px"
        margin="auto"
        marginTop={"170px"}
      >
        PREMIUM
      </Typography>
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

      <Stack flexDirection={"row"}>
        {premiumThinkingResponse.premiumThinkingDtos.map((thinking) => {
          return (
            <PremiumThinking
              key={thinking.thinkingId}
              thinkingImg={thinking.thinkingThumbnail}
              thinkingWriter={thinking.thinkingWriter}
              thinkingTitle={thinking.thinkingTitle}
              likeCount={thinking.repliesCount}
              viewCount={thinking.viewCount}
            />
          );
        })}
      </Stack>
    </Stack>
  );
}
