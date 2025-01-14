# OrderFlow-ms

### 📝 **Descrição do Projeto**
O **OrderFlow-ms** é um serviço responsável pelo gerenciamento de pedidos. Ele recebe pedidos do Produto Externo A, realiza o processamento e cálculo do valor total dos produtos, e em seguida disponibiliza esses pedidos processados para o Produto Externo B. O serviço é construído utilizando **Java 17** e **Spring Boot**, seguindo a arquitetura **Hexagonal (Ports & Adapters)** para garantir flexibilidade e facilidade de manutenção.

---

### 🚧 **Status do Projeto**
🛠️ O projeto está em construção. Estamos atualmente desenvolvendo as funcionalidades principais.

---

### 🧱 **Arquitetura Utilizada**
O projeto segue o padrão de **Arquitetura Hexagonal**, separando a lógica de negócios do mundo externo por meio de **Ports (interfaces)** e **Adapters (implementações)**. Isso permite que o sistema seja mais testável, flexível e preparado para futuras integrações.

**Principais Camadas:**
- **Domain:** Contém as regras de negócio.
- **Application:** Contém os casos de uso e as interfaces (ports).
- **Infrastructure:** Contém as implementações das interfaces e a configuração de infraestrutura.

---

### 📚 **Práticas e Padrões Utilizados**
- **Arquitetura Hexagonal (Ports & Adapters)**
- **Clean Architecture**
- **Domain-Driven Design (DDD)**
- **Clean Code**
- **SOLID Principles**
- **Test-Driven Development (TDD)**

---

### 🔎 **Aplicação dos Princípios SOLID**
#### **S - Single Responsibility Principle (Princípio da Responsabilidade Única)**
Cada classe do projeto possui **uma única responsabilidade** bem definida. Por exemplo:
- **`OrderService`**: Focado nas operações de pedidos (criação, consulta, etc.).
- **`ProductService`**: Responsável pelas operações relacionadas a produtos.

Essas responsabilidades estão separadas para evitar que uma única classe tenha múltiplos motivos para mudança.

#### **O - Open/Closed Principle (Princípio Aberto/Fechado)**
O sistema está projetado para ser **aberto para extensões e fechado para modificações**. Isso é possível graças ao uso de **interfaces (ports)** e **injeção de dependência**:
- **Interfaces como `OrderPersistence` e `CreateOrderUseCase`** permitem adicionar novas implementações sem alterar o código existente.
- Novos tipos de persistência ou novos casos de uso podem ser adicionados sem modificar as classes existentes.

#### **L - Liskov Substitution Principle (Princípio da Substituição de Liskov)**
O projeto segue o princípio de que **qualquer implementação de uma interface deve poder substituir sua interface base sem alterar o comportamento esperado**. Por exemplo:
- As implementações de **`OrderPersistence`** podem ser trocadas (como salvar em MongoDB ou em um sistema de cache) sem que os casos de uso sejam afetados.

#### **I - Interface Segregation Principle (Princípio da Segregação de Interfaces)**
O sistema utiliza **interfaces específicas e coesas** para cada caso de uso. Por exemplo:
- **`CreateOrderUseCase`**: Focado exclusivamente na criação de pedidos.
- **`GetOrderUseCase`**: Responsável apenas pela consulta de pedidos.

Isso evita que uma única interface tenha métodos que não são necessários para todos os seus implementadores.

#### **D - Dependency Inversion Principle (Princípio da Inversão de Dependência)**
O projeto utiliza **injeção de dependência** para garantir que as **camadas de alto nível (casos de uso)** não dependam diretamente das **camadas de baixo nível (infraestrutura)**. Por exemplo:
- A classe **`CreateOrderUseCaseImpl`** recebe a dependência **`OrderPersistence`** como uma interface, permitindo trocar a implementação sem alterar a lógica de negócios.

Essa abordagem facilita testes unitários e garante que o sistema seja mais flexível e fácil de manter.

---

### 🚀 **Tecnologias Utilizadas**
- **Java 17**
- **Spring Boot**
- **Spring Data MongoDB**
- **Docker**
- **Docker Compose**
- **Lombok**
- **ModelMapper**
- **JUnit 5**
- **Mockito**
- **MongoDB**
- **Redis**

---

### 📦 **Modelagem das Entidades**

#### **Order**
- `id`: Identificador único do pedido.
- `customerId`: Identificador do cliente que fez o pedido.
- `products`: Lista de produtos no pedido.
- `totalValue`: Valor total do pedido (soma dos preços dos produtos).
- `status`: Status do pedido (ex: PENDING, COMPLETED).

#### **Product**
- `id`: Identificador único do produto.
- `name`: Nome do produto.
- `price`: Preço do produto.
- `quantity`: Quantidade do produto no pedido.

#### **OrderEntity (MongoDB)**
- `id`: Identificador único do pedido.
- `customerId`: Identificador do cliente que fez o pedido.
- `products`: Lista de produtos no pedido (persistidos como documentos).
- `totalValue`: Valor total do pedido.
- `status`: Status do pedido.

#### **ProductEntity (MongoDB)**
- `id`: Identificador único do produto.
- `name`: Nome do produto.
- `price`: Preço do produto.
- `quantity`: Quantidade do produto no pedido.

---

### 📦 **Como Rodar o Projeto Localmente**

#### **Pré-requisitos:**
- **Java 17**
- **Maven**
- **Docker**

#### **Passos para rodar:**
1. Clone o repositório:
   ```bash
   git clone https://github.com/wcosme/OrderFlow-ms.git
   ```
2. Acesse a pasta do projeto:
   ```bash
   cd OrderFlow-ms
   ```
3. Compile o projeto:
   ```bash
   mvn clean install
   ```
4. Execute o Docker Compose:
   ```bash
   docker-compose up
   ```

---

### 🔗 **Endpoints Disponíveis**
| Método | Endpoint       | Descrição                      |
|--------|----------------|---------------------------------|
| POST   | `/orders`      | Recebe um pedido do Produto A  |
| GET    | `/orders/{id}` | Retorna um pedido específico    |
| GET    | `/orders`      | Lista todos os pedidos processados |

---

### 📊 **Banco de Dados Utilizado**
Para esse projeto, foi escolhido o **MongoDB** como banco de dados principal, devido à sua flexibilidade e capacidade de armazenar documentos JSON. Além disso, o **Redis** é utilizado como um cache para melhorar a performance do sistema em consultas frequentes.

---

### 🛠️ **Melhorias Futuras**
- Implementar autenticação com **Spring Security**
- Configurar monitoramento com **Prometheus** e **Grafana**
- **Integração com Kafka:** Implementar um fluxo assíncrono utilizando Apache Kafka para melhorar a escalabilidade e a resiliência do sistema.

---

### 📄 **Documentação da API**
A documentação da API está disponível através do Swagger UI, que pode ser acessado pela seguinte URL:
```
http://localhost:8080/orderflow/v1/swagger-ui/index.html
```

---

### 🤝 **Como Contribuir**
Se você deseja contribuir com o projeto, siga os passos abaixo:
1. Faça um fork do repositório
2. Crie uma nova branch:
   ```bash
   git checkout -b feature/sua-feature
   ```
3. Faça suas alterações e commit:
   ```bash
   git commit -m 'Adiciona nova feature'
   ```
4. Envie suas alterações para a branch principal:
   ```bash
   git push origin feature/sua-feature
   ```
5. Abra um Pull Request

---

### 📄 **Licença**
Este projeto está licenciado sob a **MIT License**.