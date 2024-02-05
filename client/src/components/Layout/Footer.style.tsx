import { Stack } from "@mui/material";
import { styled } from "@mui/material/styles";
import { mainColor } from "../Themes/color";

export const FooterTag = styled(Stack)({
  width: "100%",
  bottom: 0,
  margin: "auto",
  marginTop: "50px",
  height: "70px",
  alignItems: "center",
  justifyContent: "center",
  flexDirection: "row",
  borderTop: `1px solid ${mainColor}`,
});
