'use client'
import { QuillWrapper } from "@/components/ReactQuill/ReactQuill";
import { Stack, TextField } from "@mui/material";

function Write() {

    const modules = {
        toolbar: [
            [{ header: '1' }, { header: '2' }, { font: [] }],
            [{ size: [] }],
            ['bold', 'italic', 'underline', 'strike', 'blockquote'],
            [
                { list: 'ordered' },
                { list: 'bullet' },
                { indent: '-1' },
                { indent: '+1' },
            ],
            ['link', 'image', 'video'],
            ['clean'],
        ],
        clipboard: {
            // toggle to add extra line breaks when pasting HTML:
            matchVisual: false,
        },
    }
    const formats = [
        'header',
        'font',
        'size',
        'bold',
        'italic',
        'underline',
        'strike',
        'blockquote',
        'list',
        'bullet',
        'indent',
        'link',
        'image',
        'video',
    ]

    return (
        <Stack margin={'100px auto'}>
            <TextField sx={{ width: '70%' }} size="medium" label='제목을 입력하세요' variant="standard"></TextField>

            <QuillWrapper modules={modules} formats={formats} theme="snow" />
        </Stack>
    )
}

export default Write