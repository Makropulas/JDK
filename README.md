## Урок 1. Графические интерфейсы

### 1. [ServerRun](dz1/ServerRun.java)
Окно управления сервером. Кнопки логируют нажатие. Если нажать кнопку включения при запущенном сервере, отображается уведомление. Вторая кнопка работает аналогично.
### 2. [ChatClient](dz1/ChatClient.java)
Окно клиента чата. Отображает сохранённых пользователей. При неполном заполнении полей ввода, выдаёт уведомление о невозможности подключиться. При подключении загружает историю переписки из файла [log.txt](log.txt). Отправляет сообщение нажатием кнопки "Отправить", либо нажатием клавиши Enter и сразу перезаписывает файл.
