import { Stack, backdropClasses } from "@mui/material";
import { styled } from "@mui/material/styles";
import { relative } from "path";

export const HeaderTag = styled(Stack)(() => ({
  backgroundColor: "rgb(240, 240, 240)",
  width: "100%",
  height: "70px",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  flexDirection: "row",
}));

export const HeaderMainTag = styled(Stack)(() => ({
  backgroundColor: "rgb(240, 240, 240)",
  width: "100%",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  flexDirection: "row",
}));

export const SearchTag = styled(Stack)(() => ({
  backgroundColor: "#D9D9D9",
  width: "55%",
  height: "45px",
  borderRadius: "20px",
}));

export const LogoTag = styled(Stack)(() => ({
  width: "45px",
  height: "45px",
  backgroundColor: "#765AFF",
}));
