Resumo do back-end da confeitaria

Models
- Cliente (model/Cliente.java): representa um cliente com id, nome, email, telefone e endereco. Usa JPA para mapear a tabela clientes; nome e telefone são obrigatórios.
- Produto (model/Produto.java): guarda id, nome, descricao, preco, categoria e imagem, mapeados para a tabela produtos. Nome, preço e categoria são obrigatórios. O preço está como Double.
- Pedido (model/Pedido.java): relaciona um pedido a um cliente e a uma lista de itens. Contém data, valor total e status. Antes de persistir, preenche valores padrão: data atual, total zero e status PENDENTE.
- ItemPedido (model/ItemPedido.java): relaciona pedido e produto, com quantidade e preço unitário. Esse preço usa BigDecimal, diferente do preço do model Produto.

Controllers
- ClienteController (controller/ClienteController.java): oferece CRUD em /api/clientes: listar, buscar por ID, cadastrar, atualizar e excluir. Retorna 404 quando o cliente não existe e 204 ao excluir com sucesso.
- ProdutoController (controller/ProdutoController.java): oferece as mesmas operações de CRUD em /api/produtos, com respostas semelhantes para recursos inexistentes e exclusões.
Os controllers usam injeção por construtor e recebem entidades diretamente no corpo das requisições. Ambos permitem CORS de qualquer origem (*). Não há validação explícita dos dados recebidos, nem uma camada de serviço entre controllers e repositórios.

Repositories
- ClienteRepository (Repository/ClienteRepository.java): estende JpaRepository<Cliente, Long>, oferecendo operações básicas de persistência.
- ProdutoRepository (Repository/ProdutoRepository.java): também estende JpaRepository e define buscas por nome parcial, sem diferenciar maiúsculas de minúsculas, e por categoria, também sem diferenciar maiúsculas de minúsculas. Essas buscas existem no repositório, mas ainda não têm endpoints nos controllers.
Não há repositório nem controller de pedidos ou itens de pedido; os models desses recursos estão presentes, mas ainda não existe API para administrá-los.
Pontos importantes
- Há uma inconsistência entre os pacotes e os diretórios: os arquivos declaram com.nete.*, mas estão sob com/projeto/confeitaria/.... A classe principal usa com.projeto.confeitaria; com o escaneamento padrão do Spring, os pacotes com.nete.* ficam fora do pacote-base da aplicação. Isso deve ser corrigido para que o Spring encontre os controllers, repositórios e entidades.
- A execução de ./mvnw test falhou ao iniciar o contexto, com erro de pacote em Pedido. A saída apontou para uma classe compilada antiga em target/, então é necessário alinhar os pacotes e verificar novamente com uma compilação limpa.
- Convém padronizar valores monetários em BigDecimal e acrescentar validação das entradas. Também é importante revisar a serialização dos relacionamentos bidirecionais de pedido e itens, que pode gerar referências circulares no JSON