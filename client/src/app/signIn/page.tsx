"use client";
import {
  FormControlLabel,
  FormLabel,
  Paper,
  Radio,
  RadioGroup,
  Stack,
  Step,
  StepContent,
  StepLabel,
  Stepper,
  TextField,
} from "@mui/material";
import Button from "@/components/Button/Button";
import PageLink from "@/components/PageLink/PageLink";
import { mainColor } from "@/components/Themes/color";
import React from "react";
import { Box, LastTypography, SignInBox, Typography } from "./signIn.style";

function Page() {
  const [activeStep, setActiveStep] = React.useState(0);

  const handleNext = () => {
    setActiveStep((prevActiveStep) => prevActiveStep + 1);
  };
  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  {
    /*[FIXME : 백엔드 연동 시 mui 참고해서 value 값 가져오기. 마지막 finish 버튼에서 post 해야함]*/
  }
  return (
    <Stack width="500px" margin="auto" spacing={3} marginTop={"150px"}>
      <Stack textAlign={"center"} fontSize={"50px"} color={`${mainColor}`}>
        Welcome!
      </Stack>
      <Box>
        <Stepper activeStep={activeStep} orientation="vertical">
          <Step>
            <StepLabel>Make Account</StepLabel>
            <StepContent>
              <Typography spacing={2}>
                <TextField
                  fullWidth
                  required
                  label="LOGIN_ID"
                  variant="standard"
                />
                <TextField
                  fullWidth
                  required
                  label="LOGIN_PW"
                  variant="standard"
                />
              </Typography>
              <SignInBox>
                <Button sx={{ width: "120px" }} onClick={handleNext}>
                  {"Continue"}
                </Button>
              </SignInBox>
            </StepContent>
          </Step>
          <Step>
            <StepLabel>Choose Your NickName</StepLabel>
            <StepContent>
              <Typography>
                <TextField required label="USERNAME" variant="standard" />
              </Typography>
              <SignInBox>
                <Button
                  sx={{ width: "120px", marginRight: "10px" }}
                  onClick={handleNext}
                >
                  Continue
                </Button>
                <Button sx={{ width: "120px" }} onClick={handleBack}>
                  Back
                </Button>
              </SignInBox>
            </StepContent>
          </Step>
          <Step>
            <StepLabel>Choose Your Privacy</StepLabel>
            <StepContent>
              <Typography spacing={3}>
                <TextField label="AGE" variant="standard" />
                <Stack>
                  <FormLabel>Gender</FormLabel>
                  <RadioGroup row>
                    <FormControlLabel
                      value="man"
                      control={<Radio />}
                      label="Male"
                      sx={{
                        "&.Mui-checked": {
                          color: `${mainColor}`,
                        },
                      }}
                    />
                    <FormControlLabel
                      value="women"
                      control={<Radio />}
                      label="Female"
                    />
                  </RadioGroup>
                </Stack>
              </Typography>
              <SignInBox marginTop={"30px"}>
                <Button
                  sx={{ width: "120px", marginRight: "10px" }}
                  onClick={handleNext}
                >
                  Finish
                </Button>
                <Button sx={{ width: "120px" }} onClick={handleBack}>
                  Back
                </Button>
              </SignInBox>
            </StepContent>
          </Step>
        </Stepper>
        {activeStep === 3 && (
          <Paper square elevation={0} sx={{ p: 3 }}>
            <LastTypography>All steps completed</LastTypography>
            <Stack width={"500px"}>
              <Button>
                <PageLink href={"/"}>Create Account</PageLink>
              </Button>
            </Stack>
          </Paper>
        )}
      </Box>
    </Stack>
  );
}

export default Page;
