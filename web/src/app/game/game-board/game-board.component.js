import template from './game-board.html';

export const GameBoardComponent = {
  template: template,
  controller: class GameBoardController {
    constructor(GameBoardService) {
      'ngInject';
      this.gameBoardService = GameBoardService;
    }

    $onInit() {
      this.game = {
        "playerOnePlayerPits": [
          { value: 6 },
          { value: 6 },
          { value: 6 },
          { value: 6 },
          { value: 6 },
          { value: 6 },
        ],
        "playerOneScorePit": {
          value: 0
        },
        "playerTwoPlayerPits": [
          { value: 6 },
          { value: 6 },
          { value: 6 },
          { value: 6 },
          { value: 6 },
          { value: 6 },
        ],
        "playerTwoScorePit": {
          value: 0
        }
      };
    }
  }
};