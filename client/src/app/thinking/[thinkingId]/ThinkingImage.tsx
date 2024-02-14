import { Stack, styled } from "@mui/material";

const ThinkingImage = styled(Stack)(
  ({ imagesrc }: { imagesrc: string | null }) => ({
    backgroundImage: `url(${imagesrc})`,
    backgroundRepeat: "no-repeat",
    backgroundSize: "400px 300px",
    borderRadius: "15px",
    width: imagesrc === null ? "0px" : "400px",
    height: imagesrc === null ? "0px" : "300px",
  })
);

export default function ThinkingImageTag({
  ImageSrc,
}: {
  ImageSrc: string | null;
}) {
  return (
    <ThinkingImage imagesrc={ImageSrc} marginRight={"30px"}></ThinkingImage>
  );
}
