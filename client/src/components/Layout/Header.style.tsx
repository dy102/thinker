import { Stack } from "@mui/material";
import { styled } from "@mui/material/styles";
import { mainColor } from "../Themes/color";

export const HeaderTag = styled(Stack)({
  width: "100%",
  margin: "auto",
  height: "70px",
  alignItems: "center",
  justifyContent: "center",
  flexDirection: "row",
  borderBottom: `1px solid ${mainColor}`,
});

export const HeaderMainTag = styled(Stack)({
  width: "80%",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  flexDirection: "row",
});

export const LogoTag = styled(Stack)({
  width: "45px",
  height: "45px",
  backgroundColor: `${mainColor}`,
  margin: "0 5px",
  alignItems: "center",
  justifyContent: "center",
});

export const SearchTag = styled(Stack)({
  backgroundColor: "#D9D9D9",
  width: "50%",
  height: "40px",
  borderRadius: "15px",
  margin: "0 5px",
});

export const SurveyTag = styled(Stack)({
  width: "110px",
  height: "40px",
  margin: "0 5px",
});

export const ThinkingTag = styled(Stack)({
  width: "110px",
  height: "40px",
  margin: "0 5px",
});

export const LogInTag = styled(Stack)({
  width: "80px",
  height: "40px",
  color: `${mainColor}`,
  margin: "0 5px",
});

export const SignInTag = styled(Stack)({
  height: "40px",
  color: `${mainColor}`,
  margin: "0 5px",
});

export const LeftTrapezoid = styled(Stack)(
  ({ imageSrc }: { imageSrc: string }) => ({
    backgroundImage: `url(${imageSrc})`,
    backgroundSize: "100% 500px",
    backgroundRepeat: "no-repeat",
    width: "100%",
  })
);

export const RightTrapezoid = styled(Stack)(
  ({ imageSrc }: { imageSrc: string }) => ({
    backgroundImage: `url(${imageSrc})`,
    borderBottom: `500px solid ${mainColor}`,
    borderLeft: "250px solid transparent",
    backgroundSize: "100% 500px",
    backgroundRepeat: "no-repeat",
    width: "40%",
    position: "absolute",
    right: 0,
  })
);

export const LeftInnerTrapezoid = styled(Stack)({
  borderTop: "200px solid rgba(128,128,128, 0.5)",
  borderRight: "100px solid transparent",
  width: "50%",
  margin: "auto 0",
  position: "absolute",
  top: "150px",
  transition: "all .35s ease-in-out",
  ":hover": {
    transform: "translateY(-20px)",
  },
});

export const RightInnerTrapezoid = styled(Stack)({
  borderBottom: `200px solid rgba(128,128,128, 0.5)`,
  borderLeft: "100px solid transparent",
  width: "111%",
  margin: "auto 0",
  position: "absolute",
  right: 0,
  top: "150px",
  transition: "all .35s ease-in-out",
  ":hover": {
    transform: "translateY(-20px)",
  },
});
