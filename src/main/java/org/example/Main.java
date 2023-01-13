package org.example;

import dataBaseLibro.DAO.DaoConsultas;
import DTO.DtoLibros;

public class Main {
    public static void main(String[] args) {
        System.out.println("OPeración finalizada...." + DaoConsultas.actualizarElementos(new DtoLibros(19,"Don quijote de la mancha 3", "Desconocido")));
       // System.out.println("OPeración finalizada...." + DaoConsultas.eliminarElemento(new DtoLibros(16)));
    }
}