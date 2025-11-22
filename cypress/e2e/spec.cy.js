describe('template spec', () => {
  it('Validar campo de busca positivamente', () => {
    cy.visit('/')
    cy.get('input[name="q"]').type('Teste 1')
    cy.get('button[type="submit"]').contains('Buscar').click();
    cy.get('.table').should('contain.text', 'Teste 1')
  })

  it('Validar campo de busca negativamente', () => {
    cy.visit('/')
    cy.get('input[name="q"]').type('Teste errado')
    cy.get('button[type="submit"]').contains('Buscar').click();
    cy.get('.subtitle').should('contain.text', 'Nenhum livro encontrado.')
  })

  it('Validar a função limpar o campo de busca', () => {
    cy.visit('/')
    cy.get('input[name="q"]').type('Teste Limpar')
    cy.get('.btn').contains('Limpar').click();
    cy.get('input[name="q"]').should('have.value', '').and('have.attr', 'placeholder', 'Buscar por título ou autor')
  })

  it('Validar Editar livro', () => {
    cy.visit('/')
    cy.get('.btn').contains('Editar').click();
    cy.get('input[name="titulo"]').clear().type('Livro de Teste Editado');
    cy.get('input[name="autor"]').clear().type('Autor de Teste Editado');
    cy.get('.btn').contains('Salvar').click();
    cy.get('tbody').should('contain.text', 'Teste Editado').and('contain.text', 'Teste Editado');
  })

  it('Validar Criar e Excluir livro', () => {
    cy.visit('/')
    cy.get('.btn').contains('Novo').click();
    cy.get('input[name="titulo"]').type('Excluir Livro');
    cy.get('input[name="autor"]').type('Excluir Autor');
    cy.get('.btn').contains('Salvar').click();
    cy.get('tbody').should('contain.text', 'Excluir Livro').and('contain.text', 'Excluir Autor');
    cy.get('.btn').contains('Excluir').click();
    cy.get('tbody').should('not.contain.text', 'Excluir Livro').and('not.contain.text', 'Excluir Autor');
    cy.get('.flash').should('contain.text', 'Livro excluído!')
  })

  it('Validar voltar para home utilizando botão voltar', () => {
    cy.visit('/')
    cy.get('.btn').contains('Novo').click();
    cy.get('.btn').contains('Voltar').click();
    cy.get('.table').should('be.visible');
  })

  it('Validar voltar para home utilizando título', () => {
    cy.visit('/')
    cy.get('.btn').contains('Novo').click();
    cy.get('.container').contains('Biblioteca').click();
    cy.get('.table').should('be.visible');
  })

  it('Validar cadastrar livro sem nome nem autor', () => {
    cy.visit('/')
    cy.get('.btn').contains('Novo').click();
    cy.get('.btn').contains('Salvar').click();
    cy.get('.field-error').should('contain.text', 'Título é obrigatório').and('contain.text', 'Autor é obrigatório');
  })
})