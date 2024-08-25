package Q06_grafo;

public class UnionFind {
    private int[] pai;
    private int[] tamanho;

    public UnionFind(int n) {
        pai = new int[n];
        tamanho = new int[n];

        for (int i = 0; i < n; i++) {
            pai[i] = i;
            tamanho[i] = 1;
        }
    }

    public int find(int p) {
        if (p != pai[p]) {
            pai[p] = find(pai[p]);
        }
        return pai[p];
    }

    public void union(int p, int q) {
        int raizP = find(p);
        int raizQ = find(q);

        if (raizP != raizQ) {
            if (tamanho[raizP] < tamanho[raizQ]) {
                pai[raizP] = raizQ;
                tamanho[raizQ] += tamanho[raizP];
            } else {
                pai[raizQ] = raizP;
                tamanho[raizP] += tamanho[raizQ];
            }
        }
    }
}
