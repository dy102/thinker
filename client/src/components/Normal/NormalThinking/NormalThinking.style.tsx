import { Stack, styled } from "@mui/material";
import Link from "next/link";

export const NormalThinkingBox = styled(Link)({
  width: "541px",
  height: "250px",
  borderBottom: "1px solid #eee",
  flexDirection: "row",
  ":nth-child(2n-1)": {
    borderRight: "1px solid #eee",
  },
});

export const NormalThinkingThumbnail = styled(Stack)(
  ({ imagesrc }: { imagesrc: string }) => ({
    backgroundImage: `url(${imagesrc})`,
    backgroundRepeat: "no-repeat",
    backgroundSize: "150px 150px",
    width: "150px",
    height: "150px",
    margin: "50px 20px 0 0",
  })
);

export const NormalThinkingNonThumbnail = styled(Stack)({
  backgroundColor: "teal",
  width: "150px",
  height: "150px",
  margin: "50px 20px 0 0",
});
