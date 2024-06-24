import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {

    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
            new Funcionario("Maria", LocalDate.parse("2000-10-18"), new BigDecimal("2009.44"), "Operador"),
            new Funcionario("João", LocalDate.parse("1990-05-12"), new BigDecimal("2284.38"), "Operador"),
            new Funcionario("Caio", LocalDate.parse("1961-05-02"), new BigDecimal("9836.14"), "Coordenador"),
            new Funcionario("Miguel", LocalDate.parse("1988-10-14"), new BigDecimal("19119.88"), "Diretor"),
            new Funcionario("Alice", LocalDate.parse("1995-01-05"), new BigDecimal("2234.68"), "Recepcionista"),
            new Funcionario("Heitor", LocalDate.parse("1999-11-19"), new BigDecimal("1582.72"), "Operador"),
            new Funcionario("Arthur", LocalDate.parse("1993-03-31"), new BigDecimal("4071.84"), "Contador"),
            new Funcionario("Laura", LocalDate.parse("1994-07-08"), new BigDecimal("3017.45"), "Gerente"),
            new Funcionario("Heloísa", LocalDate.parse("2003-05-24"), new BigDecimal("1606.85"), "Eletricista"),
            new Funcionario("Helena", LocalDate.parse("1996-09-02"), new BigDecimal("2799.93"), "Gerente")
        ));

        // 3.2 - Remover o funcionário “João” da lista.
        funcionarios.removeIf(f -> f.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários com todas suas informações.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Funcionario f : funcionarios) {
            System.out.println("Nome: " + f.getNome() +
                                ", Data de Nascimento: " + f.getDataNascimento().format(formatter) +
                                ", Salário: " + String.format("%,.2f", f.getSalario()) +
                                ", Função: " + f.getFuncao());
        }

        // 3.4 - 10% de aumento de salário
        BigDecimal aumento = new BigDecimal("0.10");
        for (Funcionario f : funcionarios) {
            f.aumentarSalario(aumento);
        }

        // 3.5 - Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
            .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir os funcionários, agrupados por função
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario f : entry.getValue()) {
                System.out.println(f);
            }
        }

        // 3.8 - Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        List<Funcionario> aniversariantes = funcionarios.stream()
            .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
            .collect(Collectors.toList());
        System.out.println("Aniversariantes em Outubro e Dezembro:");
        for (Funcionario f : aniversariantes) {
            System.out.println(f);
        }

        // 3.9 - Imprimir o funcionário com a maior idade.
        Funcionario maisVelho = Collections.max(funcionarios, Comparator.comparing(f -> f.getDataNascimento()));
        System.out.println("Funcionário com a maior idade: Nome: " + maisVelho.getNome() + ", Idade: " + 
                        (LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear()));

        // 3.10 - Imprimir a lista de funcionários por ordem alfabética.
        List<Funcionario> funcionariosOrdenados = new ArrayList<>(funcionarios);
        funcionariosOrdenados.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("Funcionários em ordem alfabética:");
        for (Funcionario f : funcionariosOrdenados) {
            System.out.println(f);
        }

        // 3.11 - Imprimir o total dos salários dos funcionários.
        BigDecimal totalSalarios = funcionarios.stream()
            .map(Funcionario::getSalario)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários: " + String.format("%,.2f", totalSalarios));

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário.
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        for (Funcionario f : funcionarios) {
            BigDecimal salariosMinimos = f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(f.getNome() + " ganha " + salariosMinimos + " salários mínimos.");
        }
    }

}
