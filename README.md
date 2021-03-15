# Aplikacja do zarządzania wynikami w skokach narciarskich

## Uruchomienie aplikacji
1. Uruchamiamy z poziomu IntelliJ SkijumpingApplication.java
2. W folderze resources znajdują się pliki *.rest umożliwiające testowanie

## Wystawione usługi
### Zapisanie danych o skoku do bazy danych
```http
POST http://localhost:8080/jump
```
<details>
  <summary>Request body</summary>
  <p>
   Struktura JSON-a:
    
   ```javascript
   {
      competitionRoundId: long,
      distance: float,
      juryNotes: [
        {
          judgeId: long,
          note: float
        }
      ],
      gate: int,
      windSpeed: float
    }
    
   ```
  
  Przykładowe request body:
  
  ```json
  {
    "competitionRoundId": 1,
    "distance": 136.5,
    "juryNotes": [
      {
        "judgeId": 1,
        "note": 18.5
      },
      {
        "judgeId": 2,
        "note": 18.5
      },
      {
        "judgeId": 3,
        "note": 18.5
      },
      {
        "judgeId": 4,
        "note": 18.0
      },
      {
        "judgeId": 5,
        "note": 18.5
      }
    ],
    "gate": 10,
    "windSpeed": -1.4
  }
  ```
  Opis pól
  
  | Nazwa pola                | typ                 | opis biznesowy | opis techniczny |
  | ---                       | ---                 | ---            | ---             |
  | <b>competitionRoundId</b> | ```long```          | seria konkursowa, w ramach której skok został oddany | identyfikator encji ```CompetitionRound``` |
  | <b>distance</b>           | ```float```         | długość skoku w metrach | liczba zmiennoprzecinkowa z dokładnością do jednej cyfry po przecinku |
  | <b>juryNotes</b>          | ```[long, float]``` | oceny sędziowskie wraz z informacją o osobie oceniającej | tablica pięcioelementowa obiektów <ul><li><b>judgeId</b> - identyfikator encji ```Judge```</li><li><b>note</b> - liczba zmiennoprzecinkowa z dokładnością do jednej cyfry po przecinku</li></ul> |
  | <b>gate</b>               | ```int```           | numer belki, z której oddano skok | liczba całkowita |
  | <b>windSpeed</b>          | ```float```         | uśredniona prędkość wiatry w m/s - w przypadku wiatru sprzyjającego skoczkowi wartość jest ujemna, a w przypadku wiatru niekorzystnego - dodatnia | liczba zmiennoprzecinkowa z dokładnością do jednej cyfry po przecinku |
  
  </p>
</details>
