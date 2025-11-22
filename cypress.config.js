const { defineConfig } = require("cypress");
const allureWriter = require("@shelex/cypress-allure-plugin/writer"); // 👈 ESSA LINHA É ESSENCIAL

module.exports = defineConfig({
  e2e: {
    baseUrl: "https://online-library-management-iz1c.onrender.com",
    viewportWidth: 1920,
    viewportHeight: 1080,
    setupNodeEvents(on, config) {
      allureWriter(on, config);
      return config;
    },
    env: {
      allure: true,             
    },
  },
});