Тестовое задание для T1 Консалтинг создано на основе Java 17, Spring 3.2.0, Gradle 8.4.

## Эндпоинты

    POST /api/v1/countCharacters
    {
        "input" : String
    }

## Запуск локально

Есть возможность создать jar файл и запустить его через командную строку:

    git clone https://github.com/ZiPERRR/t1-test.git
    cd t1test
    ./gradlew bootJar
    java -jar t1test.jar

Или есть возможность запустить напрямую из папки, используя:

    ./gradlew bootRun

## Docker

В проекте присутствует Dockerfile, который позволяет создать образ и на его основе запустить контейнер с приложением:

    docker build -t t1test .
    docker run -dp 127.0.0.1:8080:8080 t1test