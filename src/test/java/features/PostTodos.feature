Feature: POST /todos - Создать новую задачу

  Scenario Outline: Проверка создания новой задачи
    Given я использую url "https://jsonplaceholder.typicode.com"
    And тело запроса будет со следующими параметрами: <title> <completed> <userId>
    When я вызываю метод POST с path "/todos"
    Then я получаю код ответа "201"
    And в теле ответа будет id
    Examples:
      | title    | completed | userId |
      | New Task | false     | 1      |