### Отчет по итогам автоматизации

Все сценарии, описанные в плане были автоматизированы
Базовая отчетность Gradle для формирования информации о тестах в процессе подготовки работы была изменена 
на отчетность Allure, как более наглядная

Сложности с анализом данных из БД с помощью автоматизации удалось избежать (тк была затронута только 
поверхностная информация о добавлении данных в БД)
Сложности возникли с тестированием самой формы - недостаточность опыта (тестирование видимости/текста 
не было сформировано сразу, тестирование БД в объекте страницы, сразу не вспомнила про то что значения не
должны быть жесткими)

# Реальное время выполнения работ (в часах):
- 1 час настройка проекта (согласно плану)
- 3 часа ручное тестирование + заполнение отчетности по результатам дефектов (согласно плану)
- 7 часов подготовка к тестированию (+2 часа понадобилось для формирования не жестких значений для валидации)
- 6 часов написание атотестов (+ 2 часа понадобилось на исправление недочетов)
- 1 час формирование отчетности по итогам тестирования (менее чем указано в плане, но результат еще не конечный)
- 1 час формирование отчетности по итогам проекта (менее чем указано в плане, но результат еще не конечный)

+ 1 час заняло формирование README.md для описания запуска тестирования

# План сдачи работ:
- 18.10.22 - Прогон ручных тестов, формирование issue по результатам ручного тестирования
- 21.10.22 - Формирование скелета проекта - подготовка к тестированию (страницы, классы с данными) - первая сдача 22/10, 
принято 30/10
- 23.10.22 - Автотесты - первая сдача 22/10, принято 5/11
- 26.10.22 - Формирование отчетности по итогам тестирования - сдано 9/11
- 28.10.22 - Формирование отчетности по итогам автоматизации - сдано 9/11