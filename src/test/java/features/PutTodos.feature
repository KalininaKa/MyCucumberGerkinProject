Feature: PUT /todos - Обновить задачу

  Scenario Outline: Проверка обновления задачи
    Given я использую url "https://jsonplaceholder.typicode.com"
    And тело запроса будет со следующими параметрами: <title> <completed> <userId>
    When я вызываю метод PUT с path "/todos" по id "1"
    Then я получаю код ответа "200"
    And тело ответа соответствует JSON схеме "shema.json"
    Examples:
      | title        | completed | userId |
      | Updated Task | false     | 1      |