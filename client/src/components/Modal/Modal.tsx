import { DialogProps } from "@mui/material";
import { DialogStyle } from "./Modal.style";


function Modal({ children, open, onClose, ...rest }: DialogProps) {
  return (
    <DialogStyle open={open} onClose={onClose} {...rest}>
      {children}
    </DialogStyle>
  );
}

export default Modal;
