Trabajo Practico 1 – Introducción a Android  
Kotlin   

1) Realizar una aplicación que contenga tres pantallas  
a) 
La primera pantalla deberá contener el log in de la aplicación.  
b) 
La segunda pantalla deberá contener un formulario de registración en caso de  
ser un nuevo usuario.  
c) 
La tercera pantalla le dará la bienvenida al usuario, indicando en este caso  
“Bienvenido a la aplicación Juan Torres”.  
Indicaciones para la primera pantalla (log in):  
Deberá contener un campo para ingresar el nombre del usuario y otro campo para 
ingresar la contraseña.  
Inicialmente vamos a comparar los datos ingresados con el usuario “Juan Torres” y la clave 
"1234utn".  
Validaciones:  Se deberán validar los datos ingresados por el usuario, si son correctos se 
habilitará el acceso a la pantalla de bienvenida, si no lo son, se deberá mostrar un 
mensaje advirtiendo que los datos son incorrectos.  
Además, esta pantalla debe ofrecer la posibilidad de registrar nuevos usuarios.  
Indicaciones para la segunda pantalla (registro):  
Deberá contener un campo para ingresar el nombre, e-mail, contraseña, repetir 
contraseña.  
Validaciones: los campos nombre y e-mail no deben estar vacíos, la contraseña no debe 
tener menos de 6 caracteres, las contraseñas ingresadas deben ser iguales. Luego que la 
validación sea correcta, retornar a la pantalla del log in.  
Indicaciones para la tercera pantalla (bienvenida):  
Además del mensaje de Bienvenida, deberá contener un sector donde el usuario ingrese 
la siguiente información solicitada:  
Plataforma utilizada: Android o IOS (Sistema operativo de su dispositivo) Se deberá 
mostrar una imagen del logo de la plataforma seleccionada.  
Preferencias:  
• Programación  
• Redes  
• Seguridad  
• Hardware  
• Otra  
El usuario debe poder optar por una o varias opciones. En el caso de haber elegido la 
opción Otra, debe aparecer un nuevo campo para que el usuario ingrese su preferencia. 
