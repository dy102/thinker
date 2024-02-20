'use client'
import { Stack } from "@mui/material";
import MDEditor from "@uiw/react-md-editor";
import { useState } from "react";

function Write() {

    const [content, setContent] = useState<string | undefined>('');

    return (
        <Stack>
            <MDEditor height={'68vh'} value={content} onChange={setContent} />
        </Stack>
    )
}

export default Write