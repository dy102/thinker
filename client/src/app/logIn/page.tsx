"use client";
import {
  FormControl,
  IconButton,
  Input,
  InputAdornment,
  InputLabel,
  Stack,
  TextField,
} from "@mui/material";
import Button from "@/components/Button/Button";
import PageLink from "@/components/PageLink/PageLink";
import React from "react";
import { Visibility, VisibilityOff } from "@mui/icons-material";

function page() {
  const [showPassword, setShowPassword] = React.useState(false);

  const handleClickShowPassword = () => setShowPassword((show) => !show);

  const handleMouseDownPassword = (
    event: React.MouseEvent<HTMLButtonElement>
  ) => {
    event.preventDefault();
  };
  return (
    <Stack width="400px" margin="auto" spacing={3} marginTop={"25vh"}>
      <TextField label="ID" variant="standard" />
      <FormControl sx={{ m: 1, width: "100%" }} variant="standard">
        <InputLabel htmlFor="standard-adornment-password">PASSWORD</InputLabel>
        <Input
          id="standard-adornment-password"
          type={showPassword ? "text" : "password"}
          endAdornment={
            <InputAdornment position="end">
              <IconButton
                aria-label="toggle password visibility"
                onClick={handleClickShowPassword}
                onMouseDown={handleMouseDownPassword}
              >
                {showPassword ? <VisibilityOff /> : <Visibility />}
              </IconButton>
            </InputAdornment>
          }
        />
      </FormControl>
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
