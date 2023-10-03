# 🍽️ Recipe App

## 🌱 Descripción
En un mundo donde el reloj no se detiene, tomar decisiones alimenticias saludables parece un lujo. **RecipeApp** emerge como el aliado perfecto en tu travesía hacia un estilo de vida saludable y económico. Nuestra app no solo es un tesoro de recetas nutritivas, sino una guía amigable que te acompaña en la aventura de cocinar.

Explore el universo de recetas que se adaptan a todos los gustos y necesidades: vegetarianas, veganas, sin gluten y más. Con la función de carrito de compras, planifica y compra tus ingredientes sin salir de la app. Ya sea una cena elegante o una comida rápida, **Buen Sabor App** te invita a descubrir la alegría de comer bien, sin sacrificar tu bolsillo.

## 🚀 Servicios
- **🔥 Firebase Authentication**: 
   - Proveedor: Google.
   - Descripción: Un servicio de autenticación en la nube que facilita la gestión de identidades de usuario. 
   - Detalles Técnicos: Permite la autenticación mediante correo electrónico/contraseña, así como mediante proveedores de identidad federada como Google, Facebook y Twitter.

- **🚀 AuthRocket**:
   - Proveedor: AuthRocket, LLC.
   - Descripción: Un servicio que simplifica la implementación de autenticación y autorización en aplicaciones web y móviles.
   - Detalles Técnicos: Ofrece funciones como inicio de sesión, cierre de sesión, recuperación de contraseña, y gestión de sesiones de usuario.

- **🍲 TheMealDB**:
   - Proveedor: TheMealDB.com.
   - Descripción: Una API de código abierto que despliega un mundo de recetas variadas.
   - Detalles Técnicos: Permite búsquedas de recetas por nombre, ingredientes o categorías, y retorna datos en formato JSON.

- **🔍 Edamam Recipe Search**:
   - Proveedor: Edamam LLC.
   - Descripción: Una API que destaca por su extensa base de datos de recetas de alta calidad.
   - Detalles Técnicos: Ofrece búsqueda de recetas por palabras clave, ingredientes, y más, con resultados enriquecidos con información nutricional.

- **🍎 API de Nutrición**:
   - Proveedor: A definir.
   - Descripción: Una fuente de información nutricional para cada ingrediente.
   - Detalles Técnicos: En búsqueda de una API apropiada, o se creará una base de datos local.

- **💰 API de Precios de Ingredientes**:
   - Proveedor: A definir.
   - Descripción: Un servicio para obtener los precios actualizados de los ingredientes.
   - Detalles Técnicos: En búsqueda de una API apropiada, o se creará una base de datos local.

## 📚 Librerías
- **🌐 Retrofit**:
   - Proveedor: Square, Inc.
   - Descripción: Una librería tipo-safe HTTP client para Android y Java, ideal para consumir APIs REST de manera eficiente.
   - Detalles Técnicos: Utilizado para realizar llamadas API a Spoonacular y al API de Precios de Alimentos. Convierte las respuestas JSON de las API en objetos Kotlin, facilitando la manipulación de los datos en nuestra aplicación.

- **🔄 GSON**:
   - Proveedor: Google.
   - Descripción: Una librería que permite la conversión entre objetos Java y su representación JSON.
   - Detalles Técnicos: Utilizado en combinación con Retrofit para convertir las respuestas JSON de las API en objetos Kotlin.

- **🧩 Anko**:
   - Proveedor: Kotlin.
   - Descripción: Un conjunto de extensiones y funciones que facilitan el desarrollo en Android.
   - Detalles Técnicos: Incluye módulos como Anko Commons, Anko Layouts, Anko SQLite y Anko Coroutines para distintas tareas de desarrollo.

- **🛠 KAndroid**:
   - Proveedor: Piotr Wittchen.
   - Descripción: Librería que explora las extensiones de Kotlin para simplificar tareas comunes en Android.
   - Detalles Técnicos: Provee utilidades para reducir el código boilerplate y evitar duplicidades de código.

- **🗃 PreferenceHolder**:
   - Proveedor: Inmite s.r.o.
   - Descripción: Facilita el manejo de Shared Preferences en Android.
   - Detalles Técnicos: Permite encapsular en un objeto todas las parejas clave-valor a almacenar, simplificando el acceso y gestión de preferencias.


