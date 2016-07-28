package com.muffinalunos.muffinmad.muffinalunos.modelo;

import java.io.Serializable;

/**
 * Created by muffinmad on 20/07/2016.
 */
public class Aluno implements Serializable{

    private long _ID;
    private String _nome;
    private String _endereco;
    private String _telefone;
    private String _site;
    private float _nota;
    private String _caminhoFoto;

    public long get_ID() {
        return _ID;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public String get_nome() {
        return _nome;
    }

    public void set_nome(String _nome) {
        this._nome = _nome;
    }

    public String get_endereco() {
        return _endereco;
    }

    public void set_endereco(String _endereco) {
        this._endereco = _endereco;
    }

    public String get_telefone() {
        return _telefone;
    }

    public void set_telefone(String _telefone) {
        this._telefone = _telefone;
    }

    public String get_site() {
        return _site;
    }

    public void set_site(String _site) {
        this._site = _site;
    }

    public float get_nota() {
        return _nota;
    }

    public void set_nota(float _nota) {
        this._nota = _nota;
    }

    public String get_caminhoFoto() {
        return _caminhoFoto;
    }

    public void set_caminhoFoto(String _caminhoFoto) {
        this._caminhoFoto = _caminhoFoto;
    }

    public Aluno(){

    }

    @Override
    public String toString() {
        return get_ID()+" - "+get_nome();
    }
}
