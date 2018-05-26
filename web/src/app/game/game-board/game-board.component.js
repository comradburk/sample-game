import template from './game-board.html';

export const GameBoardComponent = {
  template: template,
  controller: class GameBoardController {
    constructor(GameService, GamePlayers) {
      'ngInject';
      this.gameService = GameService;
      this.gamePlayers = GamePlayers;
    }

    $onInit() {
      this.game = null;
      this.loading = false;

      this.newGame();
    }

    newGame() {
      this.loading = true;
      this.gameService.newGame()
        .then(game => this.game = game)
        .finally(x => this.loading = false);
    }

    playPit(player, pitIndex) {
      this.gameService.performMove(this.game.id, player, pitIndex)
        .then(game => this.game = game);
    }

    isActivePlayer(gamePlayer) {
      return this.gameService.isActivePlayer(this.game, gamePlayer);
    }

    isGameOver() {
      return this.gameService.isGameOver(this.game);
    }

    victoryText() {
      debugger
      let playerOneScore = this.game.playerScore[this.gamePlayers.playerOne];
      let playerTwoScore = this.game.playerScore[this.gamePlayers.playerTwo];

      if (playerOneScore > playerTwoScore) {
        return "Player One Wins!";
      }

      if (playerTwoScore > playerOneScore) {
        return "Player Two Wins!";
      }

      return "Tie!";
    }
  }
};