import { mainColor } from "@/components/Themes/color";
import { styled } from "@mui/material/styles";
import { Stack } from "@mui/system";

export const SignInBox = styled(Stack)({
  flexDirection: "row",
  justifyContent: "center",
  width: "100%",
});

export const Typography = styled(Stack)({
  height: "150px",
  flexDirection: "column",
  justifyContent: "center",
});

export const LastTypography = styled(Stack)({
  margin: "20px 0 30px 0",
  fontSize: "20px",
  color: `${mainColor}`,
});

export const Box = styled(Stack)({
  width: "500px",
  marginTop: "100px",
});
