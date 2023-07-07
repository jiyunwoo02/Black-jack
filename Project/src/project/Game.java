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

		List<Player> players = Arrays.asList(new Gamer("사용자1"), new Dealer());
		List<Player> initAfterPlayers = initPhase(cardDeck, players);
		List<Player> playingAfterPlayers = playingPhase(sc, cardDeck, initAfterPlayers);

		Player winner = rule.getWinner(playingAfterPlayers);
		System.out.println("승자는 " + winner.getName());
		// 52장의 카드
		// System.out.println(cardDeck.toString());

		// 처음 실행하면 2장의 카드가 포함되어 총 3장의 카드가 출력되는 것을 확인할 수 있다.
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
			System.out.println(player.getName() + "님 차례입니다.");
			System.out.println("카드를 뽑겠습니까? 종료를 원하시면 0을 입력하세요.");
			
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
//		System.out.println("카드를 뽑겠습니까? 종료를 원하시면 0을 입력하세요.");
//		return !STOP_RECEIVE_CARD.equals(sc.nextLine());
//	}

	private static final int INIT_RECEIVE_CARD_COUNT = 2;

	private List<Player> initPhase(CardDeck cardDeck, List<Player> players) {
		System.out.println("처음 2장의 카드를 각자 뽑겠습니다.");
		for (int i = 0; i < INIT_RECEIVE_CARD_COUNT; i++) {
			for (Player player : players) {
				Card card = cardDeck.draw();
				player.receiveCard(card);
			}
		}

		return players;
	}
}