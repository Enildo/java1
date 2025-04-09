package cadastropoo;

import java.util.Scanner;
import model.*;

public class CadastroPOO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo pfRepo = new PessoaFisicaRepo();
        PessoaJuridicaRepo pjRepo = new PessoaJuridicaRepo();

        int opcao;
        do {
            System.out.println("\n================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("================================");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> {
                    System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                    String tipo = scanner.nextLine().toUpperCase();
                    System.out.print("Digite o id da pessoa: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Insira os dados...");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    if (tipo.equals("F")) {
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Idade: ");
                        int idade = Integer.parseInt(scanner.nextLine());
                        pfRepo.inserir(new PessoaFisica(id, nome, cpf, idade));
                    } else if (tipo.equals("J")) {
                        System.out.print("CNPJ: ");
                        String cnpj = scanner.nextLine();
                        pjRepo.inserir(new PessoaJuridica(id, nome, cnpj));
                    }
                }

                case 2 -> {
                    System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                    String tipo = scanner.nextLine().toUpperCase();
                    System.out.print("Digite o id da pessoa: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    if (tipo.equals("F")) {
                        PessoaFisica pf = pfRepo.obter(id);
                        if (pf != null) {
                            pf.exibir();
                            System.out.print("Novo nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("Novo CPF: ");
                            String cpf = scanner.nextLine();
                            System.out.print("Nova idade: ");
                            int idade = Integer.parseInt(scanner.nextLine());
                            pfRepo.alterar(new PessoaFisica(id, nome, cpf, idade));
                        }
                    } else if (tipo.equals("J")) {
                        PessoaJuridica pj = pjRepo.obter(id);
                        if (pj != null) {
                            pj.exibir();
                            System.out.print("Novo nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("Novo CNPJ: ");
                            String cnpj = scanner.nextLine();
                            pjRepo.alterar(new PessoaJuridica(id, nome, cnpj));
                        }
                    }
                }

                case 3 -> {
                    System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                    String tipo = scanner.nextLine().toUpperCase();
                    System.out.print("Digite o id da pessoa: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    if (tipo.equals("F")) {
                        pfRepo.excluir(id);
                    } else if (tipo.equals("J")) {
                        pjRepo.excluir(id);
                    }
                }

                case 4 -> {
                    System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                    String tipo = scanner.nextLine().toUpperCase();
                    System.out.print("Digite o id da pessoa: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    if (tipo.equals("F")) {
                        PessoaFisica pf = pfRepo.obter(id);
                        if (pf != null) pf.exibir();
                    } else if (tipo.equals("J")) {
                        PessoaJuridica pj = pjRepo.obter(id);
                        if (pj != null) pj.exibir();
                    }
                }

                case 5 -> {
                    System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                    String tipo = scanner.nextLine().toUpperCase();
                    if (tipo.equals("F")) {
                        for (PessoaFisica pf : pfRepo.obterTodos()) {
                            pf.exibir();
                            System.out.println();
                        }
                    } else if (tipo.equals("J")) {
                        for (PessoaJuridica pj : pjRepo.obterTodos()) {
                            pj.exibir();
                            System.out.println();
                        }
                    }
                }

                case 6 -> {
                    System.out.print("Digite o prefixo do arquivo: ");
                    String prefixo = scanner.nextLine();
                    try {
                        pfRepo.persistir(prefixo + ".fisica.bin");
                        pjRepo.persistir(prefixo + ".juridica.bin");
                        System.out.println("Dados salvos com sucesso.");
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar dados: " + e.getMessage());
                    }
                }

                case 7 -> {
                    System.out.print("Digite o prefixo do arquivo: ");
                    String prefixo = scanner.nextLine();
                    try {
                        pfRepo.recuperar(prefixo + ".fisica.bin");
                        pjRepo.recuperar(prefixo + ".juridica.bin");
                        System.out.println("Dados recuperados com sucesso.");
                    } catch (Exception e) {
                        System.out.println("Erro ao recuperar dados: " + e.getMessage());
                    }
                }

                case 0 -> System.out.println("Programa finalizado.");

                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
