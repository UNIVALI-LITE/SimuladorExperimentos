package br.simulador.plugin.biblioteca;

import br.simulador.gerenciadores.GerenciadorComponentes;
import br.simulador.gerenciadores.GerenciadorExecucao;
import br.simulador.gerenciadores.GerenciadorInterface;
import br.simulador.plugin.biblioteca.base.Retalho;
import br.simulador.util.UtilSimulador;
import br.univali.portugol.nucleo.SimuladorPrograma;
import br.univali.portugol.nucleo.bibliotecas.base.Biblioteca;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import static br.univali.portugol.nucleo.bibliotecas.base.TipoBiblioteca.COMPARTILHADA;
import br.univali.portugol.nucleo.bibliotecas.base.anotacoes.Autor;
import br.univali.portugol.nucleo.bibliotecas.base.anotacoes.DocumentacaoBiblioteca;
import br.univali.portugol.nucleo.bibliotecas.base.anotacoes.DocumentacaoFuncao;
import br.univali.portugol.nucleo.bibliotecas.base.anotacoes.DocumentacaoParametro;
import br.univali.portugol.nucleo.bibliotecas.base.anotacoes.NaoExportar;
import br.univali.portugol.nucleo.bibliotecas.base.anotacoes.PropriedadesBiblioteca;
import br.univali.portugol.nucleo.mensagens.ErroExecucao;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@PropriedadesBiblioteca(tipo = COMPARTILHADA)
@DocumentacaoBiblioteca(
        descricao = "Esta biblioteca contém uma série de funcionalidades que permitem a criação de simulações de experimentos físicos ou naturais.",
        versao = "1.0")
public final class Experimentos extends Biblioteca {

    @DocumentacaoFuncao(
            descricao = "Cria n agentes para simulação.",
            parametros
            = {
                @DocumentacaoParametro(nome = "numeroAgentes", descricao = "o número de agentes a ser criado")
                ,
                @DocumentacaoParametro(nome = "aleatorio", descricao = "Os agentes serão criados em lugares aleatórios ou todos na mesma posição")
            },
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void criar_agentes(int numeroAgentes, boolean aleatorio) throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        if (verificar_ambiente_inicializado()) {
            GerenciadorExecucao.getInstance().criar_agentes(numeroAgentes, aleatorio);
        }
    }

//    @DocumentacaoFuncao(
//            descricao = "Executa o método principal da simulação.",
//            //            parametros =
//            //            {
//            //                @DocumentacaoParametro(nome = "graus", descricao = "Quantidade em graus para rotacionar"),
//            //            },
//            retorno = "Sem retorno",
//            //            referencia = "Sem referência",
//            autores
//            = {
//                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
//            }
//    )
    @NaoExportar
    public void simular() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        try {
            GerenciadorExecucao.getInstance().inicializar_ambiente();
        } catch (ErroExecucaoBiblioteca | InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(Experimentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DocumentacaoFuncao(
            descricao = "Permite a criação de variáveis para controle e uso durante a simulação.",
            parametros
            = {
                @DocumentacaoParametro(nome = "nome_atributo", descricao = "Define a identificação do atributo."),
                @DocumentacaoParametro(nome = "valor_padrao", descricao = "Define um valor default para atribuir ao atributo criado")
            },
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void criar_atributo(String nome_atributo, String valor_padrao) throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorExecucao.getInstance().executarMetodo("criar_atributo", 2, nome_atributo, valor_padrao);
    }

    @DocumentacaoFuncao(
            descricao = "Permite controlar a média de uma característica de um agente. Por exemplo, "
            + "exibir em um monitor a média de energia ou velocidade dos agentes.",
            parametros
            = {
                @DocumentacaoParametro(nome = "nome_atributo", descricao = "Define a identificação do atributo.")
            },
            retorno = "Retorna o valor da média de um determinado atributo",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public double media(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException {
        return GerenciadorExecucao.getInstance().media(nome_atributo);
    }

//    @DocumentacaoFuncao(
//            descricao = "Define um valor a um determinado atributo.",
//            parametros
//            = {
//                @DocumentacaoParametro(nome = "nome_atributo", descricao = "Nome do atributo que terá o valor atribuído")
//                ,
//                @DocumentacaoParametro(nome = "valor", descricao = "Novo valor do atributo")
//                ,
//                @DocumentacaoParametro(nome = "id_agente", descricao = "Id do agente que terá o valor atualizado")
//            },
//            retorno = "Sem retorno",
//            autores
//            = {
//                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
//            }
//    )
//    public void definir_valor_atributo(String nome_atributo, String valor, int id_agente) throws ErroExecucaoBiblioteca, InterruptedException {
//        GerenciadorExecucao.getInstance().definir_valor_atributo_por_agente(nome_atributo, valor, id_agente);
//    }
    
    @DocumentacaoFuncao(
            descricao = "Define um valor a um determinado atributo.",
            parametros
            = {
                @DocumentacaoParametro(nome = "nome_atributo", descricao = "Nome do atributo que terá o valor atribuído")
                ,
                @DocumentacaoParametro(nome = "valor", descricao = "Novo valor do atributo")
            },
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void definir_valor_atributo(String nome_atributo, String valor) throws ErroExecucaoBiblioteca, ErroExecucaoBiblioteca, InterruptedException{
        GerenciadorExecucao.getInstance().getAgenteAtual().definir_valor_atributo(nome_atributo, valor);
    }

    @DocumentacaoFuncao(
            descricao = "Mata o agente atual da simulação e o remove do ambiente de simulação.",
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void morrer() throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorExecucao.getInstance().remover_agente_simulacao();
    }

    @DocumentacaoFuncao(
            descricao = "Retorna a coordenada X do agente atual.",
            retorno = "Coordenada X do agente",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int retornar_coordenadaX() throws ErroExecucaoBiblioteca, InterruptedException {
        int coordenadaXAgente = GerenciadorExecucao.getInstance().getAgenteAtual().retornar_coordenada_X();
        UtilSimulador.setLog("X do agente " + GerenciadorExecucao.getInstance().getAgenteAtual().retornar_id() + ": " + coordenadaXAgente);
        return coordenadaXAgente;
    }

    @DocumentacaoFuncao(
            descricao = "Retorna a coordenada Y do agente atual.",
            retorno = "Coordeanda Y do agente",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int retornar_coordenadaY() throws ErroExecucaoBiblioteca, InterruptedException {
        int coordenadaYAgente = GerenciadorExecucao.getInstance().getAgenteAtual().retornar_coordenada_Y();
        UtilSimulador.setLog("Y do agente " + GerenciadorExecucao.getInstance().getAgenteAtual().retornar_id() + ": " + coordenadaYAgente);
        return coordenadaYAgente;
    }

    @DocumentacaoFuncao(
            descricao = "Move o agente n passos(verificar explicação para os passos) para a frente.",
            parametros
            = {
                @DocumentacaoParametro(nome = "quantidade", descricao = "Quantidade de passos para mover")
            },
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void mover_frente(int quantidade) throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorExecucao.getInstance().getAgenteAtual().mover_frente(quantidade);
    }

    @DocumentacaoFuncao(
            descricao = "Faz com que o agente retorne n passos.",
            parametros
            = {
                @DocumentacaoParametro(nome = "quantidade", descricao = "Quantidade de passos para retornar")
            },
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void voltar(int quantidade) throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorExecucao.getInstance().getAgenteAtual().voltar(quantidade);
    }

    @DocumentacaoFuncao(
            descricao = "Gira o agente n graus para a esquerda.",
            parametros
            = {
                @DocumentacaoParametro(nome = "graus", descricao = "Quantidade de graus para o giro")
            },
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void girar_esquerda(int graus) throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        GerenciadorExecucao.getInstance().getAgenteAtual().girar_esquerda(graus);
        GerenciadorInterface.getInstance().renderizar_tela();
    }

    @DocumentacaoFuncao(
            descricao = "Gira o agente n graus para a direita.",
            parametros
            = {
                @DocumentacaoParametro(nome = "graus", descricao = "Quantidade de graus para o giro")
            },
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void girar_direita(int graus) throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        GerenciadorExecucao.getInstance().getAgenteAtual().girar_direita(graus);
        GerenciadorInterface.getInstance().renderizar_tela();
    }

    @DocumentacaoFuncao(
            descricao = "Move o agente de acordo com a quantidade de graus passada por parâmetro. "
            + "É considerado um posicionamento da tela, ou seja, é igual para todos os agentes.",
            parametros
            = {
                @DocumentacaoParametro(nome = "graus", descricao = "Quantidade de graus para o giro")
            },
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void definir_orientacao(int graus) throws ErroExecucaoBiblioteca, InterruptedException {
        UtilSimulador.setLog("Está definindo a orientação");
        GerenciadorExecucao.getInstance().getAgenteAtual().definir_orientacao(graus);
    }

    @DocumentacaoFuncao(
            descricao = "Define uma cor ao agente.",
            parametros
            = {
                @DocumentacaoParametro(nome = "cor", descricao = "Cor para o agente. Utilize a biblioteca Gráficos que já possui "
                        + "cores pré-definidas")
            },
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void definir_cor_agente(int cor) throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorExecucao.getInstance().getAgenteAtual().definir_cor_agente(cor);
    }

    @DocumentacaoFuncao(
            descricao = "Retorna um parâmetro no formato de cadeia de caracteres.",
            parametros
            = {
                @DocumentacaoParametro(nome = "nome_atributo", descricao = "Nome do atributo que será retornado")
            },
            retorno = "Retorna o parâmetro convertido para cadeia de caracteres",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public String retornar_atributo_cadeia(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException {
        return GerenciadorExecucao.getInstance().getAgenteAtual().retornar_atributo_cadeia(nome_atributo);
    }

    @DocumentacaoFuncao(
            descricao = "Retorna um parâmetro no formato de caracter.",
            parametros
            = {
                @DocumentacaoParametro(nome = "nome_atributo", descricao = "Nome do atributo que será retornado")
            },
            retorno = "Retorna o parâmetro convertido para caractere",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public char retornar_atributo_caracter(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException {
        return GerenciadorExecucao.getInstance().getAgenteAtual().retornar_atributo_caracter(nome_atributo);
    }

    @DocumentacaoFuncao(
            descricao = "Retorna um parâmetro no formato inteiro.",
            parametros
            = {
                @DocumentacaoParametro(nome = "nome_atributo", descricao = "Nome do atributo que será retornado")
            },
            retorno = "Retorna o parâmetro convertido para número inteiro",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int retornar_atributo_inteiro(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException {
        return GerenciadorExecucao.getInstance().getAgenteAtual().retornar_atributo_inteiro(nome_atributo);
    }

    @DocumentacaoFuncao(
            descricao = "Retorna um parâmetro no formato lógico.",
            parametros
            = {
                @DocumentacaoParametro(nome = "nome_atributo", descricao = "Nome do atributo que será retornado")
            },
            retorno = "Retorna o parâmetro convertido para lógico",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public boolean retornar_atributo_logico(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException {
        return GerenciadorExecucao.getInstance().getAgenteAtual().retornar_atributo_logico(nome_atributo);
    }

    @DocumentacaoFuncao(
            descricao = "Retorna um parâmetro no formato real.",
            parametros
            = {
                @DocumentacaoParametro(nome = "nome_atributo", descricao = "Nome do atributo que será retornado")
            },
            retorno = "Retorna o parâmetro convertido para número real",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public double retornar_atributo_real(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException {
        return GerenciadorExecucao.getInstance().getAgenteAtual().retornar_atributo_real(nome_atributo);

    }

    @DocumentacaoFuncao(
            descricao = "Retorna a cor do agente atual.",
            retorno = "O código inteiro referente a cor",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int retornar_cor_agente() throws ErroExecucaoBiblioteca, InterruptedException {
        return GerenciadorExecucao.getInstance().getAgenteAtual().retornar_cor_agente();
    }

    @DocumentacaoFuncao(
            descricao = "Retorna a orientação do agente atual.",
            retorno = "A posição em graus referente a orientação",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int retornar_orientacao() throws ErroExecucaoBiblioteca, InterruptedException {
        UtilSimulador.setLog("Buscando a orientação do agente");
        return GerenciadorExecucao.getInstance().getAgenteAtual().retornar_orientacao();
    }

    @DocumentacaoFuncao(
            descricao = "Move o agente até uma posição específica no retalho.",
            parametros
            = {
                @DocumentacaoParametro(nome = "coordenadaX", descricao = "Nova coordenada X")
                ,
                @DocumentacaoParametro(nome = "coordeandaY", descricao = "Nova coordenada Y")
            },
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void ir_ate(int coordenadaX, int coordenadaY) throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        GerenciadorExecucao.getInstance().getAgenteAtual().ir_ate(coordenadaX, coordenadaY);
        GerenciadorInterface.getInstance().renderizar_tela();
    }

    @DocumentacaoFuncao(
            descricao = "Retorna o ID único do agente atual.",
            retorno = "ID do agente",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int retornar_id() throws ErroExecucaoBiblioteca, InterruptedException {
        int id = GerenciadorExecucao.getInstance().getAgenteAtual().retornar_id();
        UtilSimulador.setLog("Retornou o id: " + id);
        return id;
    }

    @DocumentacaoFuncao(
            descricao = "Conta todos os agentes presentes na simulação.",
            retorno = "Número de agentes da simulação",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int contar_agentes() throws ErroExecucaoBiblioteca, InterruptedException {
        return GerenciadorExecucao.getInstance().contar_agentes();
    }

    @DocumentacaoFuncao(
            descricao = "Realiza a atualização dos componentes visuais caso necessário.",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void atualizar_tela() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        GerenciadorInterface.getInstance().renderizar_tela();
    }

    @DocumentacaoFuncao(
            descricao = "Limpa o ambiente de simulação, voltando para a configuração inicial(Verificar).",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void limpar_tudo() throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorInterface.getInstance().limpar_tudo();
    }

    @DocumentacaoFuncao(
            descricao = "Permite a criação de um componente de slider para alteração de configurações durante a simulação",
            parametros
            = {
                @DocumentacaoParametro(nome = "nome", descricao = "Identificação única do componente")
                ,
                @DocumentacaoParametro(nome = "titulo", descricao = "Título do componente")
                ,
                @DocumentacaoParametro(nome = "minimo", descricao = "Valor mínimo permitido do componente")
                ,
                @DocumentacaoParametro(nome = "máximo", descricao = "Valor máximo permitido do componente")
                ,
                @DocumentacaoParametro(nome = "valor_padrao", descricao = "Valor padrão para definir ao criar o componente")
            },
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void criar_slider(String nome, String titulo, double minimo, double maximo, double valor_padrao) throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        GerenciadorInterface.getInstance().criar_slider(nome, titulo, minimo, maximo, valor_padrao);
    }

    @DocumentacaoFuncao(
            descricao = "Cria um componente do tipo Monitor para exibição de alguma variável durante a execução.",
            parametros
            = {
                @DocumentacaoParametro(nome = "nome", descricao = "Identificação única do componente")
                ,
                @DocumentacaoParametro(nome = "titulo", descricao = "Título apresentado no componente")
                ,
                @DocumentacaoParametro(nome = "valor", descricao = "Valor padrão do componente"),},
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void criar_monitor(String nome, String titulo, String valor) throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        GerenciadorInterface.getInstance().criar_monitor(nome, titulo, valor);
    }

    @DocumentacaoFuncao(
            descricao = "Cria um componente do tipo Interruptor que permite ao usuário ligar ou desligar determinada funcionalidade ou característica de uma agente ou ambiente.",
            parametros
            = {
                @DocumentacaoParametro(nome = "nome", descricao = "Identificador do componente")
                ,
                @DocumentacaoParametro(nome = "titulo", descricao = "Título do componente")
                ,
                @DocumentacaoParametro(nome = "valor_padrao", descricao = "Se inicia com verdadeiro ou falso"),},
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void criar_interruptor(String nome, String titulo, boolean valor_padrao) throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        GerenciadorInterface.getInstance().criar_interruptor(nome, titulo, valor_padrao);
    }

    @DocumentacaoFuncao(
            descricao = "Retorna quantos agentes possuem uma determinada cor.",
            parametros
            = {
                @DocumentacaoParametro(nome = "cor", descricao = "Define o código inteiro da cor a ser atribuída ao agente")
            },
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int agentes_com_cor(int cor) throws ErroExecucaoBiblioteca, InterruptedException {
        int agentes_encontrados = GerenciadorExecucao.getInstance().agentes_com_cor(cor);
        UtilSimulador.setLog("Agente com a cor " + cor + " encontrados: " + agentes_encontrados);
        return agentes_encontrados;
    }

    @DocumentacaoFuncao(
            descricao = "Define os limites das bordas e passa aceita um parâmetro com a cor. Neste caso a borda já é destacada com a cor passada por parâmetro.",
            parametros
            = {
                @DocumentacaoParametro(nome = "cor", descricao = "Define o código inteiro da cor a ser atribuída ao agente")
            },
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void definir_bordas(int cor) throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorInterface.getInstance().definir_bordas(cor);
    }

    @DocumentacaoFuncao(
            descricao = "Retorna a quantidade de agentes em um determinado retalho de acordo com as coordenadas passadas por parâmetro.",
            parametros = {
                @DocumentacaoParametro(nome = "coordenadaX", descricao = "Qual a coordenada X desejada")
                ,
                @DocumentacaoParametro(nome = "coordenadaY", descricao = "Qual a coordenada Y desejada")
            },
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void agentes_em_XY(int coordenadaX, int coordenadaY) throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        GerenciadorExecucao.getInstance().agentes_em(coordenadaX, coordenadaY);
    }

    @DocumentacaoFuncao(
            descricao = "Define a cor do retalho atual.",
            parametros = {
                @DocumentacaoParametro(nome = "cor", descricao = "Código inteiro da cor")
            },
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void definir_cor_retalho(int cor) throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        int idRetalhoAgente = meu_retalho();
        GerenciadorInterface.getInstance().definir_cor_retalho(idRetalhoAgente, cor);
    }

    @DocumentacaoFuncao(
            descricao = "Retorna a cor atual do retalho.",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int retornar_cor_retalho() throws ErroExecucaoBiblioteca, InterruptedException {
        return 0;
    }

    @DocumentacaoFuncao(
            descricao = "Retorna qual é o limite máximo da coordenada X do retalho.",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int retornar_valor_max_bordaX() throws ErroExecucaoBiblioteca, InterruptedException {
        return GerenciadorInterface.getInstance().retornar_valor_max_borda_x();
    }
    
    @DocumentacaoFuncao(
            descricao = "Retorna qual é o limite máximo da coordenada X do retalho.",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int retornar_valor_min_bordaX() throws ErroExecucaoBiblioteca, InterruptedException {
        return GerenciadorInterface.getInstance().retornar_valor_min_borda_x();
    }

    @DocumentacaoFuncao(
            descricao = "Retorna qual é o limite máximo da coordenada Y do retalho.",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int retornar_valor_max_bordaY() throws ErroExecucaoBiblioteca, InterruptedException {
        return GerenciadorInterface.getInstance().retornar_valor_max_borda_y();
    }
    
    @DocumentacaoFuncao(
            descricao = "Retorna qual é o limite máximo da coordenada Y do retalho.",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int retornar_valor_min_bordaY() throws ErroExecucaoBiblioteca, InterruptedException {
        return GerenciadorInterface.getInstance().retornar_valor_min_borda_y();
    }

    @DocumentacaoFuncao(
            descricao = "Retorna se o agente irá colidir com uma parede (Para auxiliar no tratamento de colisões",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public boolean colidiu_com_parede_X() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        UtilSimulador.setLog("Verificando se está colidindo com a parede");
        int idRetalho = meu_retalho();
        return GerenciadorInterface.getInstance().verificar_retalho_eh_parede_X(idRetalho);
    }
    
    @DocumentacaoFuncao(
            descricao = "Retorna se o agente irá colidir com uma parede (Para auxiliar no tratamento de colisões",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public boolean colidiu_com_parede_Y() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        UtilSimulador.setLog("Verificando se está colidindo com a parede");
        int idRetalho = meu_retalho();
        return GerenciadorInterface.getInstance().verificar_retalho_eh_parede_Y(idRetalho);
//        GerenciadorExecucao.getInstance().getAgenteAtual().colidiu_borda_X();
    }

//    @DocumentacaoFuncao(
//            descricao = "Retorna se o agente colidiu com alguma parede lateral (direita ou esquerda)",
//            autores = {
//                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
//            }
//    )
//    public boolean colidiu_borda_X() throws ErroExecucaoBiblioteca, InterruptedException {
//        return GerenciadorExecucao.getInstance().getAgenteAtual().colidiu_borda_X();
//    }

//    @DocumentacaoFuncao(
//            descricao = "Retorna se o agente colidiu com alguma parede superior ou inferior",
//            autores = {
//                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
//            }
//    )
//    public boolean colidiu_borda_Y() throws ErroExecucaoBiblioteca, InterruptedException {
//        return GerenciadorExecucao.getInstance().getAgenteAtual().colidiu_borda_Y();
//    }

    @DocumentacaoFuncao(
            descricao = "Retorna o id atual do retalho em que o agente está posicionado",
            autores = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int meu_retalho() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        int id_retalho = 0;
        Retalho retalho = GerenciadorExecucao.getInstance().meu_retalho();

        if (retalho != null) {
            id_retalho = retalho.get_id();
            UtilSimulador.setLog("Meu retalho é: " + id_retalho);
        }
        return id_retalho;
    }

    /**
     * Retorna a lista de agentes atuais da simulação
     *
     * @return
     */
    @NaoExportar
    public List<?> retornar_lista_agentes() {
        return GerenciadorExecucao.getInstance().getListaAgentes();
    }

    /**
     * Define o agente atual da simulação
     *
     * @param agente_atual
     */
    @NaoExportar
    public void definir_agente_atual(Object agente_atual) throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorExecucao.getInstance().definir_agente_atual(agente_atual);
    }

    @DocumentacaoFuncao(
            descricao = "Atualiza o valor de um componente do tipo Monitor procurando pelo seu nome",
            parametros = {
                @DocumentacaoParametro(nome = "nome", descricao = "Identificador único do componente")
                ,
                @DocumentacaoParametro(nome = "novo_valor", descricao = "Novo valor a ser exibido pelo monitor")
            },
            autores = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void atualizar_valor_monitor(String nome, String novo_valor) throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorComponentes.atualizar_valor_monitor(nome, novo_valor);
    }

    @DocumentacaoFuncao(
            descricao = "Define qual a forma o agente assumirá <br>"
            + "Os tipos permitidos são: <br>"
            + "0: Formato de círculo <br>"
            + "1: Formato de uma reta <br>"
            + "Por padrão, caso não seja utilizado este método, os agentes assumirão a forma de círculos. <br>"
            + "Para que as formas sejam aplicadas aos agentes, este método deve ser chamado antes do método \"criar_agentes\"",
            parametros = {
                @DocumentacaoParametro(nome = "forma", descricao = "Define qual é a forma escolhida")
            },
            autores = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void definir_forma_agentes(int forma) throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorExecucao.getInstance().setFormaAgente(forma);
    }

    @DocumentacaoFuncao(
            descricao = "Define quantas execuções terá a simulação",
            parametros = {
                @DocumentacaoParametro(nome = "sim", descricao = "Define se a simulação rodará enquanto o usuário desejar, ou terá apenas uma iteração")
            },
            autores = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void executar_sempre(boolean sim) throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorExecucao.getInstance().setExecutar_sempre(sim);
    }

    /**
     * Método para que a simulação consiga iniciar e parar a execução do
     * programa, definindo qual é o programa atual
     *
     * @param programa_atual
     */
    @NaoExportar
    public void definir_programa_atual(SimuladorPrograma programa_atual) {
        GerenciadorExecucao.getInstance().setSimuladorPrograma(programa_atual);
    }

    /**
     * Método chamada pela simulação para definir que a simulação está rodando
     * ou está parada
     *
     * @param executando
     */
    @NaoExportar
    public void definir_programa_execucao(boolean executando) {
        GerenciadorExecucao.getInstance().setExecutando(executando);
    }

    /**
     * Método para atualizar o número de ticks(passos) da simulação
     *
     * @param total_ticks
     */
    @NaoExportar
    public void atualizar_ticks(int total_ticks) {
        GerenciadorExecucao.getInstance().setTicks(total_ticks);
    }

    /**
     * Verifica se o ambiente foi inicializado para evitar erros ao acessar
     * componentes nulos
     *
     * @throws ErroExecucaoBiblioteca
     */
    private boolean verificar_ambiente_inicializado() throws ErroExecucaoBiblioteca {
        if (!GerenciadorExecucao.getInstance().isAmbienteInicializado()) {
            throw new ErroExecucaoBiblioteca("[Erro Experimentos] O ambiente da simulação deve ser inicializado. Para isso, utilize o botão do plugin de simulação no canto esquerdo abaixo dos ícones padrões do Portugol");
        }
        return true;
    }
    
    @DocumentacaoFuncao(
            descricao = "Inverte o sentido da direção do agente (para facilitar os movimentos)",
            autores = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void inverter_sentido() throws ErroExecucaoBiblioteca, InterruptedException{
        GerenciadorExecucao.getInstance().getAgenteAtual().inverter_sentido();
    }
    
}
