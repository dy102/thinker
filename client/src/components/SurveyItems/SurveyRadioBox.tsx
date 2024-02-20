import { Checkbox, FormControlLabel } from "@mui/material";
import { mainColor } from "../Themes/color";


export default function RadioLabel({ item, value }: { item: string; value: string }) {
    return <FormControlLabel
        value={value}
        control={<Checkbox />}
        label={item}
        labelPlacement="end"
    />;
}
