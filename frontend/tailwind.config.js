/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      "colors":{
        "primary-grey":'var(--primary-grey)'
      },
      "fontFamily": {
       "sans": ['Poppins', 'sans-serif'],
      },
    },
  },
  plugins: [],
}

