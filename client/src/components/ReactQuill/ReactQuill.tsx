import React from 'react';
import dynamic from 'next/dynamic'
import 'react-quill/dist/quill.snow.css';
import { Stack } from '@mui/material';

export const QuillWrapper = dynamic(() => import('react-quill'), {
    ssr: false,
    loading: () => <Stack>Loading ...</Stack>,
})