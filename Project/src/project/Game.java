package project;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {
	public void play() {
		System.out.println("========= Blackjack Game =========");
		Scanner sc = new Scanner(System.in);

		// Dealer dealer = new Dealer();
		// Gamer gamer = new Gamer();
		Rule rule = new Rule();
		CardDeck cardDeck = new CardDeck();

		List<Player> players = Arrays.asList(new Gamer("�����1"), new Dealer());
		List<Player> initAfterPlayers = initPhase(cardDeck, players);
		List<Player> playingAfterPlayers = playingPhase(sc, cardDeck, initAfterPlayers);

		Player winner = rule.getWinner(playingAfterPlayers);
		System.out.println("���ڴ� " + winner.getName());
		// 52���� ī��
		// System.out.println(cardDeck.toString());

		// ó�� �����ϸ� 2���� ī�尡 ���ԵǾ� �� 3���� ī�尡 ��µǴ� ���� Ȯ���� �� �ִ�.
		// initPhase(cardDeck, gamer);
		// playingPhase(sc, cardDeck, gamer);
	}

	private List<Player> playingPhase(Scanner sc, CardDeck cardDeck, List<Player> players) {
		List<Player> cardReceivedPlayers;
		while (true) {
			cardReceivedPlayers = receiveCardAllPlayers(sc, cardDeck, players);

			if (isAllPlayerTurnOff(cardReceivedPlayers)) {
				break;
			}
		}
		return cardReceivedPlayers;
	}

	private List<Player> receiveCardAllPlayers(Scanner sc, CardDeck cardDeck, List<Player> players) {
		for (Player player : players) {
			System.out.println(player.getName() + "�� �����Դϴ�.");
			System.out.println("ī�带 �̰ڽ��ϱ�? ���Ḧ ���Ͻø� 0�� �Է��ϼ���.");
			
			if (sc.nextInt()!=0) {
				Card card = cardDeck.draw();
				player.receiveCard(card);
				player.turnOn();
			} else {
				player.turnOff();
			}
		}

		return players;
	}

	private boolean isAllPlayerTurnOff(List<Player> players) {
		for (Player player : players) {
			if (player.isTurn()) {
				return false;
			}
		}

		return true;
	}

//	private boolean isReceiveCard(Scanner sc) {
//		System.out.println("ī�带 �̰ڽ��ϱ�? ���Ḧ ���Ͻø� 0�� �Է��ϼ���.");
//		return !STOP_RECEIVE_CARD.equals(sc.nextLine());
//	}

	private static final int INIT_RECEIVE_CARD_COUNT = 2;

	private List<Player> initPhase(CardDeck cardDeck, List<Player> players) {
		System.out.println("ó�� 2���� ī�带 ���� �̰ڽ��ϴ�.");
		for (int i = 0; i < INIT_RECEIVE_CARD_COUNT; i++) {
			for (Player player : players) {
				Card card = cardDeck.draw();
				player.receiveCard(card);
			}
		}

		return players;
	}
}