package br.simulador.plugin.biblioteca.componentes;

import br.simulador.plugin.biblioteca.base.Retalho;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import layout.TableLayout;

/**
 *
 * @author Douglas
 */
public class PainelBase extends javax.swing.JPanel {

    private ArrayList<Point> points;
    private TableLayout layout = null;
    private ArrayList<Retalho> listaRetalhos = null;

    /**
     * Construtor padrão
     */
    public PainelBase() {
        initComponents();
        inicializar();
    }

    /**
     * Inicializa as propriedades necessárias para a execução
     */
    private void inicializar() {
        points = new ArrayList<Point>();
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                points.add(new Point(e.getX(), e.getY()));
                repaint();
            }
        });

        double size[][] = {
            {0.25, 0.25, 0.25, 0.25},
            {50, TableLayout.FILL, 40, 40, 40}};

        layout = new TableLayout(size);
        this.setLayout(layout);
        this.inicializar_lista_retalhos();
    }
    
    /**
     * Inicializa a lista de retalhos da simulação
     */
    private void inicializar_lista_retalhos(){
        listaRetalhos = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < layout.getRow().length; i++) {
            for (int j = 0; j < layout.getColumn().length; j++) {
                listaRetalhos.add(new Retalho(++id, i, j, layout));
            }
        }
    }

    /**
     * Sobrecarga do método de paint para desenhar os agentes na tela 
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        layout.drawGrid(this, g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.red);
        for (Point point : points) {
            g2.fillOval(point.x, point.y, 20, 20);
        }
        
        layout.updatePoints(points);
    }
    
    /**
     * Chama os métodos que retornam quais as cores e os agentes em uma determinada posição na tabela
     * (Apenas para testes) ----- Remover
     */
    public void get_cores(){
        layout.showColors();
        layout.objectsHere(points);
        layout.objectsInRowColumn(1, 1, "agents");
        layout.setColor(1, 1, Color.BLUE);
        layout.getMaxBorderX(1, 0);
        layout.getMaxBorderX(1, 1);
        layout.getMaxBorderY(0, 0);
        layout.getMaxBorderY(1, 1);
        this.mostrarRetalhos();
        repaint();
    }
    
    
    private void mostrarRetalhos(){
        StringBuilder sbRetalho = new StringBuilder();
        listaRetalhos.forEach((retalho) -> {
            sbRetalho.append("Id: ").append(retalho.getId())
                    .append("\n")
                    .append("Linha: ")
                    .append(retalho.getLinha())
                    .append("\n")
                    .append("Coluna: ")
                    .append(retalho.getColuna())
                    .append("\n");
                    
                    
        });
        
        System.out.println("-------------------------------");
        System.out.println(sbRetalho.toString());
        System.out.println("-------------------------------");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

