# Упртый Првдчик

**Актуальная версия: 1.0.0**

**[Google Play](https://play.google.com/store/apps/details?id=com.ringov.stonedtrnsltr)**

## Цель

Проект-тестовое задание для Школы мобильной разработки от Яндекса 2017 года.

Условия для тестового задания:
> Необходимо создать приложение для Android на языке Java. Оно должно обладать такой минимальной функциональностью:
> 1. Поле для ввода текста, который будет переведён на другой язык; переключатель языка и варианты перевода, которые появляются, когда пользователь вводит текст в поле.
> 2. Возможность добавить переведённое слово или предложение в избранное.
> 3. Возможность просмотра истории переводов.
> 4. Возможность просмотра избранного.
> 5. Перевод с одного языка на другой с помощью API Яндекс.Переводчика.

## Функционал

В приложении присутствуют следующие функции:
1. Перевод текста с использованием технологии [Яндекс.Переводчика](https://translate.yandex.ru/about).
2. Выбор языковой пары для направления перевода.
3. Хранение, удаление и отображение истории переводов.
4. Добавление конкретного перевода в избранное или удаление из избранного. Просмотр избранного.
5. Возможность скопировать текущий перевод или отправить через другое приложение.
6. "Упоротый режим"

### Упоротый режим

 Упоротый режим был выполнен в качестве забавного бонуса к основной задаче, хотя идея такой реализации возникла сразу и изначально закладывалась в разработку.
 Суть режима в преобразовании всего текста, видимого на экране, в "упоротый вид" - с удалением бОльшей части гласных букв. Вдохновением для такого режима стал
 небезызвестный мем с [упоротым Винни-Пухом](http://lukomore.org/lurk/%D0%92%D0%BE%D0%BD%D0%BD%D0%B8). Стоит отметить, что режим работает только с языками, для которых 
 описано правило преобразования слов в упоротый вид.
 
 Для актуальной версии 0.96 упоротый режим поддерживается в следующих языках:
 - русский
 - английский

Остальные языки в упоротом режиме выглядят без изменений.

### Скриншоты

![Screenshots](https://raw.githubusercontent.com/3DRing/YaTrnsltr/additional_materials/materials/screens.png)

## Техническая реализация

Приложение написано для операционной системы Android на языке Java с использованием следующий сторонних библиотек:
- [ButterKnife](https://github.com/JakeWharton/butterknife)
- [Retrofit2](https://github.com/square/retrofit)
- [RxJava](https://github.com/ReactiveX/RxJava) и [RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [Material Dialogs](https://github.com/afollestad/material-dialogs)
- [Gson](https://github.com/google/gson)

### Архитектура

В качестве архитектуры был выбран паттерн VIPER. Мотивация к такому выбору в первую очередь заключалась в желании попробовать 
реализовать этот шаблон самостоятельно на относительно простом примере 
(VIPER - достататочно сложная архитектура для переводчика и можно было вполне обойтись без неё).
Приложение состоит из 3-х модулей:
- Общий - отвечает за функционал, общий для всего приложения (например, упоротый режим)
- Модуль перевода - весь функционал, связанный с переводом, общением с API переводчика и отображением результатов, сосредоточен в этом модуле.
- Модуль хранения - работа с локальной базой данных, сохранение и отображение истории и избранного.


![Screenshots](https://github.com/3DRing/YaTrnsltr/blob/additional_materials/materials/logo_small.png?raw=true)