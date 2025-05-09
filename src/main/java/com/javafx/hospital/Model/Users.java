package com.javafx.hospital.Model;

public class Users {
    private static String[] users = {"Administrador","Médico","Tecnico","Enfermeiro","Estagiario"};
    private static String[] registar = {"Médico","Tecnico","Estagiário"};
    private static String[] sexo = {"Masculino","Femenino"};
    private static String[] fullname = {"Rassul Ali"};

    public static String[] getUsers() {
        return users;
    }

    public static void setUsers(String[] users) {
        Users.users = users;
    }

    public static String[] getRegistar() {
        return registar;
    }

    public static void setRegistar(String[] registar) {
        Users.registar = registar;
    }

    public static String[] getSexo() {
        return sexo;
    }

    public static void setSexo(String[] sexo) {
        Users.sexo = sexo;
    }

    public static String[] getFullname() {
        return fullname;
    }

    public static void setFullname(String[] fullname) {
        Users.fullname = fullname;
    }
}
