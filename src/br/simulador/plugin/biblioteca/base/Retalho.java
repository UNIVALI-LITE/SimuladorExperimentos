package br.simulador.plugin.biblioteca.base;

/**
 *
 * @author Douglas
 */
public class Retalho {
    
    private int cor;
    private int coordenadaX;
    private int coordenadaY;
    private boolean parede;
    private final int id;
    /*
        Construtor padrão
    */
    public Retalho(int id, int cor) {
        this.id = id;
        this.cor = cor;
    }

    public void set_cor(int cor){
        this.cor = cor;
    }

    //Verificar o tipo de retorno deste método
    public void agentes_aqui() {
        
    }

    //Verificar Retorno
    public void agentes_em_XY(int coordenadaX, int coordenadaY) {
        
    }
    
    public void definir_cor_retalho(int cor) {
//        layout.setColor(cor, coluna, Color.yellow);//Verificar coloração 
    }
    
    public int retornar_cor_retalho() {
        return cor;
//        return layout.getColor(this.linha, this.coluna).getRGB();
    }
    
    public double retornar_max_borda_x() {
        return 0;
    }
    
    public double retornar_max_borda_y() {
        return 0;
    }

    /**
     * Atribui as coordenadas equivalentes da tela aos agentes
     * @param x
     * @param y 
     */
    public void definir_coordenadas(int x, int y) {
        this.coordenadaX = x;
        this.coordenadaY = y;
    }

    /**
     * Retorna a coordenada x do retalho
     * @return 
     */
    public int get_coordenadaX() {
        return coordenadaX;
    }

    /**
     * Retorna a coordenada Y do retalho
     * @return 
     */
    public int get_coordenadaY() {
        return coordenadaY;
    }

    /**
     * Retorna se o retalho faz parte de uma parede (para tratamento de colisões)
     * @return 
     */
    public boolean eh_parede(){
        return parede;
    }
    
    /**
     * Define se o retalho será uma parede (para tratamento de colisões)
     * @param eh_parede 
     */
    public void definir_como_parede(boolean eh_parede){
        this.parede = eh_parede;
    }

    /**
     * Retorna o identificado único do retalho
     * @return 
     */
    public int get_id() {
        return id;
    }
    
    

}
