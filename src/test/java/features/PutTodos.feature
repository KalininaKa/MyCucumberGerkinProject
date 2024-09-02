
    Feature: PUT /todos - Обновить задачу

  Scenario Outline: Verify the get api for the todos
    Given я использую url "https://jsonplaceholder.typicode.com"
    And тело запроса будет со следующими параметрами: <title> <completed> <userId>
    When я вызываю метод PUT с path "/todos" по id "1"
    Then я получаю код ответа "200"
    And в теле ответа будет id
    And в теле ответа будет "title" = <title>
    Examples:
      | title        | completed | userId |
      | Updated Task | false     | 1      |

