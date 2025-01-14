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
Para esse projeto, foi escolhido o **MongoDB** como banco de dados principal, devido à sua flexibilidade e capacidade de armazenar documentos JSON. Além disso, o **Redis** será utilizado como um cache para melhorar a performance do sistema em consultas frequentes.

---

### 🛠️ **Melhorias Futuras**
- Implementar autenticação com **Spring Security**
- Configurar monitoramento com **Prometheus** e **Grafana**
- **Integração com Kafka:** Implementar um fluxo assíncrono utilizando Apache Kafka para melhorar a escalabilidade e a resiliência do sistema.
- **Integração com Redis:** Utilizar o Redis como cache para otimizar a performance e reduzir o tempo de resposta das consultas.

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