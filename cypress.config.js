const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
    baseUrl: "https://online-library-management-iz1c.onrender.com",
    viewportWidth: 1920,
    viewportHeight: 1080,
    failOnStatusCode: false
  },
});
