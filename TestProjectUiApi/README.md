# n.terzyan
# TestProjectUiApi
### Описание
Этот проект содержит автоматизированные Rest API и UI тесты.

### Инструкция для запуска
Для запуска тестов необходимо добавить нужную информацию в тестовый конфигурационный файл config_test.json. Затем проверить пути к конфигурационным файлам в ParseDataUtil.
Для тестрования авторизации перед запуском тестов добавить в JSON ```"login"``` и ```"password"``` для доступа к базе данных.
```json
{
  "variant": 2,
  "login": "",
  "password": "",
  "token": "token",
  "projectName": "Nexage",
  "projectNameAdded": "NewProject",
  "countRandomForProject": 2,
  "propertyOfDir": "user.home",
  "propertyOfNameDir": "Downloads",
  "nameDownLoadingFile": ".crdownload",
  "filePath": "src/test/resources/screenshot.png",
  "replacementProject": "projectId=",
  "newNameScreenshot": "/new.png"
}
```
Далее необходимо добавить конфигурацию для выполнения API в конфигурационный файл configApi.json. Затем проверить пути к конфигурационным файлам в ParseDataUtil.

```json
{
  "url": "http://localhost:8081/api",
  "variant": "variant",
  "projectId": "projectId",
  "sid": "SID",
  "projectName": "projectName",
  "testName": "testName",
  "methodName": "methodName",
  "env": "env",
  "browser": "browser",
  "content": "content",
  "contentType": "contentType",
  "isException": "isException",
  "testId": "testId",
  "startJsonWith": "[{",
  "simpleDateFormat": "yyyy-MM-dd HH:mm:ss.S"
}
```
Затем необходимо проверить в фйле settings.json путь к ресурсу
```json
{
  "application": {
    "url": "localhost:8081/web/projects"
  }
}

```
### Реализованные тесты
#### Шаг 1
Запросом к апи получить токен согласно номеру варианта
#### Шаг 2
Перейти на сайт. Пройти необходимую авторизацию. С помощью cookie передать сгенерированный на шаге 1 токен(параметр token). Обновить страницу.
#### Шаг 3
Перейти на страницу проекта Nexage. Запросом к апи получить список тестов в JSON\XML -формате.
#### Шаг 4
Вернуться на предыдущую страницу в браузере(страница проектов). Нажать на +Add. В новой вкладке ввести название проекта, сохранить. Закрыть вкладку. Обновить страницу
#### Шаг 5
Перейти на страницу созданного проекта. Добавить тест через API(вместе с логом и скриншотом текущей страницы).
#### Шаг 6
Перейти на страницу созданного теста.

### Технологии
Автотесты написаны на Java с использованием TestNG, RestAssured, Aquality Selenium, применены различные библиотеки и фреймворки. При написании использованы библиотеки lombok для автоматической генерации конструкторов и геттеров, сеттеров, jackson-databind для парсинга JSON-файлов slf4j-api - библиотека для логирования, image-comparison - для сравнения изображений.

