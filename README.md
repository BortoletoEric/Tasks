# Tasks - Sistema de Gestão de Tarefas

Este projeto é uma aplicação Android nativa desenvolvida como uma **Prova de Conceito (PoC)** para demonstrar competências avançadas em desenvolvimento mobile. Ele serve como uma vitrine de boas práticas, arquitetura robusta e integração com APIs modernas.

---

## 🎯 Objetivo do Projeto

Focado em demonstrar o domínio de conceitos fundamentais e avançados do ecossistema Android, priorizando **escalabilidade**, **testabilidade** e **UX**.

<details>
<summary><b>🔍 Clique para ver detalhes</b></summary>

Demonstra o domínio de conceitos como comunicação assíncrona, persistência local, segurança biométrica e arquitetura reativa. O projeto foi construído para ser um exemplo real de aplicação robusta.
</details>

---

## 🏗️ Arquitetura: MVVM + Repository Pattern

Utiliza o padrão **MVVM**, seguindo as recomendações oficiais do Google para a separação de responsabilidades.

<details>
<summary><b>🔍 Clique para ver detalhes</b></summary>

-   **View**: Implementada com **Fragments** e **Activities**, utilizando **ViewBinding** para segurança de tipos e performance.
-   **ViewModel**: Gerencia o estado da tela com **LiveData**, garantindo que os dados sobrevivam a mudanças de configuração.
-   **Repository**: Centraliza o acesso aos dados (Local e Remoto). O `BaseRepository` abstrai lógicas comuns como verificação de conectividade.
</details>

---

## 🛠️ Tecnologias e Bibliotecas

Stack moderna e performática baseada em Kotlin e Jetpack.

<details>
<summary><b>🔍 Clique para ver detalhes</b></summary>

-   **Linguagem**: [Kotlin](https://kotlinlang.org/) - 100% focado em expressividade e segurança.
-   **Injeção de Dependência/Assincronismo**: **Coroutines & Flow** com **ViewModelScope**.
-   **Rede (REST API)**: **Retrofit 2** com mapeamento **Gson** e gestão centralizada de headers JWT.
-   **Persistência Local**: **Room Database** para cache e **DataStore** para preferências reativas.
-   **Segurança**: **Biometric API** para autenticação segura.
-   **Navegação**: **Navigation Component** com Safe Args.
</details>

---

## 💡 Conceitos Demonstrados

Aplicações práticas de Clean Code, SOLID e padrões de projeto Android.

<details>
<summary><b>🔍 Clique para ver detalhes</b></summary>

1.  **Offline-First (Partial)**: Cache local de dados críticos via Room.
2.  **Tratamento de Erros Centralizado**: Camada de exceções customizada no ViewModel.
3.  **Segurança de Dados**: Gestão de tokens e integração biométrica.
4.  **Clean Code & SOLID**: Código modularizado e de fácil manutenção.
5.  **Connectivity Monitoring**: Verificação proativa de rede em chamadas de API.
</details>

---

## 🚀 Como Executar o Projeto

Passos rápidos para clonar e rodar o projeto localmente.

<details>
<summary><b>🔍 Clique para ver detalhes</b></summary>

1.  Clone este repositório.
2.  Abra no **Android Studio (Ladybug ou superior)**.
3.  Certifique-se de ter o SDK 35 instalado.
4.  Sincronize o Gradle.
5.  Execute no emulador ou dispositivo físico (com sensor biométrico para testes de login).
</details>

---

**Desenvolvido por [Eric Bortoleto](https://github.com/bortoletoeric/)**