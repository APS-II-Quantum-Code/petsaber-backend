package quantum_code.petgame;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class PetGame
{
	//Leitor de texto do usuario
	Scanner sc = new Scanner(System.in);

	//Leitor de tempo
	Timer timer = new Timer();

	//Campos de variaveis
	public String nome = "";
	public int racao_seca = 0;
	public int racao_molhada = 0;
	public int comida_natural = 0;
	public int fome = 10;
	public int dinheiro = 0;
	public int tempo = 60 * 60 * 1000;

	public PetGame() {
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (fome<10) {
					System.out.println("Fome +1");
					fome++;
				}
			}
		}, tempo, tempo);
		novoDinheiro();
		novoDinheiro();
		novoDinheiro();
		novoDinheiro();
		novoDinheiro();
		novoDinheiro();
		// Chamada para colocar o nome do pet
		System.out.println("Qual sera o nome do seu pet?");
		nome = sc.nextLine();
		System.out.println("Seja bem vindo! "+nome+ " esta te aguardando!");
		menu();
	}

	public static void main(String[] args) {
		PetGame pg = new PetGame();
	}
	/*Sistema para adicionar pontos [Adicionar quando concluir uma licao
	durante a trilha <PetGame.novoDinheiro()> ]*/
	public void novoDinheiro() {
		int valor = (int)(Math.random()*15)+5;
		dinheiro += valor;
		System.out.println("+"+valor+"$");
	}
	/*Funcao para comprar comida*/
	public void comprar(int comida, int quantidade, int valor) {
		switch(comida) {
		case 1 -> {
				//Racao Seca
				dinheiro -= valor * quantidade;
				racao_seca += quantidade;
				System.out.println("-"+(quantidade*valor)+"$");
				System.out.println("+"+(quantidade)+" Racao Seca");
				break;
			}
		case 2 -> {
				//Racao Molhada
				dinheiro -= valor * quantidade;
				racao_molhada += quantidade;
				System.out.println("-"+(quantidade*valor)+"$");
				System.out.println("+"+(quantidade)+" Racao Molhada");
				break;
			}
		case 3 -> {
				//Comida Natural
				dinheiro -= valor * quantidade;
				comida_natural += quantidade;
				System.out.println("-"+(quantidade*valor)+"$");
				System.out.println("+"+(quantidade)+" Comida Natural");
				break;
			}
			default -> {
				System.out.println("Opcao invalida");
				break;
			}
		}
	}
	/*Funcao para gastar a comida*/
	public void comer(int comida) {
		switch(comida) {
		case 1 -> {
				//Racao Seca
				racao_seca-=1;
				fome-=2;
				System.out.println("-1 Racao Seca");
				System.out.println("-2 fome :)");
				break;
			}
		case 2 -> {
				//Racao Molhada
				racao_molhada-=1;
				fome-=4;
				System.out.println("-1 Racao Molhada");
				System.out.println("-4 fome :)");
				break;
			}
		case 3 -> {
				//Comida Natural
				comida_natural-=1;
				fome-=5;
				System.out.println("-1 Comida Natural");
				System.out.println("-5 fome :)");
				break;
			}
			default -> {
				System.out.println("Opcao invalida");
				break;
			}
		}
	}
	//Funcao para imprimir status na tela
	public void imprimir() {
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Nome do pet: " + nome);
		System.out.println("Nivel de fome do pet: " + fome);
		System.out.println("Quantidade de comida do pet:");
		System.out.println("- Racao Seca " + racao_seca);
		System.out.println("- Racao Molhada " + racao_molhada);
		System.out.println("- Comida Natural " + comida_natural);
		System.out.println("Dinheiro: " + dinheiro+"$");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		//Verificando Status de Fome
		if (fome>7)System.out.println("Seu pet esta com muita fome, alimente ele!!\n");
		else if (fome>3)System.out.println("Seu pet esta com fome, tadinho!\n");
		else System.out.println("Seu pet esta sem fome, parabens!!\n");
	}

	//Funcao Menu
	public void menu() {
		System.out.println("\nMenu");
		System.out.println("1 - Visualizar Status;");
		System.out.println("2 - Alimentar pet;");
		System.out.println("3 - Comprar comida;");
		System.out.println("4 - Sair.");
		String escolha = sc.nextLine();
		try {
			int numeroEscolha = Integer.parseInt(escolha);
			// LC3gica para o nC:mero escolhido (se a conversC#o foi bem-sucedida)
		} catch (NumberFormatException e) {
			// Aconteceu um erro ao converter a string para int,
			// entC#o a string nC#o tinha um formato numC)rico vC!lido.
			escolha = "5";
		}
		System.out.println();
		switch(Integer.parseInt(escolha)) {
		case 1 -> {
				imprimir();
				menu();
				break;
			}
		case 2 -> {
				if (fome>1) {
					if ((racao_seca+racao_molhada+comida_natural) > 0) {
						System.out.println("Voce tem:");
						System.out.println("1 - Racao Seca: "+racao_seca);
						System.out.println("2 - Racao Molhada: "+racao_molhada);
						System.out.println("3 - Comida Natural: "+comida_natural);
						System.out.println("Qual deseja oferecer para o pet?");
						String comida = sc.nextLine();

						try {
							int numeroComida = Integer.parseInt(comida);
							// LC3gica para o nC:mero escolhido (se a conversC#o foi bem-sucedida)
						} catch (NumberFormatException e) {
							// Aconteceu um erro ao converter a string para int,
							// entC#o a string nC#o tinha um formato numC)rico vC!lido.
							comida = "4";
						}

						comer(Integer.parseInt(comida));
					}
					else System.out.println("Voce nao tem comida, precisa comprar mais :(");
				}
				else System.out.println("Seu pet ja esta cheio, alimente mais tarde.");
				menu();
				break;
			}
		case 3 -> {
				System.out.println("Cada racao custa:");
				System.out.println("1 - Racao Seca: 10");
				System.out.println("2 - Racao Molhada: 25");
				System.out.println("3 - Comida Natural: 30");
				System.out.println("Qual deseja comprar para o pet?");
				String comida = sc.nextLine();

				try {
					int numeroComida = Integer.parseInt(comida);
					// LC3gica para o nC:mero escolhido (se a conversC#o foi bem-sucedida)
				} catch (NumberFormatException e) {
					// Aconteceu um erro ao converter a string para int,
					// entC#o a string nC#o tinha um formato numC)rico vC!lido.
					comida = "4";
				}

				switch(Integer.parseInt(comida)) {
				case 1 -> {
						//Racao Seca
						int valor = 10;
						if (dinheiro>=valor) {
							int max = (dinheiro-(dinheiro%valor))/valor;
							System.out.println("Voce pode comprar ate "+max+" Racao Seca");
							System.out.println("Deseja comprar quantas comidas?");
							String quantidade = sc.nextLine();

							try {
								int numeroQuantidade = Integer.parseInt(quantidade);
								// LC3gica para o nC:mero escolhido (se a conversC#o foi bem-sucedida)
							} catch (NumberFormatException e) {
								// Aconteceu um erro ao converter a string para int,
								// entC#o a string nC#o tinha um formato numC)rico vC!lido.
								quantidade = "0";
							}

							if (Integer.parseInt(quantidade)>max)System.out.println("Voce digitou um numero maior que o maximo, tente novamente...");
							else if (Integer.parseInt(quantidade)<0)System.out.println("Voce digitou um numero menor que 0, tente novamente...");
							else if (Integer.parseInt(quantidade)==0)System.out.println("Voce digitou uma opcao invalida, cancelando pedido.");
							else comprar(Integer.parseInt(comida),Integer.parseInt(quantidade),valor);
						}
						else {
							System.out.println("Voce nao tem dinheiro o suficiente para comprar uma Racao Seca :(");
						}
						break;
					}
				case 2 -> {
						//Racao Molhada
						int valor = 25;
						if (dinheiro>=valor) {
							int max = (dinheiro-(dinheiro%valor))/valor;
							System.out.println("Voce pode comprar ate "+max+" Racao Molhada");
							System.out.println("Deseja comprar quantas comidas?");
							String quantidade = sc.nextLine();

							try {
								int numeroQuantidade = Integer.parseInt(quantidade);
								// LC3gica para o nC:mero escolhido (se a conversC#o foi bem-sucedida)
							} catch (NumberFormatException e) {
								// Aconteceu um erro ao converter a string para int,
								// entC#o a string nC#o tinha um formato numC)rico vC!lido.
								quantidade = "0";
							}

							if (Integer.parseInt(quantidade)>max)System.out.println("Voce digitou um numero maior que o maximo, tente novamente...");
							else if (Integer.parseInt(quantidade)<0)System.out.println("Voce digitou um numero menor que 0, tente novamente...");
							else if (Integer.parseInt(quantidade)==0)System.out.println("Voce digitou uma opcao invalida, cancelando pedido.");
							else comprar(Integer.parseInt(comida),Integer.parseInt(quantidade),valor);
						}
						else {
							System.out.println("Voce nao tem dinheiro o suficiente para comprar uma Racao Molhada :(");
						}
						break;
					}
				case 3 -> {
						//Comida Natural
						int valor = 30;
						if (dinheiro>=valor) {
							int max = (dinheiro-(dinheiro%valor))/valor;
							System.out.println("Voce pode comprar ate "+max+" Comida Natural");
							System.out.println("Deseja comprar quantas comidas?");
							String quantidade = sc.nextLine();

							try {
								int numeroQuantidade = Integer.parseInt(quantidade);
								// LC3gica para o nC:mero escolhido (se a conversC#o foi bem-sucedida)
							} catch (NumberFormatException e) {
								// Aconteceu um erro ao converter a string para int,
								// entC#o a string nC#o tinha um formato numC)rico vC!lido.
								quantidade = "0";
							}

							if (Integer.parseInt(quantidade)>max)System.out.println("Voce digitou um numero maior que o maximo, tente novamente...");
							else if (Integer.parseInt(quantidade)<0)System.out.println("Voce digitou um numero menor que 0, tente novamente...");
							else if (Integer.parseInt(quantidade)==0)System.out.println("Voce digitou uma opcao invalida, cancelando pedido.");
							else comprar(Integer.parseInt(comida),Integer.parseInt(quantidade),valor);
						}
						else {
							System.out.println("Voce nao tem dinheiro o suficiente para comprar uma Comida Natural :(");
						}
						break;
					}
					default-> {
						System.out.println("Opcao invalida");
					}
				}
				menu();
				break;
			}
		case 4 -> {
				System.out.println("Encerrando jogo");
				System.exit(0);
				break;
			}
			default-> {
				System.out.println("Opcao invalida.");
				System.out.println("Encerrando jogo");
				System.exit(0);
				break;
			}
		}
	}
}