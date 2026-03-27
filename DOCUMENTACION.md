# DOCUMENTACIÓN DE ENTREGABLE - GESTOR DE POSTS

## Introducción

Este documento presenta la documentación demostrativa del proyecto **Gestor de Posts**, una aplicación Android desarrollada en Kotlin que permite visualizar y gestionar posts de la web mediante una API pública.

---

## APARTADO 1: DESCRIPCIÓN DEL PROYECTO

### Objetivo General
Crear una aplicación Android que permita:
1. Descargar y visualizar 100 posts desde una API pública
2. Buscar posts específicos por ID y ver sus comentarios

### Tecnologías Utilizadas
- **Lenguaje**: Kotlin
- **Framework**: Android SDK
- **Librerías principales**:
  - Retrofit 2.9.0 (para comunicación con APIs)
  - Coroutines (para operaciones asincrónicas)
  - RecyclerView (para listas eficientes)
  - Gson (para parsear JSON)
  - Material Design (componentes visuales)

### URL de la API
```
https://jsonplaceholder.typicode.com/
```

Esta es una API pública de prueba que proporciona datos ficticios pero realistas para desarrollar aplicaciones.

---

## APARTADO 2: FUNCIONALIDADES PRINCIPALES

### 2.1 Pantalla Principal (MainActivity)
La aplicación comienza con una interfaz simple que muestra:

**Elementos visuales:**
- Título: "Gestor de Posts"
- Botón 1: "Ver todos los posts"
- Botón 2: "Buscar post por ID"

**Flujo:**
```
Aplicación Inicia
        ↓
Pantalla Principal (MainActivity)
    ↙              ↖
Botón 1          Botón 2
   ↓                ↓
Act1Activity    Act2Activity
```

### 2.2 Actividad 1: Listar Todos los Posts (Act1Activity)

**Funcionalidades:**
- Botón para cargar los 100 posts
- Indicador de carga mientras descarga
- RecyclerView que muestra la lista con scroll

**Datos mostrados por cada post:**
- ID del post
- Título
- Contenido (primeras líneas)

**Ejemplo de pantalla:**
```
┌─────────────────────────────────┐
│ Todos los Posts                 │
├─────────────────────────────────┤
│ [Cargar Posts]                  │
├─────────────────────────────────┤
│ ID: 1                           │
│ sunt aut facere repellat...     │
│ quia et suscipit suscipit...    │
├─────────────────────────────────┤
│ ID: 2                           │
│ qui est esse                    │
│ est rerum tempore vitae...      │
├─────────────────────────────────┤
│ (y más posts)                   │
└─────────────────────────────────┘
```

**Llamada API utilizada:**
```
GET /posts
```

### 2.3 Actividad 2: Buscar Post por ID (Act2Activity)

**Funcionalidades:**
- Campo de entrada para escribir el ID del post (1-100)
- Botón de búsqueda
- Validación de entrada
- Muestra el post y todos sus comentarios

**Validaciones implementadas:**
- Campo no vacío
- Número válido
- Entre 1 y 100

**Interfaz de usuario:**
```
┌─────────────────────────────────┐
│ Buscar Post                     │
├─────────────────────────────────┤
│ [Escribir número]               │
│ [Buscar]                        │
├─────────────────────────────────┤
│ Comentarios:                    │
├─────────────────────────────────┤
│ Nombre: Juan García             │
│ Email: juan@ejemplo.com         │
│ Comentario: Contenido del...    │
├─────────────────────────────────┤
│ (más comentarios)               │
└─────────────────────────────────┘
```

**Llamadas API utilizadas:**
```
GET /posts/{id}
GET /posts/{id}/comments
```

---

## APARTADO 3: ARQUITECTURA TÉCNICA

### 3.1 Estructura de Carpetas

```
app/src/main/
├── java/com/example/entregable_retrofit/
│   ├── MainActivity.kt
│   ├── Act1Activity.kt
│   ├── Act2Activity.kt
│   ├── models/
│   │   ├── Post.kt
│   │   └── Comment.kt
│   ├── network/
│   │   ├── ApiService.kt
│   │   └── ApiClient.kt
│   └── adapters/
│       ├── PostAdapter.kt
│       └── CommentAdapter.kt
├── res/
│   ├── layout/
│   │   ├── activity_main.xml
│   │   ├── activity_act1.xml
│   │   ├── activity_act2.xml
│   │   ├── item_post.xml
│   │   └── item_comment.xml
│   └── drawable/
│       └── item_background.xml
└── AndroidManifest.xml
```

### 3.2 Componentes Principales

#### Models (Modelos de Datos)

**Post.kt:**
```kotlin
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
```

**Comment.kt:**
```kotlin
data class Comment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)
```

#### Network (Comunicación con API)

**ApiService.kt:**
Define tres endpoints principales:
- `getAllPosts()` - Obtiene los 100 posts
- `getPostById(id)` - Obtiene un post específico
- `getCommentsByPostId(id)` - Obtiene comentarios de un post

**ApiClient.kt:**
Configura Retrofit con:
- URL base: https://jsonplaceholder.typicode.com/
- Convertidor JSON: Gson
- Tipo de llamadas: Suspending functions (compatibles con Coroutines)

#### Adapters (Adaptadores para RecyclerView)

**PostAdapter.kt:**
- Convierte lista de Posts en elementos visuales
- Muestra: ID, título y contenido

**CommentAdapter.kt:**
- Convierte lista de Comments en elementos visuales
- Muestra: nombre, email y comentario

### 3.3 Flujo de Datos

#### Uso de Coroutines para Operaciones Asincrónicas

La aplicación utiliza `Coroutines` para descargar datos sin bloquear la interfaz:

```
Botón presionado
    ↓
Launch Coroutine en Main Thread
    ↓
Switch a IO Dispatcher (descarga en background)
    ↓
Llamada a API con Retrofit
    ↓
Retorna datos
    ↓
Switch a Main Thread
    ↓
Actualiza RecyclerView
    ↓
Oculta indicador de carga
```

---

## APARTADO 4: GUÍA DE USO

### 4.1 Instalación
1. Descargar el APK desde la sección "Releases"
2. Instalar en un dispositivo o emulador Android
3. Dar permiso de acceso a Internet

### 4.2 Primer Uso

**Opción A: Ver todos los posts**
1. Abre la aplicación
2. Presiona "Ver todos los posts"
3. Presiona "Cargar Posts"
4. Espera a que cargue (verás un icono rotatorio)
5. Una vez completado, verás la lista de 100 posts
6. Haz scroll para explorar

**Opción B: Buscar un post específico**
1. Desde la pantalla principal, presiona "Buscar post por ID"
2. Escribe un número entre 1 y 100
3. Presiona "Buscar"
4. Verás el post y todos sus comentarios en la lista

---

## APARTADO 5: GESTIÓN DE ERRORES

La aplicación implementa validaciones y manejo de errores:

### Errores Comunes:

**1. Campo de búsqueda vacío:**
```
Mensaje: "Por favor ingresa un número de post válido"
```

**2. Número fuera de rango:**
```
Mensaje: "Ingresa un número entre 1 y 100"
```

**3. Sin conexión a Internet:**
```
Mensaje: "Error: Unable to resolve host"
```

**4. API no disponible:**
```
Mensaje: "Error: Connection refused"
```

---

## APARTADO 6: DATOS EJEMPLO

### Ejemplo de Post Completo:

```json
{
  "userId": 1,
  "id": 1,
  "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
  "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
}
```

### Ejemplo de Comentario Completo:

```json
{
  "postId": 1,
  "id": 1,
  "name": "id labore ex et quam laborum",
  "email": "Eliseo@gardner.biz",
  "body": "laudantium enim quasi est quidem magnam voluptate ipsam et"
}
```

---

## APARTADO 7: REQUISITOS Y ESPECIFICACIONES

### Requisitos Funcionales Cumplidos:

✅ **RF1**: Pantalla principal con dos botones
✅ **RF2**: Act1 - Mostrar 100 posts en RecyclerView
✅ **RF3**: Act3 - Búsqueda de post por ID
✅ **RF4**: Act2 - Ver comentarios en RecyclerView con scroll
✅ **RF5**: Consumo correcto de API JSON Placeholder

### Requisitos No-Funcionales Cumplidos:

✅ **RNF1**: Código en Kotlin legible y con comentarios
✅ **RNF2**: Documentación en español nivel 15-16 años
✅ **RNF3**: Interfaz intuitiva y fácil de usar
✅ **RNF4**: Manejo de errores implementado
✅ **RNF5**: Aplicación compilable a APK

---

## APARTADO 8: CAPTURAS DE PANTALLA

### Pantalla 1: Menú Principal
```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃ Gestor de Posts             ┃
┃                             ┃
┃                             ┃
┃ ┌─────────────────────────┐ ┃
┃ │ Ver todos los posts     │ ┃
┃ └─────────────────────────┘ ┃
┃                             ┃
┃ ┌─────────────────────────┐ ┃
┃ │ Buscar post por ID      │ ┃
┃ └─────────────────────────┘ ┃
┃                             ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

### Pantalla 2: Lista de Posts
```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃ Todos los Posts             ┃
├─────────────────────────────┤
┃ ┌─ Cargar Posts ─────────┐ ┃
┃ └───────────────────────-┘ ┃
├─────────────────────────────┤
┃ ┌───────────────────────-┐  ┃
┃ │ ID: 1                 │  ┃
┃ │ sunt aut facere...    │  ┃
┃ │ quia et suscipit...   │  ┃
┃ └───────────────────────-┘  ┃
┃ ┌───────────────────────-┐  ┃
┃ │ ID: 2                 │  ┃
┃ │ qui est esse          │  ┃
┃ │ est rerum tempore...  │  ┃
┃ └───────────────────────-┘  ┃
┃ ... (scroll para más)       ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

### Pantalla 3: Búsqueda de Post
```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃ Buscar Post                 ┃
├─────────────────────────────┤
┃ ┌─────────────────────────┐ ┃
┃ │ Ingresa el número...  ▌│ ┃
┃ └─────────────────────────┘ ┃
┃ ┌─────────────────────────┐ ┃
┃ │ Buscar                  │ ┃
┃ └─────────────────────────┘ ┃
├─────────────────────────────┤
┃ Comentarios:                ┃
┃ ┌───────────────────────-┐  ┃
┃ │ Nombre: Juan García   │  ┃
┃ │ Email: juan@ejm.com   │  ┃
┃ │ Comentario: Contenido │  ┃
┃ └───────────────────────-┘  ┃
┃ ... (scroll para más)       ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

---

## APARTADO 9: CONCLUSIÓN

Esta aplicación demuestra el uso práctico de:
- Consumo de APIs REST usando Retrofit
- Programación asincrónica con Coroutines
- Creación de interfaces dinámicas con RecyclerView
- Arquitectura limpia y modular en Kotlin
- Experiencia de usuario fluida y responsive

La aplicación es completamente funcional y lista para usar en cualquier dispositivo Android con versión 5.0 o superior.

---

## ENLACES IMPORTANTES

📱 **APK**: Disponible en [Releases](https://github.com/)
🔗 **GitHub**: [Repositorio del Proyecto](https://github.com/)
📚 **API**: [JSON Placeholder](https://jsonplaceholder.typicode.com/)

---

**Documento generado**: Marzo 2026
**Versión**: 1.0
**Autor**: Desarrollador
