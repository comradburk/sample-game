import template from './game-list.html';

export const GameListComponent = {
  template: template,
  controller: class GameListComponent {
    constructor(GameListService) {
      'ngInject';
      this.gameListService = GameListService;
    }

    $onInit() {
      this.gameListService.getGames().then(data => {
        this.games = data;
      });
    }
  }
};