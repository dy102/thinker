import type { Metadata } from 'next'
import './globals.css'

export const metadata: Metadata = {
  title: 'Thinker',
  description: 'Generated by create next app',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html>
      <body>
        <h1><a href='/'>WEB</a></h1>
        {children}
      </body>
    </html>
  )
}
