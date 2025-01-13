import withMT from "@material-tailwind/react/utils/withMT";

const config = withMT({
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        dark: {
          blue: "#0f172a",
        }
      }
    },
  },
  plugins: [],
})

export default config;