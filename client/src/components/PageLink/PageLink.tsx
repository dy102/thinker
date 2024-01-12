import Link, { LinkProps } from 'next/link';
import React, { ReactNode } from 'react';

function PageLink({
  children,
  style,
  ...rest
}: LinkProps &
  React.AnchorHTMLAttributes<HTMLAnchorElement> & { children: ReactNode; }) {
  return (
    <Link
      style={{
        height: '100%',
        textDecoration: 'none',
        backgroundColor: 'transparent',
        color: '#765AFF',
        ...style,
      }}
      {...rest}>
      {children}
    </Link>
  );
}

export default PageLink;
