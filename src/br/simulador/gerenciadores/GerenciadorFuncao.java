/**
 * Classe para receber por parâmetro uma função e buscar na ASA do Portugol Studio qual o método a ser executado
 */
package br.simulador.gerenciadores;

import br.simulador.gerador.GeradorCodigoJavaSimulador;
import br.univali.portugol.nucleo.asa.ASAPrograma;
import br.univali.portugol.nucleo.asa.ExcecaoVisitaASA;
import br.univali.portugol.nucleo.asa.NoBloco;
import br.univali.portugol.nucleo.asa.NoChamadaFuncao;
import br.univali.portugol.nucleo.asa.NoDeclaracaoFuncao;
import br.univali.portugol.nucleo.asa.NoInclusaoBiblioteca;
import br.univali.portugol.nucleo.asa.VisitanteNulo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Douglas
 */
public class GerenciadorFuncao extends VisitanteNulo {
    
    private final ASAPrograma asa;
    private StringBuilder funcao;
    private String nomeMetodo;
    private ASAPrograma asaGerada;
    private ArrayList<NoDeclaracaoFuncao> listaMetodos;
    private ArrayList<NoInclusaoBiblioteca> listaLibs;
    private ArrayList<String> listaFuncoesUtilizadas;
    
    public GerenciadorFuncao(ASAPrograma asa) {
        this.asa = asa;
        asaGerada = new ASAPrograma();
        listaMetodos = new ArrayList<>();
        listaLibs = new ArrayList<>();
        listaFuncoesUtilizadas = new ArrayList<>();
    }

    /**
     * Método que buscar a declaração de uma função pelo seu nome
     *
     * @param nome_metodo
     * @return
     * @throws ExcecaoVisitaASA
     */
    public ASAPrograma buscar_declaracao_metodo(String nome_metodo) throws ExcecaoVisitaASA {
        this.nomeMetodo = nome_metodo;
        asa.aceitar(this);
        
        if (asaGerada.getListaDeclaracoesGlobais() == null) {
            asaGerada.setListaDeclaracoesGlobais(new ArrayList<>());
        }

//        if(asaGerada.getListaInclusoesBibliotecas() == null){
        asaGerada.setListaInclusoesBibliotecas(new ArrayList<>());
//        }

        //remover_funcoes_nao_utilizadas();
        
        asaGerada.getListaDeclaracoesGlobais().addAll(listaMetodos);
        asaGerada.getListaInclusoesBibliotecas().add(listaLibs.get(0));
        
        GeradorCodigoJavaSimulador gerador = new GeradorCodigoJavaSimulador();
        gerador.gerar_codigo_java(asaGerada);
        
        return asaGerada;
    }

    /**
     * Remove os métodos que não são utilizados na função de simular
     */
    private void remover_funcoes_nao_utilizadas() {
        List<NoDeclaracaoFuncao> listaRemover = new ArrayList<>();
        
        listaMetodos.stream().filter((metodo) -> (!listaFuncoesUtilizadas.contains(metodo.getNome())
                && !metodo.getNome().equalsIgnoreCase(nomeMetodo))).forEachOrdered((metodo) -> {
            listaRemover.add(metodo);
        });
        
        listaRemover.forEach((noDeclaracaoFuncao) -> {
            listaMetodos.remove(noDeclaracaoFuncao);
        });
    }
    
    @Override
    public Object visitar(NoDeclaracaoFuncao declaracaoFuncao) throws ExcecaoVisitaASA {
        
        if (!listaMetodos.contains(declaracaoFuncao)) {
            
            for (NoBloco bloco : declaracaoFuncao.getBlocos()) {
                if (declaracaoFuncao.getNome().equalsIgnoreCase(nomeMetodo)) {
                    if (bloco instanceof NoChamadaFuncao) {
                        listaFuncoesUtilizadas.add(((NoChamadaFuncao) bloco).getNome());
                    }
                }
                
                bloco.aceitar(this);
            }
            
            listaMetodos.add(declaracaoFuncao);
        }
        
        return null;
    }
    
    @Override
    public Object visitar(NoInclusaoBiblioteca noInclusaoBiblioteca) throws ExcecaoVisitaASA {
        
        if (noInclusaoBiblioteca.getNome().equalsIgnoreCase("Experimentos")) {
            if (!listaLibs.contains(noInclusaoBiblioteca)) {
                listaLibs.add(noInclusaoBiblioteca);
            }
        }
        
        return null;
    }
    
}
