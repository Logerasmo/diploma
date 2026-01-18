
# Дипломная работа
### Запуск контейнера
docker-compose up --build
### Остановка контейнера
docker-compose down
## Используемый порт 8800
# Примеры запросов
## POST http:/localhost:8800/login


body json

          login:
            type: string
          password:
            type: string
          

## POST http:/localhost:8800/file
header:
`auth-token
    type: string`

query:
`filename
    type: int`

body multipart/form-data:

          File:
      type: object
      properties:
        hash:
          type: string
        file:
          type: string
          format: binary
