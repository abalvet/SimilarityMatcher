/*
 * Created on 10 janv. 2006
 * Créé le 10 janv. 2006
 */
package wordsMatcher;
import tools.BufferedPrintFileToEncoding;
import java.io.File;
import org.apache.commons.io.*;
/**
 * @author Balvet Antonio
 * Classe pour tester les différents algorithmes de calcul de plus longues sous-chaînes 
 * et sous-séquences communes.
 */
public class TestLCSAlgorithms {
    int _M = 0;
    int _N = 0;
    int [][] _opt;
    // _opt[i][j] = length of LCS of x[i..M] and y[j..N] 
    
    public void computeLCS(String x, String y){
        double dist = 0;
        _opt = new int[this._M+1][this._N+1];        
        System.out.println("opt : " + _opt.length);
        char cx;
        char cy;
        // compute length of LCS and all subproblems via dynamic programming
        for (int i = this._M-1; i >= 0; i--) {
            for (int j = this._N-1; j >= 0; j--) {
                cx = x.charAt(i);
                cy = y.charAt(j);
                //System.out.println("x:" + cx + " y:" + cy);
                if (cx == cy){
                    _opt[i][j] = _opt[i+1][j+1] + 1;
                    //System.out.println("same: " + i + ":" + j + ":" +_opt[i][j]);
                    //System.out.print(x.charAt(i) + ":" + _opt[i][j] + "\t");
                }                
                else{ 
                    _opt[i][j] = Math.max(_opt[i+1][j], _opt[i][j+1]);
                    //System.out.println("notsame: " + i + ":" + j + ":" +_opt[i][j]);
                    //System.out.print("[" + x.charAt(i) + y.charAt(i) + "]" + ":" + _opt[i][j] + "\t");
                }
            }
            //System.out.println();
        }
        //System.out.println();
        //return dist;
        }
    
    public String getLCS(String x, String y){
        String lcs = "";
        // recover LCS itself and print it to standard output
        int i = 0, j = 0;
        while(i < _M && j < _N) {
            if (x.charAt(i) == y.charAt(j)) {
                //System.out.print(x.charAt(i));
                lcs += x.charAt(i);
                i++;
                j++;
            }
            else if (_opt[i+1][j] >= _opt[i][j+1])
                {
                i++;
                lcs += "-";
                //System.out.print("_");
                }
            else
                {
                j++;
                lcs += "-";
                //System.out.print("_");
                }
        }
        //System.out.println();
        lcs += "\n";
        return lcs;
        }

    
    public int get_M()
        {
            return _M;
        }
    public void set_M(int _m)
        {
            _M = _m;
        }
    public int get_N()
        {
            return _N;
        }
    public void set_N(int _n)
        {
            _N = _n;
        }
    public int[][] get_opt()
        {
            return _opt;
        }
    public void set_opt(int[][] _opt)
        {
            this._opt = _opt;
        }
        
    public static void main(String[] args)
        {
        TestLCSAlgorithms t = new TestLCSAlgorithms();
        File in = new File("c:\\Test\\in.txt");
        String s = "";
        String[]st;
        String x = "";
        String y = "";
        try{
            s = FileUtils.readFileToString(in,"Unicode");
            }
        catch(Exception e){
            e.printStackTrace();
            }
        st = s.split("\n");
        x = st[0];
        y = st[1];
        t.set_M(x.length());
        t.set_N(y.length());
        t.computeLCS(x,y);
        //System.out.println("LCS: ");
        //System.out.println(t.getLCS(x,y));
        File out = new File("c:\\Test\\testLCS.txt");
        /*for(int c = 0; c < x.length(); c++){
            for(int l = 0; l < y.length(); l++){
                System.out.print(t._opt[c][l]+ "\t");
                }
            System.out.println();
            }*/
        BufferedPrintFileToEncoding.printAppended(out,"Unicode","computeLCS:\n" +t.getLCS(x,y)+"\n");
        }
}
