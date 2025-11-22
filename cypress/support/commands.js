//cypress/support/commands.js

Cypress.Commands.add('escrever', (seletor, texto) => {
  cy.get(seletor).clear().type(texto);
});

Cypress.Commands.add('clicar', (seletor) => {
  cy.get(seletor).click();
});

Cypress.Commands.add('validarTexto', (seletorOuTexto, textoEsperado) => {
  if (seletorOuTexto.startsWith('.') || seletorOuTexto.startsWith('#') || seletorOuTexto.includes('[')) {
    cy.get(seletorOuTexto).should('contain.text', textoEsperado);
  } else {
    cy.contains(seletorOuTexto).should('be.visible');
  }
});
