"use client";
import { Stack } from "@mui/material";
import { BorderLinear, Grade, GradeGage, Profile } from "./mypage.style";

function page() {
  const memberDataDto = {
    customId: "string",
    pw: "string",
    nickname: "jsjsjsjs",
    brithday: "string",
    gender: "string",
    point: 3500,
    grade: "thinker",
    gradeValue: 60,
  };
  const imageData = "/profileImg.jpg";
  return (
    <Stack marginTop={"100px"}>
      <Profile>
        <Stack flexDirection={"row"}>
          <Stack
            width={"200px"}
            height={"200px"}
            borderRadius={"50%"}
            bgcolor={"#765AFF"}
            margin={"50px"}
          >
            <img
              style={{ borderRadius: "50%" }}
              src="https://png.pngtree.com/png-vector/20191115/ourmid/pngtree-beautiful-profile-line-vector-icon-png-image_1990469.jpg"
            />
            {/*[FIXME] 나중에 image 태그로 바꿔야함*/}
            {/* <Image width={70} height={70} src={"/profileImg.jpg"} alt="" /> */}
          </Stack>
          <Stack margin={"120px 110px 0 0px"} fontSize={"17px"} spacing={8}>
            <Stack>NICKNAME</Stack>
            <Stack>POINT</Stack>
          </Stack>
          <Stack margin={"90px 0 0 0"} fontSize={"30px"} spacing={8}>
            <Stack>{memberDataDto.nickname}</Stack>
            <Stack>{memberDataDto.point}</Stack>
          </Stack>
        </Stack>
        <Grade>
          <Stack margin={"50px"}>
            {/*[FIXME] 나중에 image 태그로 바꿔야함*/}
            <img
              style={{ width: "100px", height: "100px" }}
              src="https://opgg-com-image.akamaized.net/attach/images/20190916020813.596917.jpg"
            />
          </Stack>
          <GradeGage spacing={1}>
            <Stack textAlign={"right"}>{memberDataDto.gradeValue}%</Stack>
            <BorderLinear
              variant="determinate"
              value={memberDataDto.gradeValue}
            ></BorderLinear>
          </GradeGage>
        </Grade>
      </Profile>

      <Stack
        bgcolor={"rgb(217,217,217)"}
        width={"60%"}
        height={"200px"}
        borderRadius={"15px"}
        margin={"auto"}
        marginTop={"40px"}
      ></Stack>
      <Stack
        bgcolor={"rgb(217,217,217)"}
        width={"60%"}
        height={"200px"}
        borderRadius={"15px"}
        margin={"auto"}
        marginTop={"40px"}
      ></Stack>
    </Stack>
  );
}
export default page;
