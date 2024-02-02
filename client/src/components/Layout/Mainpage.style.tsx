import { Stack } from "@mui/material";
import { styled } from "@mui/material/styles";

export const LeftTrapezoid = styled(Stack)(
  ({ imagesrc }: { imagesrc: string }) => ({
    backgroundImage: `url(${imagesrc})`,
    backgroundRepeat: "no-repeat",
    backgroundSize: "100% 95vh",
  })
);

export const RightTrapezoid = styled(Stack)(
  ({ imagesrc }: { imagesrc: string }) => ({
    backgroundImage: `url(${imagesrc})`,
    backgroundRepeat: "no-repeat",
    backgroundSize: "100% 95vh",
  })
);

export const LeftInnerTrapezoid = styled(Stack)({
  transition: "all .3s ease-in-out",
  ":hover": {
    transform: "translateY(-20px)",
  },
});

export const RightInnerTrapezoid = styled(Stack)({
  transition: "all .3s ease-in-out",
  ":hover": {
    transform: "translateY(-20px)",
  },
});

export const GoToSurvey = styled(Stack)({
  height: "50px",
  display: "flex",
  alignItems: "flex-start",
  justifyContent: "center",
  fontSize: "20px",
  marginTop: "50px",
});

export const GoToThinking = styled(Stack)({
  height: "50px",
  display: "flex",
  alignItems: "flex-end",
  justifyContent: "center",
  fontSize: "20px",
  marginTop: "50px",
});
