import { boxColor, shadowColor } from "@/components/Themes/color";
import { Stack } from "@mui/material";
import { styled } from "@mui/material/styles";

export const SurveyBox = styled(Stack)({
    width: "750px",
    height: "100%",
    margin: "auto",
    backgroundColor: `${boxColor}`,
    borderRadius: "15px",
    boxShadow: `0px 0px 20px 1px ${shadowColor.mid}`,
    padding: '50px',
    marginBottom: '50px',
})