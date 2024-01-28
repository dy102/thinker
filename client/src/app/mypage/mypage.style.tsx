import { boxColor, mainColor, shadowColor } from "@/components/Themes/color";
import { LinearProgress, Stack } from "@mui/material";
import { styled } from "@mui/material/styles";

export const Profile = styled(Stack)({
  width: "750px",
  height: "100%",
  margin: "auto",
  backgroundColor: `${boxColor}`,
  borderRadius: "15px",
  boxShadow: `0px 0px 20px 1px red`,
});

export const Grade = styled(Stack)({
  width: "750px",
  height: "100%",
  margin: "auto",
  marginTop: "50px",
  backgroundColor: `${boxColor}`,
  boxShadow: `0px 0px 20px 1px ${shadowColor.mid}`,
  borderRadius: "15px",
  flexDirection: "row",
});

export const GradeGage = styled(Stack)({
  flexDirection: "column",
  justifyContent: "flex-start",
  width: "500px",
  marginTop: "90px",
});

export const BorderLinear = styled(LinearProgress)({
  height: "20px",
  borderRadius: "5px",
  border: "1px solid gray",
  backgroundColor: `${boxColor}`,
  ".MuiLinearProgress-barColorPrimary": {
    backgroundColor: `${mainColor}`,
  },
});

export const SubProfile = styled(Stack)({
  backgroundColor: `${boxColor}`,
  width: "750px",
  height: "100%",
  borderRadius: "15px",
  margin: "auto",
  marginTop: "50px",
  boxShadow: `0px 0px 20px 1px ${shadowColor.mid}`,
});

export const MyRecord = styled(Stack)({
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
});
