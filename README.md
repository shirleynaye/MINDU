# Informe del Proyecto – Aplicación Móvil **MINDU**

---

## 1. Descripción del proyecto

**MINDU** es una aplicación móvil desarrollada en **Android Studio con Kotlin y Jetpack Compose**, cuyo propósito es fomentar el **bienestar psicológico y emocional** de los usuarios.  
La app combina técnicas de **journaling guiado**, ejercicios de **mindfulness** y un **blog educativo** con información confiable.

Está orientada principalmente a **estudiantes universitarios, jóvenes profesionales y empresas**, respondiendo a la necesidad actual de contar con herramientas digitales accesibles que promuevan la salud mental.

---

## 2. Exposición del problema

El **estrés, la ansiedad y la falta de gestión emocional** son problemas cada vez más comunes, sobre todo en entornos académicos y laborales.  
Aunque existen aplicaciones internacionales de bienestar, muchas presentan barreras como el **idioma**, el costo de suscripciones o la **falta de personalización**.

MINDU busca **resolver esta brecha en el mercado hispanohablante**, ofreciendo una plataforma intuitiva, accesible y segura, con énfasis en:

- La **privacidad del usuario** mediante PIN de acceso.  
- Un enfoque culturalmente cercano al público objetivo.  
- Funciones integradas en una sola aplicación (diario, emociones, respiración y educación).  

---

## 3. Plataforma

- **Lenguaje de programación:** Kotlin  
- **Framework:** Jetpack Compose  
- **Arquitectura:** MVVM + Navigation Component  
- **Persistencia:** DataStore Preferences (PIN, usuario, avatar, diseño)  
- **Compatibilidad:** Android 5.0 (Lollipop) en adelante  
- **Plan futuro:** Expansión a iOS, Web y sincronización en la nube  

---

## 4. Interfaz de usuario e interfaz de administrador

### Interfaz de usuario (UI):

- Pantalla de bienvenida con opciones de **registro e inicio de sesión**.  
- Selección de **avatar** y personalización de cuenta.  
- **Journaling guiado** con historial completo y última reflexión siempre visible.  
- Registro de **emociones del día** con frases motivacionales y recomendaciones.  
- **Ejercicios de respiración** con guía visual.  
- **Blog educativo** sobre mindfulness y bienestar psicológico.  
- Opción de **modo oscuro** para personalización de diseño.  

### Interfaz de administrador (a futuro):

- Panel de métricas para analizar el uso de la aplicación.  
- Gestión de contenidos del blog educativo.  
- Posible integración con plataformas académicas o empresariales.  

---

## 5. Funcionalidad

- **Autenticación segura** con PIN y usuario.  
- **Gestión de reflexiones:** registro, visualización de la última nota y acceso al historial completo.  
- **Emociones del día:** selección rápida con retroalimentación personalizada.  
- **Ejercicios de mindfulness:** respiración guiada paso a paso.  
- **Educación:** blog con artículos relacionados con bienestar psicológico.  
- **Personalización:** cambio de diseño (claro/oscuro), avatar y PIN.  

---

## 6. Diseño (wireframes o esquemas de página)

Los esquemas iniciales fueron diseñados en base a una estructura minimalista, amigable y emocional:

1. **Pantalla de bienvenida:** Logo + opciones de registro/inicio de sesión.  
2. **Registro:** PIN, nombre de usuario y selección de avatar.  
3. **Pantalla principal:** resumen de reflexiones, última nota visible y accesos rápidos.  
4. **Sección de emociones:** íconos con expresiones y frases personalizadas.  
5. **Ejercicios de respiración:** animación visual con instrucciones.  
6. **Blog educativo:** lista de artículos sobre mindfulness.  
7. **Perfil/Mi cuenta:** opciones de personalización (avatar, PIN, diseño).

## Wireframes y Pantallas

📄 [Ver pantallas de MINDU (PDF)](app/src/main/res/drawable/pantallas_mindu.pdf)


