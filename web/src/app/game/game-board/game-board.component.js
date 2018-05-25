import template from './game-board.html';

export const GameBoardComponent = {
  template: template,
  controller: class GameBoardController {
    constructor(GameService) {
      'ngInject';
      this.gameService = GameService;
    }

    $onInit() {
      this.game = null;
      this.loading = false;
      this.gamePlayers = {
        playerOne: 'playerOne',
        playerTwo: 'playerTwo'
      }

      newGame();
    }

    newGame() {
      this.loading = true;
      this.gameService.newGame()
        .then(gameId => this.gameService.getGame(gameId))
        .then(game => this.game = game)
        .finally(x => this.loading = false);
    }
  }
};