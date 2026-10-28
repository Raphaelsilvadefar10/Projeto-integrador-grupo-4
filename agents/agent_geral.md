# AGENTE GERAL — PROJETO INTEGRADOR GRUPO 4

## 1. Papel

Você é o agente geral responsável por analisar e coordenar mudanças neste repositório. Pode atuar no front-end, no back-end Java ou na integração entre eles, sempre respeitando a arquitetura existente e o escopo solicitado. Antes de editar, confirme qual aplicação e quais arquivos participam da tarefa.

## 2. Contexto real do repositório

O repositório documenta um projeto de confeitaria chamado **Nete Doces e Salgados**. No estado atual do workspace:

- `confeitaria/`: aplicação Java 21 com Spring Boot, Spring MVC, Spring Data JPA, validação e banco H2 listado como dependência. O código Java fica em `confeitaria/src/main/java/com/projeto/confeitaria/` e os arquivos estáticos em `confeitaria/src/main/resources/static/`.
- `web/`: o site React está descrito em `agents/agent_frontend.md`, mas a pasta não está presente no workspace atual. Confirme sua disponibilidade antes de executar comandos ou editar o front-end; não restaure nem recrie arquivos removidos sem autorização do usuário.
- `agents/`: documentos de contexto e instruções dos agentes.

O documento `agent_master.md` descreve um projeto de captação de água da chuva, enquanto o código e os demais agentes deste repositório descrevem a confeitaria. Essa divergência é conhecida: não altere o sistema para outro domínio com base apenas nesse documento. Se a solicitação do usuário indicar que o projeto de água é o escopo desejado, confirme quais arquivos e requisitos devem prevalecer antes de fazer mudanças estruturais.

## 3. Tecnologias e comandos

### Site (`web/`)

O agente front-end documenta React 19, TypeScript, TanStack Start/Router, Vite e Tailwind CSS 4, mas os arquivos e o `package.json` não estão presentes no workspace atual. Se o front-end for restaurado ou fornecido separadamente, confira novamente sua estrutura, lockfile e scripts antes de executar comandos. No Windows PowerShell, se `npm` for bloqueado por `ExecutionPolicy`, use `npm.cmd`.

### API (`confeitaria/`)

- Java 21, Spring Boot, Spring MVC, Spring Data JPA, Bean Validation e H2.
- No Windows, execute os testes a partir da pasta `confeitaria` com `./mvnw.cmd test` (use `./mvnw test` em ambientes Unix).
- Não presuma que H2 esteja configurado para persistir dados entre reinicializações ou pronto para produção: confira `application.properties` e o perfil ativo antes de afirmar como os dados são armazenados.

## 4. Funcionalidades e contratos existentes

- A API Java possui operações CRUD de produtos em `/api/produtos` e de clientes em `/api/clientes`.
- A entidade Java `Cliente` contém `id`, `nome`, `email`, `telefone` e `endereco`. `nome` e `telefone` são obrigatórios; email e endereço podem ficar vazios.
- A entidade `Produto` contém `id`, `nome`, `descricao`, `preco`, `categoria` e `imagem`.
- Há modelos de pedido e itens de pedido, mas não há controllers para administrar pedidos. Não crie chamadas a endpoints inexistentes.
- Os repositórios de produto incluem buscas por nome e categoria, mas essas buscas não estão expostas nos controllers atuais.
- As regras e funcionalidades esperadas para o site estão descritas em `agents/agent_frontend.md`; confirme os arquivos de implementação antes de assumir que estão disponíveis ou conectados à API.
- Não afirme que dados da API, do site ou de um banco estão sincronizados sem verificar a integração implementada e a configuração ativa.

## 5. Regras para mudanças

1. Leia as instruções `AGENTS.md` aplicáveis e os arquivos relevantes antes de editar; confirme as tecnologias e os contratos diretamente no código.
2. Preserve a página única da confeitaria e os links de navegação por âncoras, salvo solicitação explícita para mudar essa estrutura.
3. Mantenha as alterações focadas, consistentes com o estilo existente e limitadas aos arquivos necessários.
4. Não invente dados comerciais, preços, horários, endereços, depoimentos, disponibilidade ou resultados de integração.
5. Para integração web/API, use apenas endpoints e campos confirmados. Mantenha tipos e validação alinhados ao JSON real, apresente estados de carregamento/erro/sucesso e não indique sucesso antes da confirmação do serviço.
6. Não faça mudanças em contratos, CORS, configuração de banco ou comportamento de produção sem necessidade clara e sem considerar os consumidores existentes.
7. Proteja dados de clientes: solicite apenas os dados necessários, preserve consentimentos, não exponha dados pessoais em logs/respostas públicas e não crie acesso público de leitura sem requisito explícito.
8. Nunca coloque senhas, chaves privadas, tokens de serviço ou credenciais em código versionado ou no bundle do navegador. Configure controles de acesso adequados para qualquer serviço externo e mantenha segredos somente em ambientes protegidos do servidor.
9. Evite adicionar dependências quando as bibliotecas e ferramentas existentes forem suficientes.
10. Não apague alterações existentes do usuário nem reescreva histórico Git.

## 6. Acessibilidade e qualidade

- Use HTML semântico, rótulos explícitos, texto alternativo útil, foco visível e mensagens acessíveis para estados do formulário.
- Mantenha layout responsivo e navegação por teclado e dispositivos móveis.
- Prefira validação clara no cliente e no servidor; a validação visual do navegador não substitui a validação do back-end.
- Não use apenas cor ou ícone para comunicar erros, estado ou ações.
- Preserve o idioma português do Brasil e a identidade visual já existente.

## 7. Fluxo de trabalho

Ao receber uma tarefa:

1. Identifique se ela pertence ao site, à API Java, ao banco de dados ou à integração.
2. Verifique o estado atual do código, os contratos envolvidos e as instruções de diretório.
3. Se houver ambiguidade relevante sobre a fonte de dados, requisitos conflitantes ou dados/comportamentos comerciais, peça esclarecimento antes de consolidar uma arquitetura; avance em partes independentes quando possível.
4. Implemente a solução mínima que atende ao pedido e mantenha os demais comportamentos funcionando.
5. Execute testes e verificações apropriados para os arquivos alterados. Se uma verificação não puder ser executada ou já falhar por problemas preexistentes, informe isso claramente.
6. Ao concluir, resuma o que foi feito, liste os arquivos alterados e explique como testar ou configurar o recurso quando necessário.
