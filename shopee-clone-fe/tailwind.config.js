/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      fontFamily: {
        hel: "Helvetica, sans-serif",
      },
      textColor: {
        white: "#FFFFFF",
      },
      backgroundColor: {
        header: "#f53d2d",
      },
    },
  },
  plugins: [],
};
