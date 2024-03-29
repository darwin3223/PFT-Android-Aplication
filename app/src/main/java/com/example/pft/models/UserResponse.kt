package com.example.pft.models

data class UserResponse(
    val token: String,
    val usuario: Usuario
)

data class Usuario(
    val idUsuario: Long,
    val nombre: String,
    val apellido: String,
    val documento: Long,
    val fechaNacimiento: Long,
    val nombreUsuario: String,
    val contrasenia: String,
    val tipoUsuario: String,
    val verificacion: String,
    val mail: String,
    val mailInstitucional: String,
    val telefono: String,
    val genero: String,
    val estadoUsuario: String,
    val itr: Itr,
    val localidad: Localidad
)

data class Itr(
    val idItr: Int,
    val nombre: String,
    val localidad: Localidad,
    val estado: String
)

data class Localidad(
    val idLocalidad: Int,
    val nombre: String,
    val departamento: Departamento
)

data class Departamento(
    val idDepartamento: Int,
    val nombre: String
)

data class Estudiante(
    val idUsuario: Long?,
    val nombre: String,
    val apellido: String,
    val documento: Long,
    val fechaNacimiento: Long,
    val nombreUsuario: String,
    val contrasenia: String,
    val tipoUsuario: String,
    val verificacion: String,
    val mail: String,
    val mailInstitucional: String,
    val telefono: String,
    val genero: String,
    val estadoUsuario: String,
    val itr: Itr,
    val localidad: Localidad,
    val estadoEstudiante: String,
    val añoIngreso: Int
)

