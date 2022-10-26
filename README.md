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
2. Запустить DOCKER
3. Запуск docker-compose.yml
4. Запуск artifacts/aqa-shop.jar
5. Запуск тестов BuyTest
6. Запуск тестов CreditTest