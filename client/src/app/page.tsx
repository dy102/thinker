"use client";

import {
  GoToSurvey,
  GoToThinking,
  IntroComment,
  LeftInnerTrapezoid,
  LeftTrapezoid,
  RightInnerTrapezoid,
  RightTrapezoid,
} from "@/components/Layout/Mainpage.style";
import {
  AnimatedTitle,
  Content,
  Thinker,
  Track,
} from "@/components/Layout/page.style";
import PageLink from "@/components/PageLink/PageLink";
import PremiumSurvey from "@/components/Premium/PremiumSurvey/PremiumSurvey";
import { mainColor } from "@/components/Themes/color";
import PremiumThinkingCollect from "@/components/collect/Thinking/PremiumThinkingCollect";
import { Stack, Typography } from "@mui/material";
import { useState } from "react";
import IntroImg from "./premium_thinking.jpg";
import Image from "next/image";
import PremiumSurveyCollect from "@/components/collect/Survey/PremiumSurveyCollect";

export default function Home() {

  return (
    <Stack display={"flex"} flexDirection={"column"}>
      <Stack flexDirection={"row"} position={"relative"}>
        <Thinker
          fontSize={"130px"}
          position={"absolute"}
          right={"37%"}
          top={"50px"}
          zIndex={1000}
        >
          THINKER
        </Thinker>
        <LeftTrapezoid
          imagesrc="https://st2.depositphotos.com/4097503/47272/v/1600/depositphotos_472726658-stock-illustration-people-filling-online-survey-form.jpg"
          width={"50%"}
          height={"95vh"}
          bgcolor={"wheat"}
          position={"relative"}
        >
          <Stack
            zIndex={1}
            width={"100%"}
            height={"200px"}
            position={"absolute"}
            margin={"auto"}
          ></Stack>
          <LeftInnerTrapezoid
            width={"100%"}
            height={"25vh"}
            bgcolor={"rgba(128,128,128, 0.5)"}
            margin={"auto"}
          >
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
        </LeftTrapezoid>
        <RightTrapezoid
          imagesrc="https://i.pinimg.com/originals/66/9c/17/669c17917cf7845d93f933dbe8976995.jpg"
          width={"50%"}
          height={"95vh"}
          bgcolor={"teal"}
          position={"relative"}
        >
          <RightInnerTrapezoid
            width={"100%"}
            height={"25vh"}
            bgcolor={"rgba(128,128,128, 0.5)"}
            margin={"auto"}
          >
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
      </Stack>

      <AnimatedTitle>
        <Track>
          <Content>
            <Stack>
              나만의 설문조사를 만들어보세요 평소의 생각들, 시사, 경제 등 다양한
              주제들을 THINKING에 적어주세요 설문조사에 참여해 포인트를 쌓아
              프리미엄 설문조사, THINKING으로 만들어보세요 다양한 정보들과
              설문조사들이 넘처나는 THINKER 입니다!!dddd
            </Stack>
          </Content>
        </Track>
      </AnimatedTitle>
      <Stack
        width={"100%"}
        marginTop="200px"
        height="400px"
        flexDirection="row"
      >
        <IntroComment>
          <Stack>설문조사에 참여해 포인트를 쌓아보세요!!</Stack>
        </IntroComment>
        <Stack width={"60%"} height={"100%"} bgcolor={`${mainColor}`}></Stack>
      </Stack>
      <Stack width={"100%"} marginTop="80px" height="400px" flexDirection="row">
        <Stack
          width={"60%"}
          height={"100%"}
          bgcolor={`${mainColor}`}
          display={"flex"}
          justifyContent={"center"}
          alignItems={"center"}
        >
          <Image alt="" src={IntroImg} width={780} height={300}></Image>
        </Stack>
        <IntroComment bgcolor={mainColor} color={"white"}>
          <Stack fontSize={"35px"} fontWeight={"bolder"} marginBottom={"15px"}>
            포인트를 이용해
          </Stack>
          <Stack fontSize={"35px"} fontWeight={"bolder"} marginBottom={"15px"}>
            프리미엄 설문조사/게시물을
          </Stack>
          <Stack fontSize={"35px"} fontWeight={"bolder"} marginBottom={"15px"}>
            만들어보세요!!
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
      <PremiumSurveyCollect />
      <PremiumThinkingCollect />
    </Stack>
  );
}
