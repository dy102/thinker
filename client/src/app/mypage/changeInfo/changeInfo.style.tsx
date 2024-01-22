import { boxColor, mainColor, shadowColor } from "@/components/Themes/color";
import { Stack } from "@mui/material";
import { styled } from "@mui/material/styles";

export const MemberInfo = styled(Stack)({
  width: "750px",
  height: "100%",
  margin: "auto",
  backgroundColor: `${boxColor}`,
  borderRadius: "15px",
  boxShadow: `0px 0px 20px 1px ${shadowColor.mid}`,
  marginTop: "100px",
});

export const Infoheader = styled(Stack)({
  color: `${mainColor}`,
  fontSize: "20px",
  height: "50px",
  borderBottom: `1px solid ${mainColor}`,
  display: "flex",
  justifyContent: "center",
  alignItems: "flex-start",
  paddingLeft: "35px",
});

export const InfoTables = styled(Stack)({
  flexDirection: "row",
  justifyContent: "space-between",
  alignItems: "center",
  height: "50px",
  margin: "0 35px",
  borderBottom: `1px solid ${shadowColor.dark}`,
});

export const InnerStack = styled(Stack)({
  width: "370px",
  flexDirection: "row",
  justifyContent: "space-between",
});

export const InfoTable = styled(Stack)({
  color: `${mainColor}`,
});

export const YourInfo = styled(Stack)({
  textAlign: "left",
});
