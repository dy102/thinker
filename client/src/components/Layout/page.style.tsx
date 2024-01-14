import { Stack } from "@mui/material";
import styled, { keyframes } from "styled-components";

export const marquee = keyframes`
    from {
        transform: translateX(0);
    }
    to {
        transform: translateX(-50%);
    }
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
export const Content = styled(Stack)`
  &:hover {
    @media (hover: hover) and (min-width: 1000px) {
      transform: translateY(calc(100% - 8rem));
    }
  }
`;
