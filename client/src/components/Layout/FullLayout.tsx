"use client";
import { Stack } from "@mui/material";
import { styled } from "@mui/material/styles";

type Children = {
  children: React.ReactNode;
};

const MainStack = styled(Stack)({
  width: "100%",
  maxWidth: "1440px",
  height: "auto",
  margin: "auto",
  minHeight: "100vh",
  backgroundColor: "rgb(245, 245, 245)",
});

export default function FullLayout({ children }: Children) {
  return <MainStack>{children}</MainStack>;
}
