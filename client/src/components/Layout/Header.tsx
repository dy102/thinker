"use client";
import { Stack } from "@mui/material";
import PageLink from "../PageLink/PageLink";
import { HeaderMainTag, HeaderTag, LogoTag, SearchTag } from "./Header.style";

export default function Header() {
  return (
    <HeaderTag>
      <HeaderMainTag>
        {/*[FIXME : 나중에 PageLink로 바꿔야함]*/}
        <LogoTag>
          <PageLink href="#">.</PageLink>
        </LogoTag>
        <SearchTag></SearchTag>
      </HeaderMainTag>
    </HeaderTag>
  );
}
