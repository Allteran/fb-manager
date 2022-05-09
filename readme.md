## Документація
### Запуск
1. Для запуску поекту ви можете згенерувати файл типу <code>*.jar</code> за допомогою <code>gradle</code>-команди: <code> ./gradlew build </code>
В такому випадку ви маєте до запуску <code>*.jar</code>-файлу створити **PostgreSQL** базу данних <code>fb_manager_db</code> та користувача <code>fb_manager_admin</code>

2. Або для запуску Ви можете завантажити <code>docker-compose.yml</code>-файл та запустити генерування готового docker-образу за допомогою команди <code>docker-compose up</code>

### ТЕСТОВІ ДАННІ
Для заповнення бази початковими даними скористайтесь шаблонами запитів для **Postman**. 
1. Використайте дані з файлу <code>init-teams.json</code> в **Postman** для заведення даних про команди.
2. Наступним кроком використайте данні з файлу <code>init-teams.json</code> для заведення даних про наявні команди. <br>
Все. У вас є необхідні початкові данні для роботи з сервісом
## Структура сутності моделей даних:
### Команда (Team)
1. __id__ [LONG] - унікальний ідентифікатор
2. __name__ [STRING] - назва команди
3. __transferFeeRate__ [DOUBLE] - ставка комісії команди
4. __account__ [DOUBLE] - сума коштів на рахунку команди
### Гравець (Player)
1. __id__ [LONG] - унікальний ідентифікатор
2. __firstName__ [STRING] - ім'я гравця
3. __lastName__ [STRING] - прізвище гравця
4. __experienceMonth__ [INTEGER] - кількість місяців досвіду
5. __ageYear__ [INTEGER] - кількість повних років гравця
6. __position__ [STRING] - позиція гравця на полі
7. __team__ [TEAM] - команда за яку грає гравець. При внесені даних вноситься в повному вигляді, а в базі даних зберігається тільки у вигляді **id** самої команди.
### Трансфер (Transfer)
1. __id__ [LONG] - унікальний ідентифікатор. Генерується автоматично за допомогою Hibernate
2. __teamFrom__ [TEAM] - команда від якої проводиться трансфер. В базі зберігається у вигляді **id** команди
3. __teamTo__ [TEAM] - команда до якої проводиться трансфер. В базі зберігається у вигляді **id** команди
4. __player__ [PLAYER] - гравець, над яким проводиться операція трансферу
5. __price__ [DOUBLE] - вартість трансферу = кількість місяців досвіду гравця * 100000 / вік гравця у роках
6. __totalAmount__ [DOUBLE] - повна сума трансферу (комісія + вартість трансферу)
### CRUD-Функціонал над Гравцем
* <code>GET: api/v1/team/list</code> - повертає список всіх команд та HttpStatus.OK, якщо все ок, та HttpStatus.BAD_REQUEST, якщо все не ок
* <code>GET: api/v1/team/{id}</code> - повертає необхідну команду за заданим id
* <code>POST: api/v1/team/new</code> - створює нову команду. У якості параметру приймає команду у вигляді JSON-рядка, та повертає той же створений об'єкт та HttpStatus.OK, якщо все ок, та HttpStatus.BAD_REQUEST, якщо не ок
* <code>POST: api/v1/team/new/list</code> - робить все те саме, що і попередній запит, тільки створює не одну команду, а список команд. Відповідно приймає в себе також список команд. Та повертає відповідний статус (ок чи не ок).
* <code>PUT: api/v1/team/edit/{id}}</code> - оновлює команду. У якості параметру запиту по id приймає команду також у якості @RequestBody приймає нову версію тої ж команди
* <code>DELETE: api/v1/team/delete/{id}}</code> - видаляє команду

### CRUD-Функціонал над Гравцем
* <code>GET: api/v1/player/list</code> - повертає список всіх гравців та HttpStatus.OK, якщо все ок, та HttpStatus.BAD_REQUEST, якщо все не ок
* <code>GET: api/v1/player/{id}</code> - повертає необхідного гравця за заданим id
* <code>POST: api/v1/player/new</code> - створює нового гравця. У якості параметру приймає гравця у вигляді JSON-рядка, та повертає той же створений об'єкт та HttpStatus.OK, якщо все ок, та HttpStatus.BAD_REQUEST, якщо не ок
* <code>POST: api/v1/player/new/list</code> - робить все те саме, що і попередній запит, тільки створює не одного гравця, а список гравців. Відповідно приймає в себе також список гравців.
* <code>PUT: api/v1/player/edit/{id}}</code> - оновлює гравця. У якості параметру запиту по id приймає гравця також у якості @RequestBody приймає нову версію того ж гравця
* <code>DELETE: api/v1/player/delete/{id}}</code> - видаляє гравця

### CRUD-Функціонал над Трансфером
* <code>POST: api/v1/transfer/new/from={fromId}&to={toId}</code> - створює новий трансфер між клубами щодо гравця. У якості параметрів запиту приймає **fromId** - ідентифікатор команди, яка продає, **toId** - ідентифікатор команди, яка купляє. В якості @RequestBody приймає гравця. Якщо у команди-покупця не вистачає коштів - сервіс вкидує помилку <code>CustomException</code> та також повертає відповідний статус (BAD_REQUEST)
* <code>GET: api/v1/transfer/history/player={playerId}</code> - повертає "історію" трансферу гравця. У якості параметру запиту приймає id гравця (**playerId**)
* <code>GET: api/v1/transfer/history/team={teamId}</code> - також повертає "історію" трансферу, але не гравця, а команд. У якості параметру запиту приймає id команди (**teamId**). 
