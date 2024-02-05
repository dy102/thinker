"use client";
import { Stack } from "@mui/material";
import {
  BorderLinear,
  Grade,
  GradeGage,
  MyRecord,
  Profile,
  SubProfile,
} from "./mypage.style";
import { mainColor, shadowColor } from "@/components/Themes/color";
import Button from "@/components/Button/Button";
import PageLink from "@/components/PageLink/PageLink";
import Image from "next/image";
import { BorderRight } from "@mui/icons-material";

function page() {
  const ImageStyle = {
    borderRadius: "50%",
    boxShadow: `0px 0px 20px 1px ${shadowColor.main}`,
  };
  const memberDataDto = {
    customId: "string",
    pw: "string",
    nickname: "jsjsjsjs",
    brithday: "string",
    gender: "string",
    point: 3500,
    grade: "THINKER",
    gradeValue: 61,
  };
  const imageData =
    "https://png.pngtree.com/png-vector/20191115/ourmid/pngtree-beautiful-profile-line-vector-icon-png-image_1990469.jpg";
  return (
    <Stack marginTop={"100px"}>
      <Profile>
        <Stack flexDirection={"row"} marginBottom={"50px"}>
          <Stack width={"200px"} height={"150px"} margin={"50px"}>
            {/* <img
              style={{
                borderRadius: "50%",
                boxShadow: `0px 0px 20px 1px ${shadowColor.main}`,
              }}
              src="https://png.pngtree.com/png-vector/20191115/ourmid/pngtree-beautiful-profile-line-vector-icon-png-image_1990469.jpg"
            /> */}
            {/*[FIXME] 나중에 image 태그로 바꿔야함*/}
            <Image
              width={200}
              height={200}
              style={ImageStyle}
              src={`${imageData}`}
              alt=""
            />
          </Stack>
          <Stack marginLeft={"30px"} flexDirection={"column"}>
            <Stack margin={"40px auto 0 auto"} width={"200px"}>
              <Button>
                <PageLink href={"/mypage/changeInfo"}>
                  내 정보 수정하기
                </PageLink>
              </Button>
            </Stack>
            <Stack flexDirection={"row"}>
              <Stack margin={"55px 140px 0 0px"} fontSize={"17px"} spacing={7}>
                <Stack>NICKNAME</Stack>
                <Stack>POINT</Stack>
              </Stack>
              <Stack margin={"25px 0 0 0"} fontSize={"30px"} spacing={7}>
                <Stack>{memberDataDto.nickname}</Stack>
                <Stack>{memberDataDto.point}</Stack>
              </Stack>
            </Stack>
          </Stack>
        </Stack>
      </Profile>
      <Grade>
        <Stack width={"100px"} height={"100px"} margin={"40px"}>
          {/*[FIXME] 나중에 image 태그로 바꿔야함*/}
          {/* <img
            style={{ width: "100px", height: "100px" }}
            src="https://opgg-com-image.akamaized.net/attach/images/20190916020813.596917.jpg"
          /> */}
          <Image
            width={100}
            height={100}
            src={
              "https://opgg-com-image.akamaized.net/attach/images/20190916020813.596917.jpg"
            }
            alt=""
          />
        </Stack>
        <GradeGage>
          <Stack flexDirection={"row"} justifyContent={"space-between"}>
            <Stack
              color={`${mainColor}`}
              fontWeight={"normal"}
              fontSize={"25px"}
            >
              {memberDataDto.grade}
            </Stack>
            <MyRecord>{memberDataDto.gradeValue}%</MyRecord>
          </Stack>
          <BorderLinear
            variant="determinate"
            value={memberDataDto.gradeValue}
          ></BorderLinear>
        </GradeGage>
      </Grade>

      <SubProfile>
        <Stack
          margin={"30px 50px"}
          flexDirection="row"
          justifyContent="space-between"
        >
          <MyRecord>내 활동기록</MyRecord>
          <Stack width={"145px"}>
            <Button>
              <PageLink href={"/mypage/record"}>{`보러가기 >>>`}</PageLink>
            </Button>
          </Stack>
        </Stack>
      </SubProfile>
    </Stack>
  );
}
export default page;
