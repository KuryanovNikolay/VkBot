# VkBot

Для запуска приложения нужно:
1) Прочитать документацию из инструкции
2) Клонируем репозиторий
3) Регистрируемся и скачиваем ngrok (доступ на сайт через vpn)
4) Запускаем ngrok и авторизуемся с помощью команды ngrok authtoken <your-authtoken>
5) Запускаем сервер через ngrok с помощью команды ngrok http 8080
6) Запускаем наше приложение
7) Заходим в управление сообшеством: сообщество -> управление -> работа с API -> Callback API
8) В строку "адрес" вставляем наш адрес из ngrok. Его можно увидеть в строке со словом Forwarding, нужный нам адрес заканчивается на free.app (копируем до этих слов включительно
9) Берём строку из Callback API -> "Строка, которую должен вернуть сервер: 5c061512", копируем "5c061512" и вставляем в файл конфигурации в строчку vk.confirmation.code=5c061512
10) Нажимаем подтвердить. Должна появиться галочка c текстом: "Адрес успешно сохранён"
11) После чего пишем сообщение сообшеству и должны увидеть ожидаемый ответ
    ![image](https://github.com/user-attachments/assets/1b34539c-7dce-4ded-9bc4-2aaea8cbab12)
