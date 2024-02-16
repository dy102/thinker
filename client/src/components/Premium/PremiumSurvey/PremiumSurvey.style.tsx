import { mainColor, shadowColor } from "@/components/Themes/color";
import { Stack } from "@mui/material";
import { styled } from "@mui/material/styles";

export const SurveyBox = styled(Stack)({
  width: "300px",
  height: "320px",
  margin: "20px",
  borderRadius: "15px",
  boxShadow: `0px 0px 20px 1px ${shadowColor.main}`,
  border: `0px solid ${mainColor}`,
  transition: "all .35s ease-in-out",
  ":hover": {
    transform: "translateY(-10px)",
    boxShadow: `0px 0px 20px 1px ${mainColor}`,
    border: `1px solid ${mainColor}`,
  },
});

export const SurveyThumbnail = styled(Stack)(
  ({ imagesrc }: { imagesrc: string | null }) => ({
    backgroundImage: `url(${imagesrc})`,
    backgroundRepeat: "no-repeat",
    backgroundSize: "300px 200px",
    borderTopLeftRadius: "15px",
    borderTopRightRadius: "15px",
  })
);
