import { mainColor, shadowColor } from "@/components/Themes/color";
import { Stack, styled } from "@mui/material";

export const ThinkingyBox = styled(Stack)({
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

export const ThinkingThumbnail = styled(Stack)(
  ({ imagesrc }: { imagesrc: string }) => ({
    backgroundImage: `url(${imagesrc})`,
    backgroundRepeat: "no-repeat",
    backgroundSize: "300px 200px",
    borderTopLeftRadius: "15px",
    borderTopRightRadius: "15px",
  })
);

export const PopularCount = styled(Stack)({
  flexDirection: "row",
  alignItems: "center",
  justifyContent: "center",
  marginRight: "10px",
});
