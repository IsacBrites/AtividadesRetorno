package ControleDeVoo;

import java.time.LocalDateTime;
import java.util.ArrayList;

        public class TesteCiaAerea {

            public static void main(String[] args) {

                CiaAerea cia = new CiaAerea("Brasil Airlines");

                ArrayList<Aeronave> aeronaves = new ArrayList<>();

                aeronaves.add(new Aeronave("Boeing 737", 20, 5, 5000));
                aeronaves.add(new Aeronave("Airbus A320", 22, 5, 5500));
                aeronaves.add(new Aeronave("Embraer E195", 18, 4, 3500));
                aeronaves.add(new Aeronave("Boeing 767", 24, 6, 7000));
                aeronaves.add(new Aeronave("Airbus A330", 26, 6, 8000));
                aeronaves.add(new Aeronave("Boeing 787", 28, 7, 8500));
                aeronaves.add(new Aeronave("Airbus A350", 30, 8, 9000));
                aeronaves.add(new Aeronave("Embraer E190", 19, 4, 3000));
                aeronaves.add(new Aeronave("Airbus A321", 23, 5, 6000));
                aeronaves.add(new Aeronave("Boeing 777", 32, 8, 10000));

                String[][] rotas = {
                        {"Belo Horizonte", "São Paulo"},
                        {"Belo Horizonte", "São Paulo"},
                        {"Belo Horizonte", "São Paulo"},
                        {"Belo Horizonte", "São Paulo"},
                        {"Belo Horizonte", "São Paulo"},
                        {"Belo Horizonte", "São Paulo"},
                        {"Belo Horizonte", "São Paulo"},
                        {"Belo Horizonte", "São Paulo"},
                        {"Belo Horizonte", "Rio de Janeiro"},
                        {"Belo Horizonte", "Rio de Janeiro"},
                        {"Belo Horizonte", "Rio de Janeiro"},
                        {"Belo Horizonte", "Rio de Janeiro"},
                        {"Belo Horizonte", "Rio de Janeiro"},
                        {"Belo Horizonte", "Rio de Janeiro"},
                        {"São Paulo", "Rio de Janeiro"},
                        {"São Paulo", "Rio de Janeiro"},
                        {"São Paulo", "Rio de Janeiro"},
                        {"São Paulo", "Rio de Janeiro"},
                        {"São Paulo", "Rio de Janeiro"},
                        {"Rio de Janeiro", "Brasília"},
                        {"Rio de Janeiro", "Brasília"},
                        {"Rio de Janeiro", "Brasília"},
                        {"Rio de Janeiro", "Brasília"},
                        {"Belo Horizonte", "Brasília"},
                        {"Belo Horizonte", "Brasília"},
                        {"Belo Horizonte", "Brasília"},
                        {"Belo Horizonte", "Brasília"},
                        {"São Paulo", "Brasília"},
                        {"São Paulo", "Brasília"},
                        {"São Paulo", "Brasília"},
                        {"Brasília", "Belo Horizonte"},
                        {"Brasília", "Belo Horizonte"},
                        {"Brasília", "Belo Horizonte"},
                        {"Rio de Janeiro", "São Paulo"},
                        {"Rio de Janeiro", "São Paulo"},
                        {"Rio de Janeiro", "São Paulo"},
                        {"Belo Horizonte", "Salvador"},
                        {"Belo Horizonte", "Salvador"},
                        {"São Paulo", "Salvador"},
                        {"São Paulo", "Salvador"}
                };

                ArrayList<Voo> voos = new ArrayList<>();

                for (int i = 0; i < 40; i++) {

                    Aeronave aeronave = aeronaves.get(i % aeronaves.size());

                    Voo voo = new Voo(
                            100 + i,
                            rotas[i][0],
                            rotas[i][1],
                            aeronave,
                            LocalDateTime.of(
                                    2026,
                                    9,
                                    1 + (i % 20),
                                    8 + (i % 10),
                                    0
                            ),
                            500 + (i * 10),
                            900 + (i * 15),
                            300 + (i * 20)
                    );

                    voos.add(voo);
                    cia.cadastrarVoo(voo);
                }

                ArrayList<Passageiro> passageiros = new ArrayList<>();

                for (int i = 0; i < 200; i++) {

                    Passageiro passageiro = new Passageiro(
                            "Passageiro " + (i + 1),
                            String.format("CPF%03d", i + 1),
                            "PASS" + String.format("%03d", i + 1),
                            i * 500
                    );

                    passageiros.add(passageiro);
                    cia.cadastrarPassageiro(passageiro);
                }

                int reservasRealizadas = 0;

                for (int i = 0; i < 350; i++) {

                    Passageiro passageiro = passageiros.get(i % passageiros.size());

                    int numeroVoo;

                    if (i < 150) {
                        numeroVoo = 100 + (i % 5);
                    } else if (i < 300) {
                        numeroVoo = 105 + ((i - 150) % 10);
                    } else {
                        numeroVoo = 115 + ((i - 300) % 25);
                    }

                    ClasseVoo classe;

                    if (i % 4 == 0) {
                        classe = ClasseVoo.EXECUTIVA;
                    } else {
                        classe = ClasseVoo.ECONOMICA;
                    }

                    if (cia.fazerReserva(passageiro, numeroVoo, classe)) {
                        reservasRealizadas++;
                    }
                }

                int checkins = 0;

                for (int i = 0; i < 300; i++) {

                    Passageiro passageiro = passageiros.get(i % passageiros.size());

                    for (Voo voo : voos) {

                        if (voo.fazerCheckin(passageiro)) {
                            checkins++;
                            break;
                        }
                    }
                }

                int bagagensDespachadas = 0;

                for (int i = 0; i < 250; i++) {

                    Passageiro passageiro = passageiros.get(i % passageiros.size());

                    double peso;

                    if (i % 5 == 0) {
                        peso = 30;
                    } else if (i % 7 == 0) {
                        peso = 12;
                    } else {
                        peso = 20;
                    }

                    Tipo tipo;

                    if (i % 3 == 0) {
                        tipo = Tipo.MAO;
                    } else {
                        tipo = Tipo.DESPACHADA;
                    }

                    Bagagem bagagem = new Bagagem(i + 1, peso, tipo, passageiro);

                    Voo voo = voos.get(i % voos.size());

                    voo.despacharBagagem(bagagem);

                    bagagensDespachadas++;
                }

                System.out.println("VOOS COM OVERBOOKING");

                ArrayList<Voo> voosOverbooking = cia.voosComOverbooking();

                for (Voo voo : voosOverbooking) {
                    System.out.println(
                            "Voo " + voo.getNumero()
                                    + " - "
                                    + voo.getOrigem()
                                    + " -> "
                                    + voo.getDestino()
                    );
                }

                System.out.println("\n3 VOOS MAIS LOTADOS");

                ArrayList<Voo> maisLotados = cia.voosMaisLotado(3);

                for (Voo voo : maisLotados) {
                    System.out.println(
                            "Voo " + voo.getNumero()
                                    + " - "
                                    + voo.getOrigem()
                                    + " -> "
                                    + voo.getDestino()
                                    + " - Ocupação: "
                                    + voo.taxaOcupacao()
                                    + "%"
                    );
                }

                System.out.println("\nPASSAGEIRO COM MAIS MILHAS");

                Passageiro maisMilhas = cia.passageiroComMaisMilhas();

                if (maisMilhas != null) {
                    maisMilhas.exibir();
                }

                System.out.println("\nRECEITA TOTAL");

                System.out.println(
                        "Receita total: R$ "
                                + cia.receitaTotalCia()
                );

                System.out.println("\nARRECADAÇÃO COM BAGAGENS");

                System.out.println(
                        "Taxas de bagagem: R$ "
                                + cia.arrecadacaoTaxasBagagem()
                );

                System.out.println("\nROTA MAIS POPULAR");

                System.out.println(
                        "Rota: "
                                + cia.rotaMaisPopular()
                );

                System.out.println("\nBAGAGENS COM EXCESSO");

                ArrayList<Bagagem> bagagensExcesso =
                        cia.bagensComExcesso();

                System.out.println(
                        "Quantidade de bagagens com excesso: "
                                + bagagensExcesso.size()
                );

                System.out.println("\nRELATÓRIO OPERACIONAL");

                cia.exibir();

                System.out.println("\nRESUMO DO TESTE");

                System.out.println(
                        "Aeronaves cadastradas: "
                                + aeronaves.size()
                );

                System.out.println(
                        "Voos cadastrados: "
                                + voos.size()
                );

                System.out.println(
                        "Passageiros cadastrados: "
                                + passageiros.size()
                );

                System.out.println(
                        "Reservas realizadas: "
                                + reservasRealizadas
                );

                System.out.println(
                        "Check-ins realizados: "
                                + checkins
                );

                System.out.println(
                        "Bagagens despachadas: "
                                + bagagensDespachadas
                );

                System.out.println("\nFIM DO TESTE");
            }
        }
