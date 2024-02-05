import Link, { LinkProps } from "next/link";
import React, { ReactNode } from "react";
import { mainColor } from "../Themes/color";

function PageLink({
  children,
  style,
  ...rest
}: LinkProps &
  React.AnchorHTMLAttributes<HTMLAnchorElement> & { children: ReactNode }) {
  return (
    <Link
      style={{
        height: "100%",
        textDecoration: "none",
        backgroundColor: "transparent",
        color: `${mainColor}`,
        ...style,
      }}
      {...rest}
    >
      {children}
    </Link>
  );
}

export default PageLink;
