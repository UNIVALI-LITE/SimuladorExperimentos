/**
 * Classe para controle das telas e inicialiação do plugin
 */
package br.simulador.gerenciadores;

import br.simulador.inicializador.Inicializador;
import br.simulador.plugin.biblioteca.base.IAgente;
import br.simulador.plugin.biblioteca.base.Retalho;
import br.simulador.plugin.biblioteca.componentes.PainelBase;
import br.simulador.ui.PainelSimulacao;
import br.univali.portugol.nucleo.ProgramaVazio;
import br.univali.portugol.nucleo.bibliotecas.Graficos;
import br.univali.portugol.nucleo.bibliotecas.Mouse;
import br.univali.portugol.nucleo.bibliotecas.base.Biblioteca;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Douglas
 */
public final class GerenciadorInicializacao {

    public static GerenciadorInicializacao instance = null;

    public static Inicializador inicializador = null;

    private static JFrame frame = null;

    private PainelSimulacao ambienteSimulacao = null;

    final int LARGURA = 32;
    final int ALTURA = 32;
    final int tile = 20;
    int cor_atual = 3;

//    Retalho[][] canvas = new Retalho[ALTURA][LARGURA];
    Retalho[][] canvas = new Retalho[ALTURA][LARGURA];
    int[] cores = {0xFFFFFF, 0xE4E4E4, 0x888888, 0x222222, 0xFFA7D1, 0xE50000, 0xE59500, 0xA06A42, 0xE5D900, 0x94E044, 0x02BE01, 0x00D3DD, 0x0083C7, 0x0000EA, 0xCF6EE4, 0x820080};

    Graficos g = new Graficos();
    Mouse m = new Mouse();

    /**
     * Instância da classe estática que controla a inicialização da simulação
     *
     * @return
     */
    public static GerenciadorInicializacao getInstance() {

        if (instance == null) {
            instance = new GerenciadorInicializacao();
        }

        return instance;
    }

    /**
     * Chama a tela de inicialização do Simulador
     */
    public void inicializarTela() throws ErroExecucaoBiblioteca, InterruptedException {
//        SwingUtilities.invokeLater(() -> {

        try {
            g.inicializar(new ProgramaVazio(), null);
            ArrayList<Biblioteca> lista = new ArrayList<>();
            lista.add(g);
            m.inicializar(new ProgramaVazio(), lista);
            for (int i = 0; i < ALTURA; i++) {
                for (int j = 0; j < LARGURA; j++) {
                    canvas[i][j] = new Retalho();
                }
            }

            g.iniciar_modo_grafico(true);
            g.definir_dimensoes_janela(LARGURA * tile, (ALTURA + 2) * tile);
            rodar();
        } catch (ErroExecucaoBiblioteca | InterruptedException ex) {
            Logger.getLogger(GerenciadorInicializacao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void rodar() throws ErroExecucaoBiblioteca, InterruptedException {

        new Thread(() -> {
            while (g.get_janela().estaVisivel()) {
                try {
                    desenhar();
                    controle();
                    g.renderizar();
                } catch (ErroExecucaoBiblioteca ex) {
                    Logger.getLogger(GerenciadorInicializacao.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GerenciadorInicializacao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();

    }

    private void desenhar() throws ErroExecucaoBiblioteca, InterruptedException {
        g.definir_titulo_janela("Simulador de Experimentos");
        for (int i = 0; i < ALTURA; i++) {
            for (int j = 0; j < LARGURA; j++) {
                g.definir_cor(cores[canvas[i][j].retornar_cor_retalho()]);
                g.desenhar_retangulo(j * tile, i * tile, tile, tile, false, true);

            }
        }

        ArrayList<IAgente> listaAgente = GerenciadorExecucao.getInstance().getListaAgentes();

        if (listaAgente != null && listaAgente.size() > 0) {
            for (IAgente agente : listaAgente) {
                g.definir_cor(0xE59500);
                g.desenhar_elipse(agente.retornar_coordenada_X(), agente.retornar_coordenada_Y(), 10, 10, true);
            }
        }

        int t = m.posicao_y() / tile;
        int j = m.posicao_x() / tile;
        if (t < ALTURA) {
            g.definir_cor(cores[cor_atual]);
            g.definir_opacidade(128);
            g.desenhar_elipse(j * tile, t * tile, tile, tile, true);
            g.definir_opacidade(255);
        }

        for (int i = 0; i < 16; i++) {
            g.definir_cor(cores[i]);
            g.desenhar_retangulo(i * 2 * tile, ALTURA * tile, 2 * tile, 2 * tile, false, true);
        }

        g.definir_cor(0x222222);
        g.desenhar_retangulo(0, ALTURA * tile, LARGURA * tile, 3, false, true);
    }

    private void controle() throws ErroExecucaoBiblioteca, InterruptedException {
        int i = m.posicao_y() / tile;
        int j = m.posicao_x() / tile;
        if (m.botao_pressionado(m.BOTAO_ESQUERDO)) {
            if (i < ALTURA && j < LARGURA) {
                canvas[i][j].set_cor(cor_atual);
            } else {
                cor_atual = j / 2;
            }
        }
    }

    /**
     * Retorna a instância do ambiente onde está ocorrendo a simulação
     *
     * @return
     */
    public PainelBase getAmbienteSimulacao() {
        return ambienteSimulacao.getPainelSimulacao();
    }

    /**
     * Retorna o ambiente todo da simulação, não somente o painel onde está
     * executando a simulação
     *
     * @return
     */
    public PainelSimulacao getJanelaSimulador() {
        return ambienteSimulacao;
    }

    /**
     * Método para atualização de todos os componentes exibidos na tela de
     * simulação
     */
    public void atualizar_tela() {
        ambienteSimulacao.atualizar_tela(frame);
    }

    public int getAlturaSimulacao() throws ErroExecucaoBiblioteca, InterruptedException {
        return g.altura_janela();
    }

    public int getLarguraSimulacao() throws ErroExecucaoBiblioteca, InterruptedException {
        return g.largura_janela();
    }
}
