# Документация по проекту

### Технологии и стек

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-security)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Liquibase](https://img.shields.io/badge/Liquibase-0F4B8B?style=for-the-badge&logo=liquibase&logoColor=white)](https://www.liquibase.org/)
[![JUnit](https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white)](https://junit.org/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)

## Установка

1. Клонирование репозитория

       git clone https://github.com/kekich119/bank-card

2. Переход в директорию

       cd bank-card

3. Создание исполняемого файла

       mvn package

4. Поднятие базы данных

       docker-compose up

если что-то идёт не так, то воспользуйтесь

    alt-docker-compos.yml

5. Переход в директорию в jar файлом

       cd target

6. Запуск проекта

       java -jar bank_rest-main-0.0.1-SNAPSHOT.jar

## Структура проекта

![Структура](uml-bank-card.png)

## Api

![Скрин api](api.png)

[![Swagger API](https://img.shields.io/badge/Swagger-API-blue?logo=swagger&style=for-the-badge)](https://kekich119.github.io/bank-card/)

## Описание проекта
Этот проект представляет из себя REST приложение банка.
Его функции - создание и вход в аккаунт, обеспечение безопасности через JWT токен, создание карт, распределение на роли ADMIN и USER и многое другое что вы можете найти по api выше


## Возможные улучшения

Добавление большей валидации и подтверждение почты по письму на email

## Масштабирование

Возможно расширение на микросервисы




