import java.util.Random;
import java.util.Scanner;
public class Main{
    static int countItera;
    static int countInstru;
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Digite o tamanho da entrada");
        int tamanhoDaEntrada=in.nextInt();
        System.out.println("Digite o tamanho do padrao");
        int tamanhoDoPadrao=in.nextInt();
        in.close();
        String p=geraString(tamanhoDoPadrao);
        String t=geraString(tamanhoDaEntrada);
        testa(p,t);
    }

    public static void testa(String p, String t){
        /*zera os contadores, chama os metodos que tem varios incrementos nos contadores recem
        zerados e printa o resultado de iteracoes e instrucoes*/
        countInstru=0;
        countItera=0;
        KMPSearch(p,t);
        System.out.println("Iteracoes: "+countItera+"   Instrucoes: "+countInstru);
    }

    public static void KMPSearch(String pat, String txt) 
	{ 
		int M = pat.length(); 
		int N = txt.length(); 

		// cria lps[] que vai guardar o maior 
		// valor prefixo sufixo para o padrão 
		int lps[] = new int[M]; 
		int j = 0; // index for pat[] 

		// Calcula lps[] 
		computeLPSArray(pat, M, lps); 

        int i = 0; // index for txt[] 
        countInstru+=8;//8 instrucoes acima disso (as do metodo estao com ++ dentro do metodo)
        countInstru++;//o count do teste do while vai dar false uma vez e nao vai ser contabilizado, corrigimos isso com esse count++
        while (i < N) { 
            countInstru++;//testou para entrar no while n vezes (+1 instrucao)
            countItera++;//cada vez q entra no while eh uma iteracao a mais
            countInstru+=3;//independente de se for entrar ou nao no if, vai fazer 3 instru pra testar
			if (pat.charAt(j) == txt.charAt(i)) { 
				j++; 
                i++; 
                countInstru+=4;//se entrou faz 4 instrucoes
            } 
            countInstru++;//idenpente de se for entrar ou nao no if, vai fazer 1 instru pra testar
			if (j == M) { 
				System.out.println("Found pattern "
								+ "at index " + (i - j)); 
                j = lps[j - 1]; 
                countInstru+=7;//7 instrucoes dentro do if (2 concatena string, 1 metodo, 2 calculos, 2 atribuicoes)
			} 

			// mismatch após j matches 
			else if (i < N && pat.charAt(j) != txt.charAt(i)) { 
				// Não faz match dos caracteres lps[0..lps[j-1]], 
                // não é necesssário, eles combinarão 
                countInstru+=5;//se entrou testou e o teste tem 5 instrucoes
                countInstru++;//teste do proximo if
				if (j != 0){
                    j = lps[j - 1]; 
                    countInstru+=3;//3 instrucoes se entrou no if
                }
				else{
                    i = i + 1;
                    countInstru+=2;//2 instrucoes se entrou no else
                } 
			}else countInstru+=5;//se nao entrou no else if pelo menos testou e o teste tem 5 instru
		} 
	} 

	public static void computeLPSArray(String pat, int M, int lps[]) 
	{ 
		// tamanho do maior prefixo sufixo anterior 
		int len = 0; 
		int i = 1; 
		lps[0] = 0; // lps[0] is always 0 

        // loop calcula lps[i] for i = 1 to M-1 
        countInstru+=3;//3 instrucoes acima disso (as do metodo estao com ++ dentro do metodo)
        countInstru++;//o count do teste do while vai dar false uma vez e nao vai ser contabilizado, corrigimos isso com esse count++
		while (i < M) { 
            //se entrou aqui é porque iterou mais uma vez (ja que este metodo é chamado dentro do KMP)
            //countItera++;//se entrou no while eh pq iterou +1 vez
            countInstru++;//testou para entrar no while n vezes (+1 instrucao)
            countInstru+=3;//independente de entrar no if ou else vai fazer o teste que custa 3 instrucoes
            if (pat.charAt(i) == pat.charAt(len)) { 
				len++; 
				lps[i] = len; 
                i++; 
                countInstru+=5;//se entrou aqui fez 5 instrucoes
			} 
			else // (pat[i] != pat[len]) 
			{ 
                countInstru++;//independente de entrar no proximo if ou else vai fazer o teste de 1 instrucao
				if (len != 0) { 
                    len = lps[len - 1]; 
                    countInstru+=3;//se entrou fez 3 instrucoes
				} 
				else // if (len == 0) 
				{ 
					lps[i] = len; 
                    i++; 
                    countInstru+=3;//se entrou fez 3 instrucoes
				} 
			} 
		} 
    } 

    private static String geraString(int size) {
        StringBuilder res = new StringBuilder();;
        String charStr = "ABCDEFGHIJKLMOPQRSTWXYZ";
        int posCharStr;
        Random random = new Random();
        for(int i =0;i<size;i++){
            posCharStr=random.nextInt();
            if(posCharStr<0)posCharStr*=-1;
            res.append(charStr.charAt(posCharStr%charStr.length()));
        }
        
        return res.toString();
    }
}