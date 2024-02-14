import { mainColor } from "@/components/Themes/color";
import { Stack, styled } from "@mui/material";
import Link from "next/link";

export const NormalThinkingBox = styled(Stack)({
  width: "541px",
  height: "250px",
  borderBottom: "1px solid #eee",
  flexDirection: "row",
  ":nth-child(2n-1)": {
    borderRight: "1px solid #eee",
  },
});

export const NormalThinkingThumbnail = styled(Stack)(
  ({ imagesrc }: { imagesrc: string | null }) => ({
    backgroundImage:
      imagesrc === null
        ? "url(https://t4.ftcdn.net/jpg/00/93/83/93/240_F_93839373_JT5cpdRmTyuOg6eert4Kexq8aO63iD5r.jpg)"
        : `url(${imagesrc})`,
    backgroundRepeat: "no-repeat",
    backgroundSize: "150px 150px",
    width: "150px",
    height: "150px",
    margin: "50px 20px 0 0",
  })
);

export const ThinkingTitle = styled(Link)({
  fontSize: "25px",
  marginBottom: "60px",
  textDecoration: "none",
  color: `${mainColor}`,
});

export const NormalThinkingNonThumbnail = styled(Stack)({
  backgroundColor: "teal",
  width: "150px",
  height: "150px",
  margin: "50px 20px 0 0",
});
