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

![Структура](https://www.plantuml.com/plantuml/dsvg/pLXDSnif4xxhLqpLUv7BoPpUeok9FfHT9RQSifJaiBK7DBHaOWPcWR6Zqig_VMa6ZnWnaiQfsggzID0q3JnqFztC1ss8CcNEUo3AFCbadi9FaXSSqYKH3nbHLAUbOJozanmMcmAIRUyoIOQ3sMmonjRN-UGE_mVZc-cizpPXmbWp5AmCcsim8zlhNzZ-rYvcMBPBQbih9NCtR5KlUhjUhvTnelMHVL0ZqpbRYwz5ihKhpOrYOfq8aiE-1pbXVDyjhBKdgIZQFcKQX65cauvinzdcqavcT6y8rMPblWIrbyad4NRnTANs42m99oB3ZLtBZF0nCP1G-pEWD99DVXPCGSSj8dRjMqGWq8XT7jIAP734a892QcQagivxLtUjsUVajZ1C2iAlSEnTicA2BcGntDmg2cgucT8nwAn_OJMlcJQ15hQqrKDG-jMMK0l5Rdou6NaCsyUnSKJdKJAQACZb8vpJei317DVQdVKmZuakfD0mDlA_wWuQibAXGopa0uYFZ1jGRg-VlY-I7qzc91KkzrM36eCXZEivg4UMGL96ejx_F7YoiZD53nA4ztytfa0T3kOE_Yf1cpUXoAFqfferkYTCd8_YJ0gZ9EUWqha_mwWM9Lk_vt2plhq_MrQTxOiFxeb0mb59qUnRZK5fxZ5kCe9EVKC4MLizqYQr_FHodecBjAltxnBjsAPVaSx5KPMfS2fJqOPE9qHMR1sWWLs7H7YtPjy-WT5bTPCQJ32t7xJn56U0Gx9-3NJzfcwr_rAv3ffwELZW9ecEPPMtUJdE6qcb6yRcibH8Q-toVlSCCXLX1a7ya3n_EMtWPHpdP4y1IdAOqlEPwj9PmSGvfNWJNW45CslDYNncnLmGAKtGxe7CNpYgrWw2vd6bKEGMm6cXE9WU3xdLOhdVMhUyxkgBUNOFjEIWwXoCZHSNHyL35_SJAEhrNyKxU5zTEQTz6n4RNTRfC4mUxn1yD8rotKAd5-74XlR7ahVnRmi9LU1R9W91RwI0JHjjpry6xQvUXHNk9SJgD2BaaTmGnd4rgvpNpIPQ9_1kezXC0QboUYiWdO8-E7wozH59SbaAqvRKFogPjyaN4bVhYDo0vao4q7K6eHtYLdYSyw2TRmoUqEES4mquHvdkva2r0zQJJxbhV3-vImCuP-oghpR_gqPGfvC3wkgyQO1G7QP1mLeskXwtO3nhTEtc7LCJIl6_Zpzr06AdYTs5r_VLwo7J1PLLmYLn9eSR_1LD5esko1dqotndvbwMJO9UIic1Y0H-sfawNhb-FlaK46kqKqfaQjTxrMi1xHnxBJZuF5ADBF9yvZZFUuon4GstBX4fdSHOno3lEbLaTKwgIxBTMQO9DX6N177UZok4peK_SabGnLYv0kDDSRQ21NEMFyC6dG3B7gjcUytQG80Y1bo1U537jPGLo8kLQZKGboUdDTn8TnAFR-Is034eM7qVbucJV9acMQcDp7qFRxX3pM7DQ3DtxxGGoS5eCVqssVUdiqF9U78zMKp6Uy7m-dRqsGfstMWjfFTj1zh2yKIJH-XZgn7XjQS7r-epPfMpK2aSOyAEvY3CDT7c__DDlfIytwcig2Hl_1yJfdcvxkNV-kfkInfd7kgruPpeXsYyMz9lv1oagpYXTmwsWEsdLYSExUwVei9W3x-A7IwnB1cdTP8QOH6HOLnUlRTKQa1GdVocY310CMubRhDAOBx7G1yy0-ha56OPfWTPXeNGJ8hgqmCzEdujiuTeU6Hd86iGhe-Qc0YovD2bJ1_QblKMSqEOZVI2Up9AjY5KxW5WuNrXcJ_-h0ptf5gdka3HIf4SBDuFAIM6f3yA4gQoJmK9uktgxxTlN_knh7Mf9rYcA_UYJMz51bz0UOBpRtYtKFVlEQrhr8fOHAPO0T7AQTBuMuzTC4sxbrZ_6dnnzV3hHmlFr3LSUnz0q3Bd_m00)

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




