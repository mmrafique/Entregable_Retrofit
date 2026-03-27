# INSTRUCCIONES DE COMPILACIÓN

## Para compilar el APK en Android Studio:

### Método 1: Build > Build Bundle(s) / APK(s) > Build APK(s)
1. Abre Android Studio
2. Ve a **Build** → **Build Bundle(s) / APK(s)** → **Build APK(s)**
3. Espera a que termine la compilación
4. El APK se guardará en: `app/build/outputs/apk/debug/app-debug.apk`

### Método 2: Usando Terminal
```bash
cd c:\Users\User\Downloads\Entregable_Retrofit
.\gradlew.bat assembleDebug
```

El APK generado se encontrará en:
```
app\build\outputs\apk\debug\app-debug.apk
```

## Instalación en un dispositivo:

### Con Android Studio:
1. Conecta el dispositivo o abre un emulador
2. Presiona el botón **Run** (icono de play verde)
3. Selecciona el dispositivo
4. La app se instalará automáticamente

### Con ADB (Android Debug Bridge):
```bash
adb install app\build\outputs\apk\debug\app-debug.apk
```

### Instalación manual:
- Transfiere el APK al dispositivo
- Abre el archivo APK
- Presiona "Instalar"

## Requisitos previos:

- Android Studio instalado
- SDK de Android 32 o superior
- Java Development Kit (JDK) 8 o superior
- Conexión a Internet (para descargar dependencias)

## Solución de problemas:

### Si falla la compilación:
1. Ve a **File** → **Invalidate Caches / Restart**
2. Presiona **Invalidate and Restart**
3. Intenta compilar de nuevo

### Si no reconoce Kotlin:
1. Ve a **Tools** → **Kotlin** → **Configure Kotlin Plugin Updates**
2. Descarga la actualización más reciente

### Si faltan dependencias:
1. Ve a **File** → **Sync Now**
2. Espera a que descargue todas las dependencias
