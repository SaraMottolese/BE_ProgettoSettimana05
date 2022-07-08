package servizi;

import java.text.DateFormat;
import java.sql.Date;
import java.util.Locale;
import java.util.Scanner;

import dati.Auto;
import dati.Infrazione;
import servizi.dao.AutoDao;
import servizi.dao.AutoDaoImpl;
import servizi.dao.InfrazioneDao;
import servizi.dao.InfrazioneDaoImpl;

public class Main {

	public static void main(String[] args) {
		AutoDao ad1 = new AutoDaoImpl();
		InfrazioneDao id1 = new InfrazioneDaoImpl();

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Operazioni da compiere:\n" + "[1] per compiere operazioni sulle auto\n"
					+ "[2] per compiere operazioni sulle infrazioni\n");
			int op1 = Integer.parseInt(scanner.nextLine());
			if (op1 == 1) {
				System.out.print("Cosa vuoi fare?\n" 
						+ "[1] per aggiungere una nuova auto\n"
						+ "[2] per stampare la lista di tutte le auto persenti nel database\n"
						+ "[3] cercare un auto per targa\n");
				int op2 = Integer.parseInt(scanner.nextLine());
				switch (op2) {
					case 1:
						System.out.println("inserisci la targa: ");
						String targa = scanner.nextLine().toUpperCase();
						System.out.println("inserisci la marca: ");
						String marca = scanner.nextLine();
						System.out.println("inserisci la modello: ");
						String modello = scanner.nextLine();
						Auto nuovaAuto = new Auto(targa, marca, modello);
						ad1.inserisciAuto(nuovaAuto);
						break;
					case 2:
						System.out.println(ad1.gettAllAuto());
						break;
					case 3:
						System.out.println("Inserisci la targa da cercare: ");
						String targa2 = scanner.nextLine().toUpperCase();
						System.out.println(ad1.cercaAuto(targa2));
						break;
					default:
						System.out.println("Comando non riconosciuto");
				}

			} else if (op1 == 2) {
				System.out.println("Cosa vuoi fare?\n"
						+ "[1] per inserire una nuova infrazione\n"
						+ "[2] stampare la lista di tutte le infrazioni di un auto\n"
						+ "[3] per eliminare un' infrazione esistente ");
				int op2 = Integer.parseInt(scanner.nextLine());
				switch (op2) {
					case 1:
						Date data= new Date(0,0,0);
						System.out.println("inserisci il giorno: ");
						data.setDate(Integer.parseInt(scanner.nextLine()));
						System.out.println("inserisci il mese: ");
						data.setMonth((Integer.parseInt(scanner.nextLine()))-1);
						System.out.println("inserisci l'anno: ");
						data.setYear((Integer.parseInt(scanner.nextLine()))-1900);
						System.out.println("Infrazione commessa: ");
						String tipo = scanner.nextLine();
						System.out.println("Inserisci l'importo in euro: ");
						double importo = Double.parseDouble(scanner.nextLine());
						System.out.println("Inserisci l'id della macchina che ha commesso l'infrazione: ");
						int id= Integer.parseInt(scanner.nextLine());
						Infrazione nuovaInfrazione= new Infrazione (data,tipo,importo, id);
						id1.inserisciInfrazione(nuovaInfrazione);
						break;
					case 2:
						System.out.println("Inserisci la targa da cercare: ");
						String targa2 = scanner.nextLine().toUpperCase();
						//System.out.print(id1.stampaDatiInfrazioneAuto(targa2));
						id1.stampaDatiInfrazioneAuto(targa2);
						
						break;
					case 3:
						System.out.println("Inserisci l'id dell'infrazione che vuoi eliminare: ");
						int idInfrazione= Integer.parseInt(scanner.nextLine());
						id1.eliminaInfrazione(idInfrazione);
						
						break;
					default:
						System.out.println("Comando non riconosciuto");
				}
			}
		}

	}

}
