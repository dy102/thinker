/** @type {import('next').NextConfig} */
const removeImports = require('next-remove-imports')();

const nextConfig = {
  images: {
    remotePatterns: [
      {
        protocol: "https",
        hostname: "png.pngtree.com",
      },
      {
        protocol: "https",
        hostname: "opgg-com-image.akamaized.net",
      },
      {
        protocol: "https",
        hostname: "s2.best-wallpaper.net",
      },
      {
        protocol: "https",
        hostname: "img.freepik.com",
      },
    ],
  },
};

module.exports = removeImports({
  ...nextConfig,
});
