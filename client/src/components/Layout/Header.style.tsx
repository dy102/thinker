import { Stack } from "@mui/material";
import { styled } from "@mui/material/styles";

export const HeaderTag = styled(Stack)({
  width: "100%",
  maxWidth: "1440px",
  margin: "auto",
  height: "70px",
  alignItems: "center",
  justifyContent: "center",
  flexDirection: "row",
  borderBottom: "1px solid #765AFF",
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
  backgroundColor: "#765AFF",
  margin: "0 5px",
  alignItems: "center",
  justifyContent: "center",
});

export const SearchTag = styled(Stack)({
  backgroundColor: "#D9D9D9",
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
  backgroundColor: "rgb(245, 245, 245)",
  width: "80px",
  height: "40px",
  color: "#765AFF",
  margin: "0 5px",
});

export const SignInTag = styled(Stack)({
  backgroundColor: "rgb(245, 245, 245)",
  height: "40px",
  color: "#765AFF",
  margin: "0 5px",
});
