# 🔧 Atipera Recruitment Project

Projekt rekrutacyjny oparty o Spring Boot oraz integrację z publicznym API GitHub.

## Cel aplikacji

Umożliwia pobieranie publicznych repozytoriów użytkownika GitHub, filtruje repozytoria typu *fork*, oraz umożliwia pobieranie gałęzi (`branches`) dla konkretnego repozytorium.

---

## Endpointy

### 1. `GET /api/v1/github/{username}`

Zwraca wszystkie **publiczne, nieforkowane repozytoria** dla danego użytkownika.

**Przykład odpowiedzi:**
```json
{
  "items": [
    {
      "name": "example-repo",
      "owner": {
        "login": "nowakpawel"
      },
      "fork": false
    }
  ]
}
```

### 2. GET /api/v1/github/{username}/{repositoryName}/branches
Zwraca listę gałęzi (branches) dla konkretnego repozytorium wraz z sha ostatniego commita dla tej gałęzi.

**Przykład odpowiedzi:**
```json
[
  {
    "name": "main",
    "lastCommitSha": "d3adb33f..."
  },
  {
    "name": "feature-xyz",
    "lastCommitSha": "c0ffeec0..."
  }
]
```

## Technologie i biblioteki:
* **Java 21** – z wykorzystaniem najnowszych funkcjonalności:
  * pattern matching dla switch
  * records
* **Spring Boot 3.5**
* **Retrofit 2** - komunikacja z GitHub API
* **JUnit 5 + Mockito** - testy jednostkowe + integracyjne
* **Lombok**

## Uruchomienie
### 1. `git clone https://github.com/nowakpawel/Atipera_recruitment.git`
### 2. `cd Atipera_recruitment`
### 3. `./mvnw spring-boot:run`