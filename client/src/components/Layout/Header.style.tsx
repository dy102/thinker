import { Stack } from "@mui/material";
import { styled } from "@mui/material/styles";
import { mainColor } from "../Themes/color";

export const HeaderTag = styled(Stack)({
  width: "100%",
  margin: "auto",
  height: "70px",
  alignItems: "center",
  justifyContent: "center",
  flexDirection: "row",
});

export const HeaderMainTag = styled(Stack)({
  width: "80%",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  flexDirection: "row",
});

export const LogoTag = styled(Stack)({
  width: "45px",
  height: "45px",
  backgroundColor: `${mainColor}`,
  margin: "0 5px",
  alignItems: "center",
  justifyContent: "center",
});

export const SearchTag = styled(Stack)({
  backgroundColor: 'none',
  width: "50%",
  height: "40px",
  borderRadius: "15px",
  margin: "0 5px",
});

export const SurveyTag = styled(Stack)({
  width: "110px",
  height: "40px",
  margin: "0 5px",
});

export const ThinkingTag = styled(Stack)({
  width: "110px",
  height: "40px",
  margin: "0 5px",
});

export const LogInTag = styled(Stack)({
  width: "80px",
  height: "40px",
  color: `${mainColor}`,
  margin: "0 5px",
});

export const SignInTag = styled(Stack)({
  height: "40px",
  color: `${mainColor}`,
  margin: "0 5px",
});

