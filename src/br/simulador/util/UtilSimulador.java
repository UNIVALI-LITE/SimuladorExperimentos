/**
 * Classe com funções úteis utilizados em vários locais da aplicação
 */
package br.simulador.util;

import java.util.Random;

/**
 *
 * @author Douglas
 */
public final class UtilSimulador {

    private static int [] paletaCores = {0xFFFFFF, 0xE4E4E4, 
                                         0x888888, 0x222222, 
                                         0xFFA7D1, 0xE50000, 
                                         0xE59500, 0xA06A42, 
                                         0xE5D900, 0x94E044, 
                                         0x02BE01, 0x00D3DD, 
                                         0x0083C7, 0x0000EA, 
                                         0xCF6EE4, 0x820080,
                                         0x00FA11, 0xFA00A6};
    
    /**
     * Retorna um número randômico
     *
     * @param limite - Define qual é o limite para gerar o número randômico
     * @return
     */
    public static int getNumeroRandomico(int limite) {
        Random random = new Random();

        return random.nextInt(limite);
    }
    
    /**
     * Retorna um número randômico no formato Double
     * @param limite
     * @return 
     */
//    public static double getNumeroRandomico(double limite){
//        Random random = new Random((long) limite);
//        
//        return random.nextDouble();
//    }

    /**
     * Centralização do método para exibir mensagens no console
     *
     * @param mensagem
     */
    public static void setLog(String mensagem) {
        System.out.println(mensagem);
    }

    /**
     * Método para converter um valor em Inteiro
     *
     * @param valor
     * @return
     */
    public static int toInt(Object valor) {
        try {
            return Integer.parseInt(String.valueOf(valor));
        } catch (Exception ex) {
            setLog("Ocorreu um erro ao converter para inteiro: " + ex.getMessage());
            return 0;
        }
    }

    /**
     * Método para converter um valor em Double
     *
     * @param valor
     * @return
     */
    public static double toDouble(Object valor) {
        try {
            return Double.parseDouble(String.valueOf(valor));
        } catch (Exception ex) {
            setLog("Ocorreu um erro ao converter para double" + ex.getMessage());
            return 0;
        }
    }

    /**
     * Método para converter um valor em Boolean
     * @param valor
     * @return 
     */
    public static boolean toBoolean(Object valor) {
        try {
            return Boolean.parseBoolean(String.valueOf(valor));
        } catch (Exception ex) {
            setLog("Ocorreu um erro ao converter para boolean" + ex.getMessage());
            return false;
        }
    }
    
    /**
     * Retorna uma cor aleatória a partir de uma paleta de cores padrão
     * @return 
     */
    public static int corRandomica(){
        return paletaCores[getNumeroRandomico(paletaCores.length - 1)];
    }
}
