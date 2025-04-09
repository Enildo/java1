package model;

import java.io.*;
import java.util.*;

public class PessoaJuridicaRepo {
    private List<PessoaJuridica> pessoas = new ArrayList<>();

    public void inserir(PessoaJuridica p) { pessoas.add(p); }
    public void alterar(PessoaJuridica p) {
        excluir(p.getId());
        inserir(p);
    }
    public void excluir(int id) { pessoas.removeIf(p -> p.getId() == id); }
    public PessoaJuridica obter(int id) {
        return pessoas.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
    public List<PessoaJuridica> obterTodos() { return pessoas; }

    public void persistir(String nomeArquivo) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(pessoas);
        }
    }

    public void recuperar(String nomeArquivo) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            pessoas = (List<PessoaJuridica>) ois.readObject();
        }
    }
}
