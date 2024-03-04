import {
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  styled,
} from "@mui/material";
import { boxColor, mainColor } from "../Themes/color";

export const ModalTitle = styled(DialogTitle)({
  "&&.MuiDialogTitle-root": {
    backgroundColor: boxColor,
    padding: "16px 8px 0px 8px",
    fontSize: "22px",
    fontWeight: "bold",
  },
});

export const ModalContent = styled(DialogContent)({
  "&&.MuiDialogContent-root": {
    backgroundColor: boxColor,
    padding: "50px 60px",
    borderRadius: "15px",
  },
});

export const ModalActions = styled(DialogActions)({
  "&&.MuiDialogActions-root": {
    width: "100%",
    backgroundColor: boxColor,
  },
});

export const DialogStyle = styled(Dialog)({
  "&.MuiDialog-paper": {
    padding: "30px 30px",
    backgroundImage: "none",
    backgroundColor: boxColor,
    border: `2px solid ${mainColor}`,
    margin: "0px",
    borderRadius: "15px",
  },
});
