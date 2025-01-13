import withMT from "@material-tailwind/react/utils/withMT";

const config = withMT({
	content: [
		"./index.html",
		"./src/**/*.{js,ts,jsx,tsx}",
	],
	theme: {
		extend: {
			colors: {
				primary: {
					"DEFAULT": "#003770",
					"variant": "#3b4f68",
				},
				secondary: {
					"DEFAULT": "#3B82F6",
				},
				link: "#22b3a4",
			},
			boxShadow: {
				'DEFAULT': '0 1px 3px 0 rgb(0 0 0 / 0.1), 0 1px 2px -1px rgb(0 0 0 / 0.1)',
				'sm': '0 0 2px 0 rgb(0 0 0 / 0.05)',
				'md': '0 0 6px -1px rgb(0 0 0 / 0.1), 0 2px 4px -2px rgb(0 0 0 / 0.1)',
				'lg': '0 0 15px -3px rgb(0 0 0 / 0.1), 0 4px 6px -4px rgb(0 0 0 / 0.1)',
				'xl': '0 0 25px -5px rgb(0 0 0 / 0.1), 0 8px 10px -6px rgb(0 0 0 / 0.1)',
				'2xl': '0 0 50px -12px rgb(0 0 0 / 0.25)',
				'3xl': '0 0 25px 0px rgba(0, 0, 0, 0.3)',
				'4xl': '0 0 55px 0px rgba(0, 0, 0, 0.3)',
			},
			width: {
				'128': '32rem',
				'160': '40rem',
				'192': '48rem',
				'256': '64rem',
			},
			screens: {
				'sm': '640px',
				'md': '768px',
				'lg': '1024px',
				'xl': '1280px',
				'2xl': '1536px',
			},
		},
		
	},
	plugins: [],
});

export default config;