
    Feature: GET /todos - Получить список задач

  Scenario: Проверка кода ответа для GET /todos
    Given я использую url "https://jsonplaceholder.typicode.com"
    When я вызываю метод GET с path "/todos"
    Then я получаю код ответа "200"


  Scenario Outline: Проверка получения задачи по id GET /todos/{id}
    Given я использую url "https://jsonplaceholder.typicode.com"
    When я вызываю метод GET с path "/todos" по id "1"
    Then я получаю код ответа "200"
    And в теле ответа будет id = <id>
    Examples:
      | id |
      | 1  |