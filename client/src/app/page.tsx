"use client";
import { AnimatedTitle, Content, Track } from "@/components/Layout/page.style";
import { Stack } from "@mui/material";

export default function Home() {
  return (
    <>
      <Stack height={"200px"} marginTop={"40px"}>
        <Stack
          fontSize={"100px"}
          fontWeight={"bolder"}
          margin={"auto"}
          color={"#765AFF"}
        >
          THINKER
        </Stack>
        <AnimatedTitle>
          <Track>
            <Content>
              <Stack>
                나만의 설문조사를 만들어보세요 평소의 생각들, 시사, 경제 등
                다양한 주제들을 THINKING에 적어주세요 설문조사에 참여해 포인트를
                쌓아 프리미엄 설문조사, THINKING으로 만들어보세요 다양한
                정보들과 설문조사들이 넘처나는 THINKER 입니다!!
              </Stack>
            </Content>
          </Track>
        </AnimatedTitle>
      </Stack>
    </>
  );
}
