# README.md

# QAMID-DIPLOM-TAMONOVA

Тестирование формы бронирования тура
Форма предполагает оплату или кредит по данным карты (APPROVED/DECLINED)
Проводится тестирования вывода форм оплаты/оплаты в кредит, отправка данных и ответ для пользователя при попытке брони 
по разрешенной/запрещенной к операции карте

## Начало работы

clone
GIT-HUB: https://github.com/AsyaGedonist/qamid-diplom

### Prerequisites

IDEA
Docker
Docker-compose: container mysql

### Установка и запуск

1. Открыть проект в IDEA
2. Открыть программу DOCKER
3. Запуск docker-compose.yml - команда в терминале: docker-compose up
4. Запуск artifacts/aqa-shop.jar - команда: java -jar ./artifacts/aqa-shop.jar
5. Открыть тестовый класс "BuyTest" - команда: open src/test/java/test/BuyTest.java
6. Запуск тестов - команда: ctrl-shift-R
6. Просмотр отчетов Allure по итогам тестирования - ctrl-ctrl =>  gradlew allureServe

### Документы проекта

1. План тестирования - https://github.com/AsyaGedonist/qamid-diplom/blob/bafad05f3830f053cd886ee83a02b17f8a443962/Plan.md
2. Issue по итогам ручного тестирования - https://github.com/AsyaGedonist/qamid-diplom/issues
3. Отчет по итогам тестирования - jetbrains://idea/navigate/reference?project=qamid-diplom-tamonova&path=Report Doc/Report.md
4. Отчет по итогам автоматизации - https://github.com/AsyaGedonist/qamid-diplom/blob/1f34d5722c7815eff6ae80e0afd8be72d6b28e68/Report%20Doc/Summary.md

