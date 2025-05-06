## Проект Project3 (Погодный сервис)

- Сервис для получения и сохранения в БД данных с погодного сенсора
- Java клиент для отправки и сохранения в БД 1000 запросов со случайными данными
- Получение данных с сервера, и построение на их основе графика

***Используемые технологии:***
- Java, Postgres, Hibernate, Hibernate Validator, Spring Boot, Spring MVC, Spring Data JPA, XChart, JUnit, Maven

***REST API сервис***

- /sensors/registration - регистрация нового датчика в системе
- /measurements/add - добавить новое измерение
- /measurements - возвращает все измерения из БД
- /measurements/rainyDaysCount - возвращает количество дождливых дней из БД


- формат JSON для регистрации нового датчика в системе

```json
{
"name": "Sensor"
}
```

- формат JSON для добавления нового измерения в БД

```json
{
  "value": 24.7,
  "raining": true,
  "sensor": {
    "name": "Sensor"
  }
}
```
