package com.muffinalunos.muffinmad.muffinalunos.modelo;

/**
 * Created by muffinmad on 20/07/2016.
 */
public class Aluno {
    private static long _ID;
    private static String _nome;
    private static String _endereco;
    private static String _telefone;
    private static String _site;
    private static float _nota;
    public Aluno(){

    }

    public static long getId() {
        return _ID;
    }

    public static void setId(long Id) {
        _ID = Id;
    }

    public static String get_nome() {
        return _nome;
    }

    public static void set_nome(String _nome) {
        Aluno._nome = _nome;
    }

    public static String get_endereco() {
        return _endereco;
    }

    public static void set_endereco(String _endereco) {
        Aluno._endereco = _endereco;
    }

    public static String get_telefone() {
        return _telefone;
    }

    public static void set_telefone(String _telefone) {
        Aluno._telefone = _telefone;
    }

    public static String get_site() {
        return _site;
    }

    public static void set_site(String _site) {
        Aluno._site = _site;
    }

    public static float get_nota() {
        return _nota;
    }

    public static void set_nota(float _nota) {
        Aluno._nota = _nota;
    }

    @Override
    public String toString() {
        return getId()+" - "+get_nome();
    }
}
