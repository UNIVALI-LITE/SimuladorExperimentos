/**
 * Classe para controle das telas e inicialiação do plugin
 */
package br.simulador.gerenciadores;

import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;

/**
 *
 * @author Douglas
 */
public final class GerenciadorInterface {

    private static GerenciadorInterface instance = null;

    private static GerenciadorDesenho desenho = null;

    /**
     * Instância da classe estática que controla a inicialização da simulação
     *
     * @return
     */
    public static GerenciadorInterface getInstance() {

        if (instance == null) {
            instance = new GerenciadorInterface();
            desenho = new GerenciadorDesenho();
        }

        return instance;
    }

    /**
     * Chama a tela de inicialização do Simulador
     */
    public void inicializarTela() throws ErroExecucaoBiblioteca, InterruptedException {
        desenho.inicializar_tela();
    }

    /**
     * Retorna se a janela ainda está visível e executando
     * @return 
     */
    public boolean esta_executando(){
        return desenho.esta_executando();
    }

    /**
     * Retorna a altura da janela de simulação
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException 
     */
    public int getAlturaSimulacao() throws ErroExecucaoBiblioteca, InterruptedException {
        return desenho.get_altura_janela();
    }

    /**
     * Retorna a largura da janela de simulação
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException 
     */
    public int getLarguraSimulacao() throws ErroExecucaoBiblioteca, InterruptedException {
        return desenho.get_largura_janela();
    }
    
    /**
     * Método que vai desenhar os limites(bordas) da tela
     * @param cor 
     */
    public void definir_bordas(int cor){
        desenho.desenhar_bordas(cor);
    }
    
    /**
     * Retorna o limite mínimo para X
     * @return 
     */
    public int retorna_limite_minimo_borda_X(){
        return desenho.retorna_valor_minimo_borda_X();
    }
    
    /**
     * Retorna o limite mínimo para Y
     * @return 
     */
    public int retorna_limite_minimo_borda_Y(){
        return desenho.retorna_valor_minimo_borda_Y();
    }
    
    /**
     * Retorna o limite máximo para X
     * @return 
     */
    public int retorna_limite_maximo_borda_X(){
        return desenho.retorna_valor_maximo_borda_X();
    }
    
    /**
     * Retorna o limite máximo para Y
     * @return 
     */
    public int retorna_limite_maximo_borda_Y(){
        return desenho.retorna_valor_maximo_borda_Y();
    }       
}
