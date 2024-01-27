import { Stack } from "@mui/material";
import styled, { keyframes } from "styled-components";
import { mainColor } from "../Themes/color";

export const marquee = keyframes`
    from {
        transform: translateX(0);
    }
    to {
        transform: translateX(-50%);
    }
`;

export const trackingInContract = keyframes`
0% {
  letter-spacing: -0.5em;
  opacity: 0;
}
40% {
  opacity: 0.6;
}
100% {
  opacity: 1;
}
`;

export const leftToRight = keyframes`
0%{
  transform: translateX(0);
  opacity: 0;
}
100%{
  transform: translateX(100px);
  opacity: 1;
}
`;

export const Thinker = styled(Stack)`
  fontweight: bolder;
  margin: auto;
  color: ${mainColor};
  animation: ${trackingInContract} 0.7s cubic-bezier(0.25, 0.46, 0.45, 0.94)
    both;
  fontsize: 100px;
`;

export const AnimatedTitle = styled.div`
  font-size: 25px;
  font-family: "Raleway", Sans-serif;
  font-weight: 300;
  position: relative;
  width: 100%;
  max-width: 100%;
  height: auto;
  padding: 50px 0;
  overflow-x: hidden;
  overflow-y: hidden;
`;

export const Track = styled(Stack)`
  position: absolute;
  white-space: nowrap;
  will-change: transform;
  animation: ${marquee} 20s linear infinite;
`;
export const Content = styled(Stack)``;

export const ServeyCollect = styled(Stack)({
  display: "flex",
  flexWrap: "wrap",
});

export const IntroComment = styled(Stack)({
  width: "55%",
  height: "100%",
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  fontSize: "20px",
});

export const GoToSurvey = styled(Stack)({
  height: "50px",
  display: "flex",
  alignItems: "flex-end",
  justifyContent: "center",
  fontSize: "20px",
  position: "absolute",
  bottom: "100px",
});

export const GoToThinking = styled(Stack)({
  height: "50px",
  display: "flex",
  alignItems: "flex-start",
  justifyContent: "center",
  fontSize: "20px",
  marginTop: "50px",
  position: "absolute",
  right: "100px",
});
