# Gestor de Posts - Aplicación Android

## Descripción General

Esta es una aplicación Android desarrollada en Kotlin que permite visualizar y gestionar posts de internet. La aplicación se conecta a una API pública (JSON Placeholder) para obtener información sobre posts y comentarios.

La aplicación tiene dos funciones principales:
1. **Ver todos los posts**: Muestra una lista de los 100 posts disponibles en la API
2. **Buscar post específico**: Permite buscar un post por su ID y ver todos sus comentarios

## Funcionalidades

### Pantalla Principal (Main Menu)
La pantalla inicial muestra dos botones:
- **Botón 1: Ver todos los posts** - Te lleva a la primera actividad donde puedes descargar y ver el listado completo de posts
- **Botón 2: Buscar post por ID** - Te lleva a la segunda actividad donde puedes introducir el número de un post (entre 1 y 100)

### Actividad 1: Lista de Posts (Act1Activity)
En esta pantalla puedes:
- Presionar el botón "Cargar Posts" para descargar los 100 posts desde la API
- Ver todos los posts en un listado con scroll
- Cada post muestra: ID, título y contenido

### Actividad 2: Búsqueda de Post (Act2Activity)
En esta pantalla puedes:
- Escribir el número de un post (entre 1 y 100)
- Presionar "Buscar" para obtener los detalles del post
- Ver todos los comentarios asociados al post
- Cada comentario muestra: nombre del usuario, correo y contenido

## Estructura del Proyecto

```
app/src/main/java/com/example/entregable_retrofit/
├── MainActivity.kt              # Pantalla principal con los dos botones
├── Act1Activity.kt              # Actividad para ver todos los posts
├── Act2Activity.kt              # Actividad para buscar post específico
├── models/
│   ├── Post.kt                  # Modelo de datos de un post
│   └── Comment.kt               # Modelo de datos de un comentario
├── network/
│   ├── ApiService.kt            # Interface con los endpoints de la API
│   └── ApiClient.kt             # Configuración de Retrofit
└── adapters/
    ├── PostAdapter.kt           # Adaptador para el RecyclerView de posts
    └── CommentAdapter.kt        # Adaptador para el RecyclerView de comentarios
```

## Tecnologías Utilizadas

- **Kotlin**: Lenguaje de programación principal
- **Retrofit**: Para hacer peticiones HTTP a la API
- **Coroutines**: Para ejecutar tareas en segundo plano sin bloquear la interfaz
- **RecyclerView**: Para mostrar listas de items de manera eficiente
- **Material Design**: Para los componentes visuales

## Cómo Funciona Internamente

### Flujo de Datos

1. **Conectar a la API**: La aplicación se conecta a `https://jsonplaceholder.typicode.com/` que es una API pública de prueba
2. **Descargar datos**: Usa Retrofit para hacer peticiones GET
3. **Mostrar en pantalla**: Los datos se muestran en RecyclerViews usando adaptadores personalizados
4. **Experiencia del usuario**: Muestra un indicador de carga mientras descarga los datos

### Datos que Utiliza

**Estructura de un Post**:
```json
{
  "userId": 1,
  "id": 1,
  "title": "Título del post",
  "body": "Contenido del post"
}
```

**Estructura de un Comentario**:
```json
{
  "postId": 1,
  "id": 1,
  "name": "Nombre del usuario",
  "email": "usuario@ejemplo.com",
  "body": "Contenido del comentario"
}
```

## Requisitos del Sistema

- Android 5.0 (API 21) o superior
- Conexión a Internet
- Acceso a la API JSON Placeholder

## Cómo Usar la Aplicación

### Paso 1: Iniciar la aplicación
Abre la aplicación y verás la pantalla principal con dos botones

### Paso 2: Opción A - Ver todos los posts
1. Presiona el botón "Ver todos los posts"
2. En la siguiente pantalla, presiona "Cargar Posts"
3. Espera a que se descarguen los datos (verás un icono de carga)
4. Una vez se complete, verás el listado de los 100 posts
5. Puedes hacer scroll para ver más posts

### Paso 3: Opción B - Buscar post específico
1. Regresa a la pantalla principal (usa el botón atrás)
2. Presiona el botón "Buscar post por ID"
3. Escribe un número entre 1 y 100
4. Presiona el botón "Buscar"
5. La aplicación mostrará el post y todos sus comentarios
6. Puedes hacer scroll para ver todos los comentarios

## Permisos Necesarios

La aplicación requiere el permiso `INTERNET` para conectarse a la API pública. Este permiso se solicita automáticamente cuando instalas la aplicación.

## Manejo de Errores

La aplicación tiene protección contra errores comunes:
- Si no hay conexión a Internet, mostrará un mensaje de error
- Si escribes un número inválido (menor que 1 o mayor que 100), te dirá que ingreses un número válido
- Si dejas en blanco el campo de búsqueda, te alertará

## Notas Importantes

- Los datos se descargan en tiempo real desde la API, por lo que necesitas conexión a Internet
- La primera carga puede tardar unos segundos según tu velocidad de conexión
- La API JSON Placeholder es solo para pruebas, los datos no son reales pero son útiles para desarrollar y probar aplicaciones
- Puedes descargar la aplicación múltiples veces sin problemas, los datos siempre serán los mismos

## Compilación y Construcción

Para construir la aplicación:

1. Abre Android Studio
2. Carga este proyecto
3. DejaQuequecargue todas las dependencias
4. Presiona "Run" o usa `gradle build`
5. La aplicación se compilará y podrá instalarse en un emulador o dispositivo real

## APK

El archivo APK compilado se encuentra en la sección "Releases" de este proyecto. Puedes descargarlo e instalarlo directamente en tu dispositivo Android.

## Información del Desarrollador

Esta aplicación fue creada como un proyecto de aprendizaje para practicar:
- Desarrollo Android con Kotlin
- Consumo de APIs REST con Retrofit
- Uso de RecyclerViews
- Programación asincrónica con Coroutines
- Diseño de interfaces de usuario

## Preguntas Frecuentes

**P: ¿Por qué tarda en cargar?**
R: La aplicación descarga datos de internet, por lo que la velocidad depende de tu conexión. Si tarda mucho, puede deberse a que la conexión es lenta o la API está ocupada.

**P: ¿Qué pasa si pongo un número mayor a 100?**
R: La aplicación te advertirá que ingreses un número válido entre 1 y 100.

**P: ¿Necesito crear una cuenta?**
R: No, la API es pública y no requiere autenticación.

**P: ¿Puedo modificar la aplicación?**
R: Sí, el código fuente está disponible y puedes personalizarlo según tus necesidades.

---

**Última actualización**: Marzo 2026
**Versión**: 1.0
