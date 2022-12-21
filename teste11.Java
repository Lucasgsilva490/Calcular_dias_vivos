# Calcular_dias_vivos
programa feito em java para calcular quantos dias você viveu até a data atual



//importando as classes necessárias
import java.time.Year;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class teste11 {
    public static void main(String[] args) {
        //extraindo a data atual e guardando na String "dataAtual"
        DateTimeFormatter teste = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataAtual = teste.format((LocalDateTime.now()));
        //criando o Scanner para extrair as informações que o usuário vai passar
        Scanner sc = new Scanner(System.in);

        // criando as listas dos meses 30 e 31 para saber quais meses tem 30 dias e quais tem 31
        ArrayList<String> meses30 = new ArrayList<String>();
        meses30.add("04");
        meses30.add("06");
        meses30.add("09");
        meses30.add("11");

        ArrayList<String> meses31 = new ArrayList<String>();
        meses31.add("03");
        meses31.add("05");
        meses31.add("07");
        meses31.add("08");
        meses31.add("10");
        meses31.add("12");


        // solicitando e extraindo a data de nascimento com o Scanner e guardando na String "dataNasc"
        System.out.println("Digite sua data de nascimento no formato (dd/MM/yyyy):");
        String dataNasc = sc.next();

        //extraindo separadamente o dia, mês e ano da data de nascimento e guardando nas suas Strings correspondentes
        String diaNasc = dataNasc.substring(0, 2);
        String mesNasc = dataNasc.substring(3, 5);
        String AnoNasc = dataNasc.substring(6);

        //extraindo separadamente o dia, mês e ano da data atual e guardando nas suas Strings correspondentes
        String diaAtual = dataAtual.substring(0, 2);
        String mesAtual = dataAtual.substring(3, 5);
        String AnoAtual = dataAtual.substring(6);





        //criando todas as variáveis que serão usadas para guardar as informações e também auxiliar

        int diasPerdidos = 0; /* irá guardar a quantidade de dias perdidos do ano de nascimento,
         por exemplo, se a pessoa nasceu dia 1 de fevereiro, ela perdeu 31 dias daquele ano,
         correspondente ao número de dias de janeiro em que ela ainda não havia nascido*/

        int diasVividosAnoNasc = 0; /* irá guardar a quantidade de dias que a pessoa viveu no ano do nascimento,
        é necessário subtrair a quantidade de diasPerdidos(calculado anteriormente), da quantidade de dias que aquele
        ano possui no total, 365 ou 366(caso for bissexto)*/

        int diasVividosAnoAtual = 0;/*irá guardar a quantidade de dias que a pessoa já viveu do ano atual, por exemplo,
        se hoje é dia 15 de fevereiro, a pessoa já viveu 45 dias desse ano, o dia atual não conta, pois ainda não
        acabou*/

        int diasVividosTotal = 0;/*é o resultado final, que mostra quantos dias a pessoa viveu desde que ela nasceu
        até hoje*/

        int cont30 = 0;/*irá ser somado +1 dentro da estrutura de repetição quando o programa detectar que o
        usuário perdeu algum mês que tenha 30 dias no ano de nascimento*/

        int cont31 = 0;/*irá ser somado +1 dentro da estrutura de repetição quando o programa detectar que o
        usuário perdeu algum mês que tenha 31 dias no ano de nascimento*/

        int cont231 = 0;/*irá ser somado +1 dentro da estrutura de repetição quando o programa detectar que o
        usuário já completo um mês que tenha 31 dias no ano atual*/

        int cont230 = 0;/*irá ser somado +1 dentro da estrutura de repetição quando o programa detectar que o
        usuário já completo um mês que tenha 30 dias no ano atual*/

        /*essas duas auxiliares foram utilizadas para transformar um int "k" em string, dentro da estrutura
        de repetição, para que depois seja possível comparar com os valores String das Arrays*/
        String auxiliar3 = "0";
        String auxiliar4 = "";



        /*iniciando a estrutura para calcular a quantidade de diasPerdidos do ano de nascimento */
        if (mesNasc.equals("01")) {
            diasPerdidos = Integer.parseInt(diaNasc) - 1; /*caso o nascimento seja em janeiro,
                os diasPerdidos serão o dia de nascimento menos 1, pois a partir do dia que nasceu,
                já é comsiderado um dia vivido*/

        } else if (mesNasc.equals("02")) {
            diasPerdidos = 31 + (Integer.parseInt(diaNasc) - 1); /*caso o nascimento seja em
                fevereiro, os diasPerdios serão o mês inteiro de janeiro que já passou(31 dias),
                mais o dia de nascimento menos 1*/

        } else if (mesNasc.equals("03") && Year.isLeap(Integer.parseInt(AnoNasc)) == true) {
            diasPerdidos = 31 + 29 + (Integer.parseInt(diaNasc) - 1);/*caso o nascimento seja
                em março e o ano seja bissexto, irá somar os meses de janeiro(31 dias),
                fevereiro(29 dias porque é bissexto), e o dia de nascimento menos 1*/

        } else {
            if (mesNasc.equals("03") && Year.isLeap(Integer.parseInt(AnoNasc)) == false) {
                diasPerdidos = 31 + 28 + (Integer.parseInt(diaNasc) - 1);/*caso o nascimento seja
                em março e o ano NÃO seja bissexto, irá somar os meses de janeiro(31 dias),
                fevereiro(28 dias porque NÃO é bissexto), e o dia de nascimento menos 1*/
            } else {
                for (int k = 4; k <= (Integer.parseInt(mesNasc)); k++) { /*caso o nascimento seja a partir
            de Março, o "for" irá passar por todos os meses a partir de Março até que chegue um mês antes do mês
            nascimento, a cada laço, ele verifica se o mês pertence a Array meses31 ou meses30, caso pertença ao
            meses31, irá somar somar um na int "cont31", se não, se pertencer ao meses30, irá somar 1  na int
            "cont30", o mês de nascimento não é verificado, pois o programa só verifica os meses completos que
            já foram passados, e o mês de nascimento pode não ter sido completo ainda, por isso ele é considerado
            só depois do "for", usando o dia de nascimento*/
                    if (meses31.contains(auxiliar3 + (k - 1)) || meses31.contains(auxiliar4 + (k - 1))) { /*
                verificando em qual Array aquele mês está, usando as auxiliares para transformar a int "k", em
                uma String para comparar com os meses das Arrays*/
                        cont31++;
                    } else {
                        cont30++;
                    /*para sabermos os dias com os contadores, é só multiplicar o cont31 por 31, e o
                    cont30 por 30, depois somar os dois, assim temos os diasPerdidos dos meses a partir de março*/
                    }
                }
                /*assim que o laço de repetição acabar, teremos quais as quantidades de meses perdidos que
            possuem 30 e 31 dias, ou seja, para termos os dias, é só multiplicar a quantidade de meses pelo
            número de dias que cada mês tem, por exemplo, se passamos por 2 meses que tem 31 dias,
            será 31 x 2 = 62 dias*/

                if (Year.isLeap(Integer.parseInt(AnoNasc)) == true) {/*se o ano for bissexto, ele irá somar o mês
            de janeiro(31 dias), mais o mês de fevereiro(29 dias porque é bissexto), mais os dias dos meses
            completos que tem 30 e 31 dias, mais o dia de nascimento menos 1*/
                    diasPerdidos = (31 + 29 + ((cont31 * 31) + (cont30 * 30))) + (Integer.parseInt(diaNasc) - 1);
                } else {
                /*se o ano não for bissexto, irá ser feito a mesma coisa, porém o mês de fevereiro terá apenas
                28 dias pois o ano não é bissexto*/
                    diasPerdidos = (31 + 28 + ((cont31 * 31) + (cont30 * 30))) + (Integer.parseInt(diaNasc) - 1);
                }
            }
        }



        /*iniciando a estrutura para calcular a quantidade de diasVividosAnoAtual*/
        if (mesAtual.equals("01")) {
            diasVividosAnoAtual = Integer.parseInt(diaAtual) - 1; /*caso o mêsAtual seja janeiro,
                os diasVividosAnoAtual será o dia de nascimento menos 1, pois o diaAtual ainda
                não é considerado um dia vivido, apenas quando ele acaba*/

        } else if (mesAtual.equals("02")) {
            diasVividosAnoAtual = 31 + (Integer.parseInt(diaAtual) - 1); /*caso o mêsAtual seja
                fevereiro, os diasVividosAnoAtual será o mês inteiro de janeiro que já passou(31 dias),
                mais o diaAtual menos 1*/

        } else {
            if (mesAtual.equals("03") && Year.isLeap(Integer.parseInt(AnoAtual)) == true) {
                diasVividosAnoAtual = 31 + 29 + (Integer.parseInt(diaAtual) - 1);/*caso o mêsAtual seja
                março e o ano seja bissexto, irá somar os meses de janeiro(31 dias),
                fevereiro(29 dias porque é bissexto), e o diaAtual menos 1*/

            } else {
                if (mesAtual.equals("03") && Year.isLeap(Integer.parseInt(AnoAtual)) == false) {
                    diasVividosAnoAtual = 31 + 28 + (Integer.parseInt(diaAtual) - 1);/*caso o mêsAtual seja
                março e o ano NÃO seja bissexto, irá somar os meses de janeiro(31 dias),
                fevereiro(28 dias porque NÃO é bissexto), e o diaAtual menos 1*/
                } else {
                    for (int k = 4; k <= (Integer.parseInt(mesAtual)); k++) { /*caso o mêsAtual seja a partir
            de Março, o "for" irá passar por todos os meses a partir de Março até que chegue um mês antes do mêsAtual,
            a cada laço, ele verifica se o mês pertence a Array meses31 ou meses30, caso pertença ao
            meses31, irá somar somar um na int "cont31", se não, se pertencer ao meses30, irá somar 1  na int
            "cont30", o mêsAtual não é verificado, pois o programa só verifica os meses completos que
            já foram passados, e o mêsAtual pode não ter sido completo ainda, por isso ele é considerado
            só depois do "for", usando o diaAtual*/

                        if (meses31.contains(auxiliar3 + (k - 1)) || meses31.contains(auxiliar4 + (k - 1))) { /*
                verificando em qual Array aquele mês está, usando as auxiliares para transformar a int "k", em
                uma String para comparar com os meses das Arrays*/
                            cont231++;
                        } else {
                            cont230++;
                    /*para sabermos os dias com os contadores, é só multiplicar o cont31 por 31, e o
                    cont30 por 30, depois somar os dois, assim temos os dias vividos dos meses a partir de março*/
                        }
                    }
                    /*assim que o laço de repetição acabar, teremos quais as quantidades de meses vividos que
            possuem 30 e 31 dias, ou seja, para termos os dias, é só multiplicar a quantidade de meses pelo
            número de dias que cada mês tem, por exemplo, se passamos por 2 meses que tem 31 dias,
            será 31 x 2 = 62 dias*/

                    if (Year.isLeap(Integer.parseInt(AnoAtual)) == true) {/*se o ano for bissexto, ele irá somar o mês
            de janeiro(31 dias), mais o mês de fevereiro(29 dias porque é bissexto), mais os dias dos meses
            completos que tem 30 e 31 dias, mais o diaAtual menos 1*/
                        diasVividosAnoAtual = (31 + 29 + ((cont231 * 31) + (cont230 * 30))) +
                                (Integer.parseInt(diaAtual) - 1);
                    } else {
                /*se o ano não for bissexto, irá ser feito a mesma coisa, porém o mês de fevereiro terá apenas
                28 dias pois o ano não é bissexto*/
                        diasVividosAnoAtual = (31 + 28 + ((cont231 * 31) + (cont230 * 30))) +
                                (Integer.parseInt(diaAtual) - 1);
                    }
                }
            }
        }


        int diasAnosInteirosVividos = 0;/*essa variável irá armazenar a quantidade de dias vividos dos anos
        completos que a pessoa passou, por exemplo, se o nascimento foi em 2021 e estamos em 2023,
        a pessoa tem que obrigatóriamente ter completado o ano de 2022, ou seja, essa variável vai
        armazenar os dias desses anos completos, os únicos anos que não foram completos foram o de
        nascimento e o atual, apenas os anos que estão entre eles foram completos*/
        int aux1 = Integer.parseInt(AnoNasc);/*transformando o ano de nascimento em int para ser mais fácil
        de visualizar no for*/
        int aux2 = Integer.parseInt(AnoAtual);/*transformando o ano atual em int para ser mais fácil
        de visualizar no for*/

        for (int h = aux1 + 1; h < aux2; h++) {/*o laço irá começar no ano seguinte do ano de nascimento
         e irá parar um ano antes do ano atual, e toda vez que passar por cada ano, vai verificar se o ano
         é bissexto, se o ano for bissexto, ele soma 366 na variável, se não for bissexto, soma 365, no
         final, teremos os dias vividos dos anos inteiros*/
            if (Year.isLeap(h) == true) {
                diasAnosInteirosVividos += 366;
            } else {
                diasAnosInteirosVividos += 365;
            }
        }



        /*criando estrutura para calcular os dias vividos do ano de nascimento usando os
        diasPerdidos calculados anteriormente, para calucular, apenas verificamos se o ano
         de nascimento é bissexto o não, se for, subtraímos os dias perdidos de
         366(dias completos de um ano bissexto), se não for, subtraímos os dias perdidos
         de 365(dias completos de um ano não bissexto), assim, teremos os dias vividos do
         ano de nascimento*/
        if (Year.isLeap(Integer.parseInt(AnoNasc)) == true) {
            diasVividosAnoNasc = 366 - diasPerdidos;
        } else {
            if (Year.isLeap(Integer.parseInt(AnoNasc)) == false) {
                diasVividosAnoNasc = 365 - diasPerdidos;
            }
        }


        /*por fim, para carcular o total de dias vividos, basta somar as 3 variáveis que conseguimos,
         mas, se o ano de nascimento for igual o ano atual basta subtrairmos os diasPerdidos dos
         diasVividosAnoAtual, assim teremos os dias totais vividos desse ano*/
        if (AnoNasc.equals(AnoAtual)) {
            diasVividosTotal = diasVividosAnoAtual - diasPerdidos;
        } else {
            diasVividosTotal = diasVividosAnoNasc + diasAnosInteirosVividos + diasVividosAnoAtual;
        }

        /*mas antes de mostrarmos o resultado presisamos verificar se a data digitada é válida */

        boolean confirm1 = false; /*será usada como confirmação final para começar o cálculo dos dias*/
        boolean confirm2 = false; /*será usada como confirmação apenas do mês de fevereiro, pois os dias
        de fevereiro podem mudar por conta do ano bissexto*/


        if (mesNasc.equals("02")) {
            if (Year.isLeap(Integer.parseInt(AnoNasc))) {/*se o mês for fevereiro e o ano for bissexto,
            o dia de nascimento só pode ser menor ou igual a 29*/
                if (Integer.parseInt(diaNasc) <= 29) {
                    confirm2 = true;
                } else {
                    System.out.println("Data inválida"); /*se for maior que 29 retorna isso*/
                }
            } else {
                if (Integer.parseInt(diaNasc) >= 29) { /*se o ano não for bissexto e o dia for maior
                ou igual a 29, retornará isso*/
                    System.out.println("Data inválida");
                } else {
                    /*se cair nesse else, quer dizer que o ano não é bissexto e o dia é menor ou igual a 28,
                    data válida*/
                    confirm2 = true;
                }
            }
        } else {
            /*se cair nesse else, quer dizer que o mês não é fevereiro*/
            confirm2 = true;
        }

        if (confirm2 == true) {
            if (Integer.parseInt(AnoNasc) > Integer.parseInt(AnoAtual)) {
                /*o ano de nascimento não pode ser maior que o ano atual*/
                System.out.println("Data inválida");
            } else {
                if (diaNasc.equals("31")) {/*se o dia for 31, vai verificar se o mês possui 31 dias,
                 verificando se o mês é janeiro ou se está na Array meses31*/
                    if (meses31.contains(mesNasc) || mesNasc.equals("01")) {
                        if (diasVividosTotal <= 0) {/*se esse if for verdadeiro, quer dizer que os
                        dias totais vividos é 0(a data de nascimento é a mesma do dia atual), ou é
                        negativa(a data da nascimento é depois da data atual)*/
                            System.out.println("Data inválida");
                        } else {
                            /*se cair nesse else, quer dizer que o dia é 31, em um mês que possui 31 dias,
                            e que a data de nascimento é menor que a data atual, data válida*/
                            confirm1 = true;
                        }
                    } else {
                        /*se cair nesse else, quer dizer que o dia é 31, mas o mês não possui 31 dias*/
                        System.out.println("Data inválida");
                    }
                } else {
                    if (Integer.parseInt(diaNasc) > 31) {
                        /*não existe nenhum mês que tenha mais que 31 dias*/
                        System.out.println("Data inválida");
                    } else {
                        if (diasVividosTotal <= 0) {
                            /*testando novamente se a data de nascimento é maior que a data atual*/
                            System.out.println("Data inválida");
                        } else {
                            /*se cair nesse else, quer dizer que o dia é menor ou igual a 30, e a
                            data de nascimento é menor que a data atual*/
                            confirm1 = true;
                        }
                    }
                }
            }
        }

        /*verificando se é aniversário da pessoa para deixar uma mensagem carinhosa, e se a pessoa tiver
        nascendo hoje, não é considerado o aniverário dela aindam apenas quando ela fizer 1 ano de idade*/
        if (diaNasc.equals(diaAtual) && mesNasc.equals(mesAtual) &&
                Integer.parseInt(AnoNasc) < Integer.parseInt(AnoAtual)) {
            System.out.println("Hoje é seu aniversário, Parabéns !!!");
            System.out.println("");
        }

        /*mostrando o resultado final, caso a data seja válida, o confirm1 será true e irá mostrar o resultado,
         se não, a data é inválida para o programa*/
        if (confirm1 == true) {
            System.out.println("Até agora, você viveu por " + diasVividosTotal + " dias.");
            System.out.println("Até agora, você viveu por " + diasVividosTotal / 7 + " semanas.");
            double sla = diasVividosTotal / 30.4;
            int slaInteiro = (int) sla;
            System.out.println("Até agora, você viveu por " + slaInteiro + " meses.");
            double sla2 = diasVividosTotal / 365;
            int slaInteiro2 = (int) sla2;
            System.out.println("Até agora, você viveu por " + slaInteiro2 + " anos.");
            System.out.println("");
            System.out.println("Obrigado por usar meu programa :)");
        }
    }
}
