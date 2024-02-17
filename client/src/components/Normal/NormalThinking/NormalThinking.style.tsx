import { mainColor } from "@/components/Themes/color";
import { Stack, styled } from "@mui/material";
import Link from "next/link";

export const NormalThinkingBox = styled(Stack)({
  width: "541px",
  height: "250px",
  borderBottom: "1px solid #eee",
  flexDirection: "row",
  ":nth-of-type(2n-1)": {
    borderRight: "1px solid #eee",
  },
});

export const NormalThinkingThumbnail = styled(Stack)(
  ({ imagesrc }: { imagesrc: string | null }) => ({
    backgroundImage:
      imagesrc === null
        ? "url()"
        : `url(${imagesrc})`,
    backgroundRepeat: "no-repeat",
    backgroundSize: "150px 150px",
    width: "150px",
    height: "150px",
    margin: "50px 20px 0 0",
    borderRadius: '15px',
  })
);

export const ThinkingTitle = styled(Link)({
  fontSize: "25px",
  marginBottom: "60px",
  textDecoration: "none",
  color: `${mainColor}`,
});

export const NormalThinkingNonThumbnail = styled(Stack)({
  backgroundImage: 'url(https://ineducationonline.org/wp-content/uploads/2021/04/Importance-of-critical-thinking-Zealousness-Q1-2021.jpg)',
  backgroundRepeat: "no-repeat",
  backgroundSize: "150px 150px",
  width: "150px",
  height: "150px",
  margin: "50px 20px 0 0",
  borderRadius: '15px',
});
