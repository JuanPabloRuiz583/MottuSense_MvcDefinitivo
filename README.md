# Proposta

MottuSense é uma solução inteligente desenvolvida para a Mottu, focada no mapeamento automatizado do pátio e na gestão eficiente das motos da frota.
Utilizando sensores IoT e uma arquitetura em nuvem com APIs .o sistema permite monitoramento em tempo real, controle de entrada e saída dos veículos, e integração com um app mobile para operadores.
Combinando banco de dados relacional e não relacional, DevOps, testes de qualidade e tecnologias modernas de desenvolvimento mobile e backend, o MottuSense garante rastreabilidade, performance e escalabilidade — tudo alinhado com os pilares da Mottu: tecnologia, mobilidade e oportunidade.

🛵 Nome da Solução: MottuSense
🔤 Significado:
"Mottu" (nome da empresa)
"Sense" de sensorial, percepção, inteligência → representa a capacidade da solução de "sentir" e gerenciar o pátio de motos com IoT.

## Diferencial

- Monitoramento em tempo real das motos atraves dos sensores
- localização exata das motos

# Mottu Sense

Aplicação web MVC para gestão de motos, sensores de localização e pátios, com autenticação de usuários.

## Tecnologias

- Java
- Spring Boot
- Gradle
- Thymeleaf
- Flyway
- Postgres
- Spring Security
- oAuth2 (permitindo se autenticar com o github ou google)
- Seeder (deixando patios ja cadastrados automaticamente no sistema)
- compose.yaml 

## Instalação

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/JuanPabloRuiz583/MottuSense_MvcDefinitivo.git

2. **Configure as seguintes variaveis de ambiente para funcionar a autenticacao no google e github:**
   ```bash
   GITHUB_CLIENT_ID = Ov23li0Nu4JFz0n2ay1r
   GITHUB_CLIENT_SECRET = 948617a4f142c1964d46d670371112340f8a964c
   GOOGLE_CLIENT_ID = 412634895320-fuvf9cnj52cmdnpvk1huea1aim4v673n.apps.googleusercontent.com
   GOOGLE_CLIENT_SECRET = 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


3. **Abra o docker desktop antes de executar o projeto**
   
4. **rode o projeto**

5. **Acesse no navegador:**

🔑 Login (autentique-se primeiro):
http://localhost:8080/login

🏍️ Motos — Cadastro / Edição / Remoção / Listagem / Busca por placa:
http://localhost:8080/moto
(se não estiver autenticado, será redirecionado para a tela de login)

📄 Formulário de Motos:
http://localhost:8080/moto/form
(acessível também clicando no botão "Nova moto")

🏢 Pátios — Listagem (ver quais pátios estão disponíveis antes do cadastro):
http://localhost:8080/patio

📍 Sensores — Cadastro / Edição / Remoção / Listagem:
http://localhost:8080/sensor-localizacao
(para criar, é necessário ter uma moto cadastrada para vincular a placa)

📝 Formulário de Sensores:
http://localhost:8080/sensor-localizacao/form
(acessível também clicando no botão "Cadastrar sensor")

🔒 Logout:
http://localhost:8080/logout



