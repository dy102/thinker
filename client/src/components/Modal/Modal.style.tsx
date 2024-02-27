import {
  Dialog as MuiDialog,
  DialogActions,
  DialogContent,
  DialogTitle,
} from "@mui/material";
import { styled } from "@mui/material/styles";
import { mainColor } from "../Themes/color";

export const ModalTitle = styled(DialogTitle)({
  "&&.MuiDialogTitle-root": {
    backgroundColor: mainColor,
    padding: "16px 8px 0px 8px",
    fontSize: "22px",
    fontWeight: "bold",
  },
});

export const ModalContent = styled(DialogContent)({
  "&&.MuiDialogContent-root": {
    backgroundColor: mainColor,
    padding: "20px 8px",
  },
});

export const ModalActions = styled(DialogActions)({
  "&&.MuiDialogActions-root": { width: "100%", backgroundColor: mainColor },
});

export const DialogStyle = styled(MuiDialog)({
  "& .MuiDialog-paper": {
    padding: "10px 40px 20px 40px",
    backgroundImage: "none",
    backgroundColor: mainColor,
    border: `1px solid ${mainColor}`,
  },
});
