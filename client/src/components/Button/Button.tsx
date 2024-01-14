'use client';

import React from 'react';
import { ButtonProps, Button as MuiButton, styled } from '@mui/material';

export const ButtonStyled = styled(MuiButton)({
    boxShadow: 'none',
    color: '#765AFF',
    borderRadius: '15px',
    border: '1px solid #765AFF',
    backgroundColor: 'rgb(245, 245, 245)',
    overflow: 'auto',
    ':hover': {
        backgroundColor: 'rgba(118,90,255,0.3)',
        transition: 'background-color 0.5s ease, border 0.5s ease',
        boxShadow: '0px 0px 0px rgba(0, 0, 0, 0)',
    },
    ':active': {
        backgroundColor: 'rgba(118,90,255,0.6)',
        transition: 'background-color .1s ease, border 0.5s ease',
        boxShadow: '0px 0px 0px rgba(0, 0, 0, 0)',
    },
});

const Button = ({ children, variant, color, ...rest }: ButtonProps) => {
    return (
        <ButtonStyled variant={variant} disableRipple color={color} {...rest}>
            {children}
        </ButtonStyled>
    );
};

export default Button;
