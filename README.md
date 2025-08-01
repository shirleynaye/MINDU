
# MINDU – Aplicación de Diario de Mindfulness y Bienestar Personal

**MINDU** es una aplicación móvil desarrollada en Kotlin utilizando Jetpack Compose. Está diseñada para fomentar el bienestar emocional y la práctica del mindfulness a través del journaling guiado, la gestión de emociones y el acceso a contenido educativo. Su interfaz ordenada, minimalista y funcional permite a los usuarios realizar reflexiones diarias, identificar sus emociones y comprender los beneficios del autocuidado consciente.

---

## Funcionalidades principales

- **Pantalla de bienvenida**: Incluye una frase inspiradora sobre la salud mental.
- **Registro de usuario**: Permite establecer nombre de usuario y un PIN de acceso (almacenado de forma segura con DataStore).
- **Autenticación por PIN**: Valida el acceso del usuario mediante una pantalla de entrada de PIN.
- **Pantalla principal personalizada**: Muestra el nombre del usuario y ofrece acceso directo a funciones clave.
- **Selección de emociones**: El usuario puede seleccionar su estado emocional actual y recibir una frase motivadora junto con una sugerencia específica (journaling o ejercicio de respiración).
- **Journaling guiado**: Permite al usuario registrar reflexiones estructuradas con base en preguntas como: ¿qué sintió hoy?, ¿qué aprendió?, ¿por qué está agradecido?, entre otras.
- **Sección educativa**: Contiene una introducción al mindfulness, sus beneficios y su aplicación cotidiana, complementada con una animación ilustrativa.
- **Navegación intuitiva y diseño visual consistente**: Utiliza colores suaves, formas redondeadas y una disposición lógica de elementos.

---

## Registro de cambios (Changelog)

| Versión | Fecha           | Cambios realizados                                                                   |
|---------|------------------|-------------------------------------------------------------------------------------|
| 0.1.0   | 20 de julio 2025 | Estructura inicial del proyecto y configuración de navegación básica.               |
| 0.2.0   | 23 de julio 2025 | Implementación del registro de usuario con nombre y PIN utilizando DataStore.       |
| 0.3.0   | 25 de julio 2025 | Desarrollo de la pantalla principal con saludo personalizado.                       |
| 0.4.0   | 27 de julio 2025 | Inclusión de la pantalla de emociones con sugerencias dinámicas.                    |
| 0.5.0   | 28 de julio 2025 | Incorporación del journaling estructurado y almacenamiento local.                   |
| 0.6.0   | 30 de julio 2025 | Sección educativa sobre mindfulness con animación integrada desde LottieFiles.      |
| 0.7.0   | 31 de agosto 2025 | Ajustes visuales, validación de navegación y preparación para la entrega final.    |

---

## Tecnologías utilizadas

- **Lenguaje de programación:** Kotlin
- **Framework de interfaz gráfica:** Jetpack Compose
- **Almacenamiento local:** Android DataStore (SharedPreferences moderno)
- **Animaciones:** Lottie Compose (vía URL)
- **Control de versiones:** Git + GitHub
- **Entorno de desarrollo:** Android Studio

---

## Estructura del proyecto

```
com.mindu.app/
├── data/
│   └── UserPreferences.kt
├── ui/
│   ├── screens/
│   │   ├── WelcomeScreen.kt
│   │   ├── CreateUserScreen.kt
│   │   ├── EnterPinScreen.kt
│   │   ├── MainScreen.kt
│   │   ├── MoodScreen.kt
│   │   ├── JournalScreen.kt
│   │   └── MindfulnessInfoScreen.kt
│   └── theme/
│       ├── Color.kt
│       ├── Shape.kt
│       └── Theme.kt
├── MainActivity.kt
├── Navigation.kt
└── build.gradle.kts
```

---

## Próximas mejoras previstas

- Implementar almacenamiento persistente de entradas de journaling mediante SQLite o Room.
- Añadir historial de emociones y entradas escritas, organizadas por fecha.
- Integrar recordatorios automáticos para fomentar la constancia en la práctica de journaling.
- Incorporar un menú de ajustes con opciones para cambiar el PIN, editar nombre y personalizar el diseño.
- Realizar pruebas de usabilidad y optimización de rendimiento.

---

## Observaciones finales

El desarrollo de MINDU responde a la necesidad creciente de herramientas digitales centradas en el bienestar emocional. Su diseño intencional, guiado por principios de simplicidad y empatía, busca ofrecer una experiencia funcional, accesible y estéticamente cuidada. El enfoque modular del código permite su escalabilidad futura y facilita el mantenimiento del proyecto.


