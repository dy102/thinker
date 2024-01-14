import { LinearProgress, Stack } from "@mui/material";
import { styled } from "@mui/material/styles";

export const Profile = styled(Stack)({
  width: "750px",
  height: "480px",
  margin: "auto",
  padding: "50px",
  backgroundColor: "rgb(217,217,217)",
  borderRadius: "15px",
  flexDirection: "column",
  alignItems: "space-between",
});

export const Grade = styled(Stack)({
  flexDirection: "row",
});

export const GradeGage = styled(Stack)({
  flexDirection: "column",
  justifyContent: "flex-start",
  width: "500px",
  marginTop: "100px",
});

export const BorderLinear = styled(LinearProgress)({
  height: "20px",
  borderRadius: "5px",
  border: "1px solid gray",
  backgroundColor: "rgb(217,217,217)",
  ".MuiLinearProgress-barColorPrimary": {
    backgroundColor: "#765AFF",
  },
});
