# GUÍA RÁPIDA DE INICIO

## ¿Qué se ha creado?

Esta es una aplicación completa de Android que te permite:
1. **Ver 100 posts** desde una API pública
2. **Buscar posts específicos** por número y ver sus comentarios

## Archivos Principales Creados/Modificados:

### 📱 Código Kotlin (Activities)
- `MainActivity.kt` - Pantalla principal con dos botones
- `Act1Activity.kt` - Ver lista de 100 posts
- `Act2Activity.kt` - Buscar posts por ID y ver comentarios

### 📦 Modelos de Datos
- `models/Post.kt` - Estructura de un post
- `models/Comment.kt` - Estructura de un comentario

### 🌐 Configuración de API
- `network/ApiService.kt` - Endpoints de la API
- `network/ApiClient.kt` - Configuración de Retrofit

### 🎨 Adaptadores para RecyclerView
- `adapters/PostAdapter.kt` - Muestra posts en lista
- `adapters/CommentAdapter.kt` - Muestra comentarios en lista

### 📄 Archivos de Interfaz (Layouts XML)
- `activity_main.xml` - Diseño del menú principal
- `activity_act1.xml` - Diseño de la lista de posts
- `activity_act2.xml` - Diseño de búsqueda de posts
- `item_post.xml` - Diseño de cada item en la lista de posts
- `item_comment.xml` - Diseño de cada comentario
- `item_background.xml` - Estilos visuales de los items

### ⚙️ Configuración del Proyecto
- `build.gradle` - Dependencias del proyecto (Retrofit, Coroutines, etc.)
- `AndroidManifest.xml` - Permisos e información de la app
- `local.properties` - Configuración del SDK
- `strings.xml` - Textos de la aplicación

### 📚 Documentación
- `README.md` - Guía completa en español
- `DOCUMENTACION.md` - Documentación técnica detallada
- `ARQUITECTURA.md` - Explicación de la arquitectura del proyecto
- `INSTRUCCIONES_COMPILACION.md` - Cómo compilar la app

---

## Primeros Pasos: Cómo Compilar y Ejecutar

### Paso 1: Abrir en Android Studio
1. Abre Android Studio
2. Selecciona "Open an existing Android Studio project"
3. Navega hasta la carpeta `Entregable_Retrofit`
4. Presiona "Open"

### Paso 2: Esperar a que sincronice
- Android Studio descargará todas las dependencias automáticamente
- Verás "Gradle sync completed successfully" en la parte inferior

### Paso 3: Ejecutar la aplicación
**Opción A: En un emulador**
1. Presiona el botón de play verde ▶️
2. Selecciona "Create New Emulator" o elige un emulador existente
3. Espera a que se inicie
4. La app se instalará automáticamente

**Opción B: En tu teléfono**
1. Conecta tu teléfono por USB
2. Activa "Modo de Desarrollador" en tu teléfono
3. Presiona el botón de play verde ▶️
4. Selecciona tu teléfono en la lista

### Paso 4: ¡Listo!
Ya puedes usar la aplicación:
- Presiona "Ver todos los posts" para descargar los 100 posts
- Presiona "Buscar post por ID" para buscar un post específico

---

## Estructura Visual de la App

### Pantalla 1: Menú Principal
```
┌────────────────────────────┐
│     Gestor de Posts        │
│                            │
│  ┌──────────────────────┐  │
│  │ Ver todos los posts  │  │ ← Botón 1
│  └──────────────────────┘  │
│                            │
│  ┌──────────────────────┐  │
│  │ Buscar post por ID   │  │ ← Botón 2
│  └──────────────────────┘  │
│                            │
└────────────────────────────┘
```

### Pantalla 2: Ver Todos los Posts
```
┌────────────────────────────┐
│ Todos los Posts            │
├────────────────────────────┤
│ ┌ Cargar Posts ──────────┐ │
│ └───────────────────────┘  │
├────────────────────────────┤
│ ┌────────────────────────┐ │
│ │ ID: 1                  │ │
│ │ sunt aut facere...     │ │
│ │                        │ │
│ └────────────────────────┘ │
│ ┌────────────────────────┐ │
│ │ ID: 2                  │ │
│ │ qui est esse           │ │
│ │                        │ │
│ └────────────────────────┘ │
│ ... (scroll para más)      │
└────────────────────────────┘
```

### Pantalla 3: Buscar Post
```
┌────────────────────────────┐
│ Buscar Post                │
├────────────────────────────┤
│ ┌──────────────────────┐   │
│ │ Escribe el número    │   │
│ └──────────────────────┘   │
│ ┌ Buscar ────────────────┐ │
│ └────────────────────────┘ │
├────────────────────────────┤
│ Comentarios:               │
│ ┌────────────────────────┐ │
│ │ Nombre: Juan García    │ │
│ │ Email: juan@test.com   │ │
│ └────────────────────────┘ │
│ ┌────────────────────────┐ │
│ │ Nombre: María López    │ │
│ │ Email: maria@test.com  │ │
│ └────────────────────────┘ │
│ ... (scroll para más)      │
└────────────────────────────┘
```

---

## ¿Qué Tecnologías Usa?

- **Kotlin** - Lenguaje de programación moderno para Android
- **Retrofit** - Para descargar datos de la internet
- **Gson** - Para convertir JSON a datos que Kotlin entiende
- **Coroutines** - Para no "congelar" la pantalla mientras se descargan datos
- **RecyclerView** - Para mostrar listas grandes de forma eficiente

---

## Solución Rápida de Problemas

### "Gradle Sync Failed"
→ Haz clic en "Sync Now" o File > Sync Project with Gradle Files

### La app se abre pero está vacía
→ Asegúrate de tener conexión a Internet. Presiona el botón "Cargar Posts"

### No aparecen comentarios
→ El número del post debe estar entre 1 y 100

### El botón no hace nada
→ Espera a que termine de cargar. Verás un icono rotatorio

---

## Próximos Pasos (Opcionales)

Cuando hayas entendido el funcionamiento, puedes:
- Cambiar los colores en `res/values/colors.xml`
- Cambiar los textos en `res/values/strings.xml`
- Agregar más funcionalidades
- Publicar tu app en Google Play Store

---

## Información de Contacto

Para dudas sobre Android, Kotlin o APIs:
- Documentación oficial: https://developer.android.com/
- Retrofit docs: https://square.github.io/retrofit/
- Kotlin docs: https://kotlinlang.org/docs/

---

**Versión**: 1.0  
**Creado**: Marzo 2026  
**Estado**: Completo y funcional ✅
