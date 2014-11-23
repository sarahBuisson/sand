package fr.perso.sand.model;

/**
 * Created by sbuisson on 18/11/2014.
 */
public class VecteurUnitaire extends VecteurInvariant {
    int index;
    int hashCode;

    private VecteurUnitaire(int x, int y) {
        super(x, y);
    }

    public static final VecteurUnitaire[] vecteurs;
    private static final VecteurUnitaire[][] vecteursSquare;

    static {
        vecteurs = new VecteurUnitaire[8];
        vecteursSquare = new VecteurUnitaire[3][3];
        int n = 0;

        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                if (i != 0 || j != 0) {


                    VecteurUnitaire vecteur = new VecteurUnitaire((i),
                            (int) (j));

                    vecteursSquare[i + 1][j + 1] = vecteur;

                }

        vecteurs[0]=vecteursSquare[1][0];
        vecteurs[1]=vecteursSquare[2][0];
        vecteurs[2]=vecteursSquare[2][1];
        vecteurs[3]=vecteursSquare[2][2];
        vecteurs[4]=vecteursSquare[1][2];
        vecteurs[5]=vecteursSquare[0][2];
        vecteurs[6]=vecteursSquare[0][1];
        vecteurs[7]=vecteursSquare[0][0];

        for(int i=0;i<8;i++){
            vecteurs[i].index=i;
        }

    }


    public static VecteurUnitaire get(int x, int y) {
        return vecteursSquare[x + 1][y + 1];
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public int getRealX() {
        return x;
    }

    @Override
    public int getRealY() {
        return y;
    }

    public VecteurUnitaire getVecteurAfter(){
        return vecteurs[(this.index+1)%8];
    }
    public VecteurUnitaire getVecteurBefore(){

        return vecteurs[(8+this.index-1)%8];
    }
    public VecteurUnitaire getVecteurAfter(int n){
        return vecteurs[(8+this.index+n)%8];
    }
    public VecteurUnitaire getVecteurBefore(int n){

        return vecteurs[(8+this.index-n)%8];
    }
}
