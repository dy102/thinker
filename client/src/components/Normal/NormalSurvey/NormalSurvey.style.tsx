import { mainColor } from "@/components/Themes/color";
import { Link, Stack, styled } from "@mui/material";

export const NormalSurveyBox = styled(Stack)({
  width: "541px",
  height: "250px",
  borderBottom: "1px solid #eee",
  flexDirection: "row",
  ":nth-of-type(2n-1)": {
    borderRight: "1px solid #eee",
  },
});

export const SurveyTitle = styled(Link)({
  fontSize: "25px",
  marginBottom: "20px",
  textDecoration: "none",
  color: `${mainColor}`,
});

export const NormalSurveyThumbnail = styled(Stack)(
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
    borderRadius: '15px',
  })
);

export const NonNormalSurveyThumbnail = styled(Stack)({
  backgroundImage: 'url(https://t4.ftcdn.net/jpg/00/93/83/93/240_F_93839373_JT5cpdRmTyuOg6eert4Kexq8aO63iD5r.jpg)',
  backgroundRepeat: "no-repeat",
  backgroundSize: "150px 150px",
  width: "150px",
  height: "150px",
  margin: "50px 20px 0 0",
  borderRadius: '15px',
});
