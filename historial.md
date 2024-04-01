# Diario del proyecto

### Dia 11 (Lunes)
Empecé creando el modelo de datos y el figma

### Dia 12 (Martes)
Empecé terminando el modelo de datos y del figma quedaban algunos detalles

### Dia 13 (Miercoles)
Ya terminé el modelo de datos y el figma y lo subi al repositorio de github

### Dia 14 (Jueves)
No hice nada

### Dia 15 (Viernes)
Este día empecé creando el proyecto de la app donde hice un poco del login

### Dia 16 (Sabado)
En este dia hice un poco de la verificación de usuarios con la api de Google(Firebase)

### Dia 17 (Domingo)
Terminé de hacer la verificación de usuario y el registro

### Dia 18 (Lunes)
Estuve haciendo el bottom bar de la app para despues de loguearse se mostrase

### Día 19 (Martes)
Intentando hacer el bottom bar me dio muchos errores hasta el punto de que ni funcionaba y no sabia que hacer, así que estoy haciendo un proyecto nuevo donde voy a ir más rápido y lo voy a hacer de la manera más simple, que funcione y despues hago lo del modelo de datos

### Día 20 (Miercoles)
Ya tengo el proyecto creado donde ya tengo las primeras pantallas básicas, donde tengo el login y el register (sin su autenticación todavía, pero que algunas condiciones como el tener que escribir para poder habilitar el boton), tengo también el navbar de arriba y abajo con iconos que realizan el cambio de pantallas, donde la primera pantalla es el blog donde la gente sube los post, la pantalla del usuario donde solo hay un boton para cerrar la sesión con un mensaje de alerta para asegurarse si se quiere cerrar la sesion, y por ultimo he realizado cambios en el color de la aplicacion.

### Día 21 (Jueves)
Hoy no hice demasiada cosa, sino solo miré un video en donde puedo utiliza un SwipeRefresh que es el icono redondo que se utiliza para poder actualizar la pantalla cuando deslizas hacia abajo y lo ultimo que hice fue modificar un poco los los card del blog(todavia me queda hacer alguna modificaciones visuales para los post). Aqui abajo pongo el video donde hice el SwipeRefresh, pero tuve que hacer algunas modificaciones porque no funcionaba el rememberPullRefresh:
[Cómo Implementar Swipe Refresh en Jetpack Compose y Android - PullToRefresh](https://www.youtube.com/watch?v=5PAMCy6JCHk&list=PLFV6hE_GSkxWv3sdBR2yeoYvdG_hohPwP&index=31&ab_channel=MartinKiperszmid%7CProgramador)

### Día 22 (Viernes)
Hoy realizé un splash screen que es basicamente esa pantalla de antes que inicies una app te sale una pantalla donde sale el logo y de quien fue hecho, tambien estuve retocando un poco el BlogScreen donde hice mas bonita el post donde se van a subir las imagenes, de momento no es funcional. Por ultimo agregé la fuente "righteous" para mi app siguiendo la guia de este video:
[Como Usar Fuentes Personalizadas en Jetpack Compose](https://www.youtube.com/watch?v=mIG_KEGr7Pc&ab_channel=MartinKiperszmid%7CProgramador).
Y este para realizar el splash screen:
[SPLASH SCREEN en Android Studio con Jetpack Compose](https://www.youtube.com/watch?v=Ww16yyN4noo&ab_channel=MoureDevbyBraisMoure)

### Día 23 (Sábado)
No hice nada

### Día 24 (Domingo)
Hoy estuve realizando la pantalla del calendario de planificación de recetas en donde hice un calednario que detecta el dia en el que estamos y borra los dias anteriores y tiene como limite los dias que tiene el mes, tengo pensado que tenga dos iconos en donde pueda combbiar de mes pero parece que no me sale, pero lo seguire intentando y por ultimo estuve haciendo unos card rápido para pobbra como se veia, todavia tengo que retocarlo

### Día 25 (Lunes)
Hoy estuve terminando de hacer las cards de PlanificationScreen, en donde ya tiene un poco de funcionalidad, y ahora estoy empezando a hacer la llamada a la api, en este caso utilizo la api TheMealDB para las recetas ya que es la única que he encoontrado para esto, ya que las demás, como Spoonacular o Edamam se necesita realizar un pago de subscripión y para el ultimo es muy raro el utilizar su api ya que su implementacion es rara y no tiene lo que necesito, asi que opté por TheMealDB. Todavía me faltan algunas cosas para la implementacion del TheMealDB 

### Día 26 (Martes)
Hoy estuve realizando la llamada a la Api tomando como referencia este github, pero presento un problema grande y es el de que cuando entro a la pantalla de receta a través del bottombar se realiza un cierre forzoso en la app, y yo creo que tiene que ser un problema ya sea el propio emulador que no funciona el internet y tendría que arreglarlo o de mi app, que tenga algun error en lo que es la navegacion a esa pantalla, pero no creo porque cuando estaba tomando como referencia el github lo estaba probando directamente y tampoco funciona, asi que espero que sea solo el emulador y lo pueda arreglar, porque sino me parece que me voy a quedar un poco atascado
[YumYumApp](https://github.com/LcsMilhan/YumYumApp/tree/master/app/src/main/java/com/example/yumyum)

### Día 27 (Miercoles)
Hoy estuve intentado hacer lo de la api, pero parece que lo voy a dejar, porque aparte de que no me sale, tambien es porque van mal las apis que encuentor, no están bien establecidos los get para poder realizar las llamadas y encima no hay al menos una guía reciente para poder hacer esas llamadas. Además, las apis que veo hay que realizar una subscripción, Así que he decidido que voy a utilizar Firebase que es una base de datos de Google en donde se puede realizar la autenticación de usuarios y la base de datos es en tiempo real, perfecto para mi ya que puedo poner una lista de recetas por defecto y si quiero crear otra receta lo puedo hacer fácil, ademas que sirve para que los usuarios puedan hacer sus recetas y subirlas para que otros lo vean

### Día 28 (Hoy)
Hoy estuve modificando el proyecto y empecé con lo de Firebase, ya tengo hecho la autenticación básica del usuario, ya lo que sería es una autenticación mas amplia, como cambiar nombre de usuario, cerrar sesion, etc. De momento solo hay de crear usuario e iniciar sesion con google o a traves de la app. Mañana empezaré con los usuarios para que puedan subir post y seguir a otros usuarios, ver sus seguidores y los que sigue y los post que ha subido el usuario

### Día 29 (Viernes)
Hoy no hice nada

### Día 30 (Sabado)
Hoy estuve intentando hacer la pantalla del post en Firebase Firestore pero parece que no funciona, porque no me sale, y se me cierra la app cada vez que lo intento hacer y ya no se que más hacer, me parece que lo voy a tener que dejar. Me pondré a hacer como si fuese un CRUD de recetas donde el usuario se logueé y pueda crear sus recetas y ver recetas y sus favoritos.

### Día 31 (Domingo)
Hoy estuve intentando hacer que sea una base de datos con Room para hacer las recetas, pero parece que ahora cada vez que intento entrar se me cierra la app, y no entiendo porque y mira que veo lo que hacen otros, pero es que a mi no me funciona

### Día 1 (Hoy)
Hoy por la mañana realizé un cambio en mi proyecto donde cambie la funcionalidad del bottom bar y ahora funciona correctamente haciendo el navController, despues estuve intentando poner el Room para almacenar los datos en local pero me daba error y me echaba de la app, lo intentaré en casa. He estado mirando el logcat para ver cual era el error y al final lo pude solucionar, era un error tonto, pero hacía que se me cerrase la app.
### VideoTutorial
[Login Screen en Android Jetpack Compose. Mostrar y ocultar password. Validación de campos.](https://www.youtube.com/watch?v=lC_mgTqiaSM&ab_channel=Gibr%C3%A1nGarc%C3%ADa)

[Login y creación de usuarios en Firebase con Android Jetpack Compose: Authentication y FireStore](https://www.youtube.com/watch?v=NFot9_bSFhw&ab_channel=Gibr%C3%A1nGarc%C3%ADa)

[Cómo Implementar Swipe Refresh en Jetpack Compose y Android - PullToRefresh](https://www.youtube.com/watch?v=5PAMCy6JCHk&list=PLFV6hE_GSkxWv3sdBR2yeoYvdG_hohPwP&index=31&ab_channel=MartinKiperszmid%7CProgramador)

[Como Usar Fuentes Personalizadas en Jetpack Compose](https://www.youtube.com/watch?v=mIG_KEGr7Pc&ab_channel=MartinKiperszmid%7CProgramador).

[SPLASH SCREEN en Android Studio con Jetpack Compose](https://www.youtube.com/watch?v=Ww16yyN4noo&ab_channel=MoureDevbyBraisMoure)
