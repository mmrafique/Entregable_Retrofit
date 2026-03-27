# ARQUITECTURA Y FLUJO DEL PROYECTO

## Estructura del Proyecto (Árbol de Carpetas)

```
Entregable_Retrofit/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/entregable_retrofit/
│   │   │   │   ├── MainActivity.kt                    (Pantalla principal)
│   │   │   │   ├── Act1Activity.kt                   (Lista de posts)
│   │   │   │   ├── Act2Activity.kt                   (Búsqueda de post)
│   │   │   │   │
│   │   │   │   ├── models/
│   │   │   │   │   ├── Post.kt                       (Data class Post)
│   │   │   │   │   └── Comment.kt                    (Data class Comment)
│   │   │   │   │
│   │   │   │   ├── network/
│   │   │   │   │   ├── ApiService.kt                 (Interface de API)
│   │   │   │   │   └── ApiClient.kt                  (Configuración Retrofit)
│   │   │   │   │
│   │   │   │   └── adapters/
│   │   │   │       ├── PostAdapter.kt                (RecyclerView para posts)
│   │   │   │       └── CommentAdapter.kt             (RecyclerView para comentarios)
│   │   │   │
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml             (Menú principal)
│   │   │   │   │   ├── activity_act1.xml             (Lista de posts)
│   │   │   │   │   ├── activity_act2.xml             (Búsqueda de post)
│   │   │   │   │   ├── item_post.xml                 (Item de post)
│   │   │   │   │   └── item_comment.xml              (Item de comentario)
│   │   │   │   │
│   │   │   │   ├── drawable/
│   │   │   │   │   └── item_background.xml           (Estilos de items)
│   │   │   │   │
│   │   │   │   ├── values/
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   └── themes.xml
│   │   │   │   │
│   │   │   │   └── values-night/
│   │   │   │       └── themes.xml
│   │   │   │
│   │   │   └── AndroidManifest.xml
│   │   │
│   │   ├── test/
│   │   └── androidTest/
│   │
│   ├── build.gradle
│   └── proguard-rules.pro
│
├── gradle/
│   └── wrapper/
│
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── local.properties
│
├── README.md                        (Documentación principal)
├── DOCUMENTACION.md                 (Documentación detallada)
└── INSTRUCCIONES_COMPILACION.md    (Guía de compilación)
```

---

## Flujo de la Aplicación

### Flujo General

```
┌─────────────────────────────────────────────────────────┐
│                   INICIO DE LA APP                      │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  MainActivity (Pantalla Principal)                      │
│  ┌─────────────────────────────────────────────────┐   │
│  │ • Título: "Gestor de Posts"                    │   │
│  │ • Botón 1: "Ver todos los posts"               │   │
│  │ • Botón 2: "Buscar post por ID"                │   │
│  └─────────────────────────────────────────────────┘   │
│              ↙                             ↖             │
│         Botón 1                        Botón 2          │
│             ↓                              ↓             │
└─────────────────────────────────────────────────────────┘
        ↓                                    ↓
    Act1Activity                        Act2Activity
    (Lista Completa)                    (Búsqueda)
```

### Flujo de Act1Activity (Ver todos los posts)

```
Clic en "Ver todos los posts"
            ↓
    Act1Activity se abre
            ↓
  Espera a que el usuario
  presione "Cargar Posts"
            ↓
  Usuario presiona botón
            ↓
  Se muestra ProgressBar (icono de carga)
            ↓
  ┌─────────────────────────────────┐
  │   Coroutine en Dispatchers.IO   │
  │                                 │
  │  Retrofit realiza:              │
  │  GET /posts                     │
  │                                 │
  │  Retorna: List<Post>            │
  └─────────────────────────────────┘
            ↓
  Retorna a Main Thread
            ↓
  PostAdapter actualiza lista
            ↓
  RecyclerView muestra los items
            ↓
  Se oculta ProgressBar
            ↓
  Usuario puede hacer scroll
  para ver los 100 posts
```

### Flujo de Act2Activity (Buscar post específico)

```
Clic en "Buscar post por ID"
            ↓
    Act2Activity se abre
            ↓
  Usuario ingresa un número
  (validación: 1-100)
            ↓
  Usuario presiona "Buscar"
            ↓
  ┌─────────────────────────────────┐
  │   Validaciones en Kotlin        │
  │                                 │
  │   • ¿Campo vacío?               │
  │   • ¿Número válido?             │
  │   • ¿Entre 1 y 100?             │
  └─────────────────────────────────┘
            ↓
  Si validation PASS → Continúa
  Si validation FAIL → Muestra Toast
            ↓
  Se muestra ProgressBar
            ↓
  ┌─────────────────────────────────┐
  │   Coroutine Dispatchers.IO      │
  │                                 │
  │  Retrofit realiza:              │
  │  GET /posts/{id}                │
  │  GET /posts/{id}/comments       │
  │                                 │
  │  Retorna: Post + List<Comment>  │
  └─────────────────────────────────┘
            ↓
  Retorna a Main Thread
            ↓
  Actualiza título con info del post
            ↓
  CommentAdapter actualiza lista
            ↓
  RecyclerView muestra comentarios
            ↓
  Se oculta ProgressBar
            ↓
  Usuario puede hacer scroll
  para ver todos los comentarios
```

---

## Arquitectura MVC Simplificada

```
┌─────────────────────────────────────────────────────────┐
│                      VIEW (UI)                          │
├─────────────────────────────────────────────────────────┤
│ • MainActivity, Act1Activity, Act2Activity             │
│ • Layouts XML                                          │
│ • RecyclerViews                                        │
│ • Buttons, EditText, ProgressBar                       │
└─┬───────────────────────────────────────────────────────┘
  │
  │ Interacción del usuario
  │
  ↓
┌─────────────────────────────────────────────────────────┐
│                   CONTROLLER (Logic)                    │
├─────────────────────────────────────────────────────────┤
│ • Activities (manejan eventos)                         │
│ • Coroutines (lógica asincrónica)                      │
│ • Adapters (transforman datos en vistas)               │
└┬───────────────────────────────────────────────────────┘
  │
  │ Solicita datos
  │
  ↓
┌─────────────────────────────────────────────────────────┐
│                   MODEL (Datos)                         │
├─────────────────────────────────────────────────────────┤
│ • ApiClient (Retrofit configuration)                   │
│ • ApiService (interface de endpoints)                  │
│ • Post, Comment (Data classes)                         │
│ • https://jsonplaceholder.typicode.com/ (API remota)   │
└─────────────────────────────────────────────────────────┘
```

---

## Flujo de Datos (Data Binding)

```
1. Usuario presiona botón
                ↓
2. OnClickListener se dispara
                ↓
3. Se inicia una Coroutine
                ↓
4. Se cambia de contexto a Dispatchers.IO (thread de fondo)
                ↓
5. Retrofit realiza solicitud HTTP GET
                ↓
6. Servidor responde con JSON
                ↓
7. Retrofit convierte JSON a Kotlin objects (Post/Comment)
                ↓
8. Se retorna al Main Thread (Dispatchers.Main)
                ↓
9. El Adapter actualiza su lista interna
                ↓
10. notifyDataSetChanged() informa al RecyclerView
                ↓
11. RecyclerView redibuja los items con los nuevos datos
                ↓
12. Usuario ve la información en pantalla
```

---

## Componentes Clave

### 1. Retrofit + Gson
```
JSON RawAPI    → Retrofit    → Gson    → Kotlin Data Class
                (HTTP Call)  (Parsing)
```

### 2. Coroutines
```
Main Thread (UI)
        ↓
    launch {
        withContext(Dispatchers.IO) {
            // Código que se ejecuta en background
            ApiClient.apiService.getAllPosts()
        }
        // Retorna automaticamente a Main Thread
        // Actualizar UI
    }
```

### 3. RecyclerView Pattern
```
RecyclerView
    ↓
    ├─ LayoutManager (LinearLayoutManager)
    │  Posición: vertical, horizontal, grid
    │
    ├─ Adapter (PostAdapter / CommentAdapter)
    │  • onCreateViewHolder() → Crea vistas
    │  • onBindViewHolder()   → Llena con datos
    │  • getItemCount()       → Número de items
    │
    └─ ViewHolder (PostViewHolder / CommentViewHolder)
       • references a views (TextView, etc)
       • método bind() para mostrar datos
```

---

## Llamadas a la API

### Endpoints utilizados:

1. **Obtener todos los posts**
   ```
   GET /posts
   Response: Array de 100 Posts
   ```

2. **Obtener un post específico**
   ```
   GET /posts/{id}
   Response: Un objeto Post
   ```

3. **Obtener comentarios de un post**
   ```
   GET /posts/{id}/comments
   Response: Array de Comments
   ```

### Estructura de las respuestas:

**Response de /posts:**
```json
[
  {
    "userId": 1,
    "id": 1,
    "title": "...",
    "body": "..."
  },
  ...
]
```

**Response de /posts/{id}/comments:**
```json
[
  {
    "postId": 1,
    "id": 1,
    "name": "...",
    "email": "...",
    "body": "..."
  },
  ...
]
```

---

## Gestión de Estados

```
Estado: IDLE (inicial)
    ↓ Usuario presiona botón
Estado: LOADING
    ↓ Se muestra ProgressBar
    ↓ Coroutine descarga datos
Estado: SUCCESS
    ↓ Datos llegan correctamente
Estado: DISPLAY
    ↓ RecyclerView muestra datos
    ↓ ProgressBar desaparece

O en caso de error:

Estado: LOADING
    ↓
Estado: ERROR
    ↓ Se muestra Toast con mensaje de error
```

---

## Dependencias Importadas

### Retrofit y Gson
- `com.squareup.retrofit2:retrofit` - Framework HTTP
- `com.squareup.retrofit2:converter-gson` - Convierte JSON a Kotlin

### Coroutines
- `org.jetbrains.kotlinx:kotlinx-coroutines-core` - Core de Coroutines
- `org.jetbrains.kotlinx:kotlinx-coroutines-android` - Integración con Android

### Lifecycle
- `androidx.lifecycle:lifecycle-runtime-ktx` - Lifecycle-aware components

### UI
- `androidx.appcompat:appcompat` - AppCompatActivity
- `androidx.recyclerview:recyclerview` - RecyclerView
- `com.google.android.material:material` - Material Design

---

Este documento muestra la arquitectura completa y el flujo de datos de la aplicación.
