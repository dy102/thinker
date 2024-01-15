"use client";
import { InputAdornment, TextField } from "@mui/material";
import PageLink from "../PageLink/PageLink";
import {
  HeaderMainTag,
  HeaderTag,
  LogInTag,
  SignInTag,
  SurveyTag,
  ThinkingTag,
} from "./Header.style";
import Button from "../Button/Button";
import SearchIcon from "@mui/icons-material/Search";

export default function Header() {
  return (
    <HeaderTag>
      <HeaderMainTag>
        {/*[FIXME : 나중에 로고로 바꿔야함]*/}
        <PageLink href="/">LOGO</PageLink>
        <TextField
          InputProps={{
            startAdornment: (
              <InputAdornment position="start">
                <SearchIcon />
              </InputAdornment>
            ),
          }}
          sx={{ color: "#765AFF", width: "50%", margin: "0 5px 0 10px" }}
          variant="standard"
        />

        <SurveyTag>
          <Button>
            <PageLink href="#">Survey</PageLink>
          </Button>
        </SurveyTag>

        <ThinkingTag>
          <Button>
            <PageLink href="#">Thinking</PageLink>
          </Button>
        </ThinkingTag>

        <LogInTag>
          <Button sx={{ border: "none" }}>
            <PageLink href="#">Log_In</PageLink>
          </Button>
        </LogInTag>

        <SignInTag>
          <Button sx={{ border: "none" }}>
            <PageLink href="#">Log_Out</PageLink>
          </Button>
        </SignInTag>
      </HeaderMainTag>
    </HeaderTag>
  );
}
