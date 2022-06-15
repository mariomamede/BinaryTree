import java.util.*;

public class Main {

    static class No {
        int valor;
        No esquerda;
        No direita;

        No(int valor) {
            this.valor = valor;
            direita = null;
            esquerda = null;
        }
    }
    
    static class ArvoreBinaria {

        No raiz;
        boolean debug = false;

        public void adicionar(int valor) {
            raiz = adicionarRecursivo(raiz, valor);
        }

        private No adicionarRecursivo(No atual, int valor) {

            if (atual == null) {
                return new No(valor);
            }

            if (valor < atual.valor) {
                if(debug)
                    System.out.println("Valor " + valor + " inserido a esquerda do nó " + atual.valor);
                atual.esquerda = adicionarRecursivo(atual.esquerda, valor);
            } else if (valor > atual.valor) {
                if(debug)
                    System.out.println("Valor " + valor + " inserido a direita do nó " + atual.valor);
                atual.direita = adicionarRecursivo(atual.direita, valor);
            }

            return atual;
        }

        public boolean vazio() {
            return raiz == null;
        }

        public int tamanho() {
            return tamanhoRecursivo(raiz);
        }

        private int tamanhoRecursivo(No atual) {
            return atual == null ? 0 : tamanhoRecursivo(atual.esquerda) + 1 + tamanhoRecursivo(atual.direita);
        }

        public void remover(int valor) {
            raiz = removerRecursivo(raiz, valor);
        }

        private No removerRecursivo(No atual, int valor) {
            if (atual == null) {
                return null;
            }

            if (valor == atual.valor) {
                if (atual.esquerda == null && atual.direita == null) {
                    return null;
                }

                if (atual.direita == null) {
                    return atual.esquerda;
                }

                if (atual.esquerda == null) {
                    return atual.direita;
                }

                int smallestvalor = MenorValor(atual.direita);
                atual.valor = smallestvalor;
                atual.direita = removerRecursivo(atual.direita, smallestvalor);
                return atual;
            }
            if (valor < atual.valor) {
                atual.esquerda = removerRecursivo(atual.esquerda, valor);
                return atual;
            }

            atual.direita = removerRecursivo(atual.direita, valor);
            return atual;
        }

        private int MenorValor(No raiz) {
            return raiz.esquerda == null ? raiz.valor : MenorValor(raiz.esquerda);
        }

        private void imprimir(int valor) {
            System.out.print(" " + valor);        
        }

        public void imprimirInOrdem(No No) {
            if (No != null) {
                imprimirInOrdem(No.esquerda);
                imprimir(No.valor);
                imprimirInOrdem(No.direita);
            }
        }

        public void imprimirPreOrdem(No No) {
            if (No != null) {
                imprimir(No.valor);
                imprimirPreOrdem(No.esquerda);
                imprimirPreOrdem(No.direita);
            }
        }

        public void imprimirPosOrdem(No No) {
            if (No != null) {
                imprimirPosOrdem(No.esquerda);
                imprimirPosOrdem(No.direita);
                imprimir(No.valor);
            }
        }

        public void imprimirArvore() {
            if (raiz == null) {
                return;
            }

            Queue<No> Nos = new LinkedList<>();
            Nos.add(raiz);

            while (!Nos.isEmpty()) {

                No No = Nos.remove();

                System.out.print(" " + No.valor);

                if (No.esquerda != null) {
                    Nos.add(No.esquerda);
                }

                if (No.direita != null) {
                    Nos.add(No.direita);
                }
            }
        }
    }

    public static void imprementacaoArvoreBinaria(int quantidade)
    {
        ArvoreBinaria arvore = new ArvoreBinaria();

        System.out.printf("Inserindo %d dados com ArvoreBinaria()\n", quantidade);
        
        long startTime = System.currentTimeMillis();
        
        for(int i = 0; i < quantidade; i++)
        {
            arvore.adicionar(i);
        }
        
        long stopTime = System.currentTimeMillis();

        System.out.println("O tempo decorrido foi " + (stopTime - startTime) + " milissegundos.");
    }

    public static void imprementacaoTreeSet(int quantidade)
    {
        Set<Integer> treeSet = new TreeSet<>();

        System.out.printf("Inserindo %d dados com TreeSet\n", quantidade);
        
        long startTime = System.currentTimeMillis();
        
        for(int i = 0; i < quantidade; i++)
        {
            treeSet.add(i);
        }
        
        long stopTime = System.currentTimeMillis();

        System.out.println("O tempo decorrido foi " + (stopTime - startTime) + " milissegundos.");
    }

    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        
        arvore.adicionar(21);
        arvore.adicionar(15);
        arvore.adicionar(14);
        arvore.adicionar(70);
        arvore.adicionar(82);
        arvore.adicionar(16);
        arvore.adicionar(65);
        arvore.adicionar(61);
        arvore.adicionar(18);
        arvore.adicionar(68);
        arvore.adicionar(17);
        
        System.out.print("Pos Ordem: ");
        arvore.imprimirPosOrdem(arvore.raiz);
        System.out.print("\n");
        System.out.print("In Ordem: ");
        arvore.imprimirInOrdem(arvore.raiz);
        System.out.print("\n");
        System.out.print("Pre Ordem: ");
        arvore.imprimirPreOrdem(arvore.raiz);
        System.out.print("\n\n");

        //benchmark

        int qnt = 1000;

        imprementacaoArvoreBinaria(qnt);

        imprementacaoTreeSet(qnt);
    }
}