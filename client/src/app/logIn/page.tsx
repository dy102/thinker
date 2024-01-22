"use client";
import { Stack, TextField } from "@mui/material";
import Button from "@/components/Button/Button";
import PageLink from "@/components/PageLink/PageLink";

function page() {
  return (
    <Stack width="400px" margin="auto" spacing={3} marginTop={"25vh"}>
      <TextField sx={{ color: "#765AFF" }} label="ID" variant="standard" />
      <TextField label="PASSWARD" variant="standard" />
      <Stack width={"400px"}>
        <Button sx={{ color: "#765AFF" }}>
          <PageLink href={"/"}>LOG_IN</PageLink>
        </Button>
      </Stack>
      <Stack
        fontSize={"14px"}
        flexDirection={"row"}
        justifyContent={"flex-end"}
      >
        <Stack marginRight={"10px"}>Not a member yet?</Stack>
        <PageLink href={"/signIn"}>Register Now</PageLink>
      </Stack>
    </Stack>
  );
}

export default page;
