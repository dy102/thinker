"use client";
import Button from "@/components/Button/Button";
import PageLink from "@/components/PageLink/PageLink";
import { QuillWrapper } from "@/components/ReactQuill/ReactQuill";
import { Stack, TextField } from "@mui/material";

function ThinkingWrite() {
  const modules = {
    toolbar: [
      [{ header: "1" }, { header: "2" }, { font: [] }],
      [{ size: [] }],
      ["bold", "italic", "underline", "strike", "blockquote"],
      [
        { list: "ordered" },
        { list: "bullet" },
        { indent: "-1" },
        { indent: "+1" },
      ],
      ["link", "image", "video"],
      ["clean"],
    ],
    clipboard: {
      // toggle to add extra line breaks when pasting HTML:
      matchVisual: false,
    },
  };
  const formats = [
    "header",
    "font",
    "size",
    "bold",
    "italic",
    "underline",
    "strike",
    "blockquote",
    "list",
    "bullet",
    "indent",
    "link",
    "image",
    "video",
  ];

  return (
    <Stack margin={"100px auto"}>
      <Stack>
        <TextField
          sx={{ width: "70%" }}
          size="medium"
          label="제목을 입력하세요"
          variant="standard"
        ></TextField>

        <QuillWrapper modules={modules} formats={formats} theme="snow" />
      </Stack>
      <Stack marginTop={"75px"} display={"flex"} alignItems={"flex-end"}>
        <Button sx={{ width: "150px" }}>
          <PageLink href={"/thinking"}>제출하기</PageLink>
        </Button>
      </Stack>
    </Stack>
  );
}

export default ThinkingWrite;
