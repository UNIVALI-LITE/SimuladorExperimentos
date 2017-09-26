/**
 * Classe para testes dos métodos dos agentes
 */
package br.simulador.testes;

import br.simulador.gerenciadores.GerenciadorExecucao;
import br.simulador.gerenciadores.GerenciadorInicializacao;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;

/**
 *
 * @author Douglas
 */
public class ExperimentosAgenteTest {
    
    public static void main(String[] args) throws InterruptedException, ErroExecucaoBiblioteca {
        new ExperimentosAgenteTest().testarFuncionalidades();
    }
    
    public void testarFuncionalidades() throws InterruptedException, ErroExecucaoBiblioteca{
        GerenciadorInicializacao.getInstance().inicializarTela();
        
        Thread.sleep(1000);//Para esperar a tela ser criada, pois ocorreu null pointer ao buscar componentes que ainda não foram criados
        
        GerenciadorExecucao.getInstance().criar_agentes(5, true);
        
        GerenciadorExecucao.getInstance().executarMetodo("retornar_coordenada_X");
        GerenciadorExecucao.getInstance().executarMetodo("retornar_coordenada_Y");
        GerenciadorExecucao.getInstance().executarMetodo("retornar_id");
        
        GerenciadorExecucao.getInstance().executarMetodo("criar_atributo", 1, "velocidade");
        GerenciadorExecucao.getInstance().executarMetodo("retornar_atributo_cadeia", 1, "velocidade");
    }
}
