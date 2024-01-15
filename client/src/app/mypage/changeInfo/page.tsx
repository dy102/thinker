"use client";
import { Stack } from "@mui/material";
import {
  InfoTable,
  InfoTables,
  Infoheader,
  InnerStack,
  MemberInfo,
  YourInfo,
} from "./changeInfo.style";
import EditIcon from "@mui/icons-material/Edit";
import Button from "@/components/Button/Button";
import IconButton from "@/components/Button/IconButton";

function page() {
  const memberDataReqeust = {
    customId: "hjs0814",
    pw: "hjs08140814",
    nickname: "jsjsjsjs",
    year: 2000,
    month: 8,
    day: 14,
    gender: "남",
  };
  const memberDataReqeustArr = [memberDataReqeust];
  return (
    <Stack>
      <MemberInfo>
        <Infoheader>Info</Infoheader>
        <Stack flexDirection={"column"} marginTop={"10px"}>
          <InfoTables>
            <InnerStack>
              <InfoTable>NICKNAME</InfoTable>
              <YourInfo>{memberDataReqeust.nickname}</YourInfo>
            </InnerStack>
            <Stack>
              <IconButton>
                <EditIcon />
              </IconButton>
            </Stack>
          </InfoTables>
          <InfoTables>
            <InnerStack>
              <InfoTable>ID</InfoTable>
              <YourInfo>{memberDataReqeust.customId}</YourInfo>
            </InnerStack>
            <Stack>
              <IconButton>
                <EditIcon />
              </IconButton>
            </Stack>
          </InfoTables>
          <InfoTables>
            <InnerStack>
              <InfoTable>PASSWORD</InfoTable>
              <YourInfo>{memberDataReqeust.pw}</YourInfo>
            </InnerStack>
            <Stack>
              <IconButton>
                <EditIcon />
              </IconButton>
            </Stack>
          </InfoTables>
          <InfoTables borderBottom="10px solid black">
            <InnerStack>
              <InfoTable>GENDER</InfoTable>
              <YourInfo>{memberDataReqeust.gender}</YourInfo>
            </InnerStack>
            <Stack>
              <IconButton>
                <EditIcon />
              </IconButton>
            </Stack>
          </InfoTables>
        </Stack>
      </MemberInfo>
    </Stack>
  );
}
export default page;
